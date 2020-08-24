package com.ptithcm.ptshop.view.carousel

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ptithcm.core.CoreApplication
import com.ptithcm.core.model.Filter
import com.ptithcm.core.model.ProductClothes
import com.ptithcm.core.param.ProductsOfProviderRequestParam
import com.ptithcm.core.vo.Result
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.base.BaseActivity
import com.ptithcm.ptshop.base.BaseFragment
import com.ptithcm.ptshop.constant.KEY_ARGUMENT
import com.ptithcm.ptshop.constant.KEY_IS_SHOW_FILTER_BY
import com.ptithcm.ptshop.constant.KEY_SEARCH
import com.ptithcm.ptshop.databinding.FragmentCarouselProductBinding
import com.ptithcm.ptshop.ext.*
import com.ptithcm.ptshop.util.ScrollHandler
import com.ptithcm.ptshop.view.carousel.adapter.CarouselProductPagedAdapter
import com.ptithcm.ptshop.viewmodel.CarouselDetailViewModel
import com.ptithcm.ptshop.viewmodel.ProvidersViewModel
import com.ptithcm.ptshop.viewmodel.RefineViewModel
import com.ptithcm.ptshop.viewmodel.WishListViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CarouselProductFragment : BaseFragment<FragmentCarouselProductBinding>(),
    View.OnClickListener {

    override val layoutId: Int = R.layout.fragment_carousel_product

    private val providersViewModel: ProvidersViewModel by sharedViewModel(from = { requireParentFragment() })
    private val viewModel: CarouselDetailViewModel by sharedViewModel(from = { requireParentFragment() })
    private val refineViewModel: RefineViewModel by sharedViewModel(from = { requireActivity() })
    private val wishListViewModel: WishListViewModel by viewModel()

    private lateinit var productAdapter: CarouselProductPagedAdapter
    private lateinit var scrollListener: RecyclerView.OnScrollListener
    private var filterParam: Filter? = Filter()
    private var scrollHandler: ScrollHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRequest()
    }

    override fun bindEvent() {
        super.bindEvent()
        scrollHandler = ScrollHandler(viewBinding.rvProducts)
        initScroll()
        intiAdapter()
        viewBinding.btnFab.setOnClickListener(this)
    }

    override fun bindViewModel() {
        viewModel.productsProviderLiveData.observe(this, Observer {
            productAdapter.submitList(it)
        })

        viewModel.productLoadStatusX.observe(this, Observer {
            productAdapter.setNetworkState(it)
            when (it) {
                is Result.Loading -> viewBinding.layoutLoading.visible()
                is Result.Error -> {
                    if (productAdapter.currentList?.isEmpty() == true) {
                        (requireActivity() as? BaseActivity<*>)?.isShowErrorNetwork(true)
                    }
                }
                else -> viewBinding.layoutLoading.gone()
            }
        })

        if (!refineViewModel.filterLiveData.hasObservers())
            refineViewModel.filterLiveData.observe(this, Observer {
                it?.let {
                    if (it.second) {
                        filterParam = it.first
                        initRequest(filterParam)
                    }
                }
            })

        wishListViewModel.addAndRemoveResult.observe(this, Observer {})
        wishListViewModel.error.observe(this, Observer {
            (requireActivity() as? BaseActivity<*>)?.isShowErrorNetwork(true)
        })
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btnFab -> {
                scrollHandler?.runHandler()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        scrollHandler?.removeHandler()
    }

    private fun intiAdapter() {
        productAdapter = CarouselProductPagedAdapter(
            this::eventListener,
            this::listenerAddProduct
        )

        val layoutManager = GridLayoutManager(requireContext(), 2)
        viewBinding.rvProducts.adapter = productAdapter
        viewBinding.rvProducts.layoutManager = layoutManager
        viewBinding.rvProducts.addOnScrollListener(scrollListener)

        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when {
                    position == 0 || (position == layoutManager.itemCount - 1 && productAdapter.hasExtraRow()) -> 2
                    else -> 1
                }
            }
        }
    }

    private fun initScroll() {
        var isRunning = false
        scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val position =
                    (viewBinding.rvProducts.layoutManager as? GridLayoutManager)?.findFirstVisibleItemPosition()
                        ?: 0
                if (position > 0) {
                    if (!isRunning) {
                        viewBinding.btnFab.runAnimationAlpha(true)
                        isRunning = !isRunning
                    }
                } else {
                    if (isRunning) {
                        viewBinding.btnFab.runAnimationAlpha(false)
                        isRunning = !isRunning
                    }
                }
            }
        }
    }

    private fun eventListener(product: ProductClothes?, isRefine: Boolean) {
        if (isRefine) {
            navController.navigateAnimation(
                R.id.nav_refine,
                bundle = bundleOf(
                    KEY_SEARCH to filterParam,
                    KEY_IS_SHOW_FILTER_BY to false
                )
            )
        } else {
            navController.navigateAnimation(
                R.id.fragment_product_detail,
                bundle = bundleOf(
                    KEY_ARGUMENT to product
                )
            )
        }
    }

    private fun listenerAddProduct(product: ProductClothes?, position: Int) {
        if (CoreApplication.instance.account != null) {
            if (product?.isLike == 0)
                product.isLike = 1
            else
                product?.isLike = 0

            wishListViewModel.addAndRemoveToWishList(product?.id)
        } else {
            messageHandler?.runMessageErrorHandler(getString(R.string.error_add_product))
        }
    }

    private fun initRequest(filter: Filter? = null) {
        val request = ProductsOfProviderRequestParam(
            providerID = providersViewModel.provider?.id,
            accountId = CoreApplication.instance.account?.id,
            filter = filter
        )
        viewModel.getPagingProductsProvider(request)
    }
}