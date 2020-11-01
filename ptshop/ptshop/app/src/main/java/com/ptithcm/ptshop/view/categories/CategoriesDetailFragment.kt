package com.ptithcm.ptshop.view.categories

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ptithcm.core.CoreApplication
import com.ptithcm.core.model.Category
import com.ptithcm.core.model.Filter
import com.ptithcm.core.model.ProductClothes
import com.ptithcm.core.param.ProductsOfCategoryRequestParam
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.base.BaseActivity
import com.ptithcm.ptshop.base.BaseFragment
import com.ptithcm.ptshop.constant.*
import com.ptithcm.ptshop.databinding.FragmentCategoriesDetailBinding
import com.ptithcm.ptshop.ext.*
import com.ptithcm.ptshop.util.ScrollHandler
import com.ptithcm.ptshop.view.MainActivity
import com.ptithcm.ptshop.view.categories.adapter.CategoriesPagedAdapter
import com.ptithcm.ptshop.viewmodel.CarouselDetailViewModel
import com.ptithcm.ptshop.viewmodel.RefineViewModel
import com.ptithcm.ptshop.viewmodel.WishListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoriesDetailFragment : BaseFragment<FragmentCategoriesDetailBinding>(),
    View.OnClickListener {

    override val layoutId: Int = R.layout.fragment_categories_detail

    private lateinit var adapter: CategoriesPagedAdapter

    private val viewModelProduct: CarouselDetailViewModel by viewModel()
    private val viewModelRefine: RefineViewModel by sharedViewModel(from = { requireActivity() })
    private val wishListViewModel: WishListViewModel by viewModel()

    private lateinit var category: Category
    private var filterParam: Filter? = Filter()
    private lateinit var scrollListener: RecyclerView.OnScrollListener
    private var scrollHandler: ScrollHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRequest()
    }

    override fun onResume() {
        super.onResume()
        activity?.btnNav?.visibility = View.GONE
    }

    override fun bindEvent() {
        super.bindEvent()
        scrollHandler = ScrollHandler(viewBinding.rvCategories)
        setupToolbar()
        initScroll()
        intiAdapter()
        viewBinding.btnFab.setOnClickListener(this)
    }

    override fun bindViewModel() {
        super.bindViewModel()
        viewModelProduct.productsCategoriesLiveData.observe(this, Observer {
            adapter.submitList(it)
        })

        viewModelProduct.productLoadStatusX.observe(this, Observer {
            adapter.setNetworkState(it)
            when (it) {
                is com.ptithcm.core.vo.Result.Error -> {
                    if (adapter.currentList?.isEmpty() == true) {
                        (requireActivity() as? MainActivity)?.isShowErrorNetwork(true)
                    }
                }
            }
        })

        viewModelProduct.networkStateRefine.observe(this, Observer {
            (requireActivity() as? MainActivity)?.isShowLoading(it)
        })

        if (!viewModelRefine.filterLiveData.hasObservers())
            viewModelRefine.filterLiveData.observe(this, Observer {
                it?.let {
                    if (it.second) {
                        filterParam = it.first
                        initRequest(filterParam)
                        viewModelRefine.filterLiveData.value = Pair(filterParam, false)
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

    private fun setupToolbar() {
        (requireActivity() as? MainActivity)?.apply {
            initToolBar(viewBinding.layoutToolbar.toolbar, hasBackRight = false)
            if (arguments?.getBoolean(IS_PRODUCT) == true) {
                setupToolbar(
                    viewBinding.layoutToolbar.toolbar, KEY_EMPTY,
                    messageQueue = this@CategoriesDetailFragment::onClickToolbar
                )
                viewBinding.layoutToolbar.tvTitleToolbar.gone()
            } else {
                setupToolbar(
                    viewBinding.layoutToolbar.toolbar,
                    category.name ?: "",
                    messageQueue = this@CategoriesDetailFragment::onClickToolbar
                )
            }
        }
    }

    private fun onClickToolbar(view: View) {
        when (view.id) {
            R.id.ivLeft -> {
                navController.navigateAnimation(R.id.nav_search)
            }
            R.id.ivRight -> {
                navController.navigateAnimation(R.id.nav_shopping_card, isBotToTop = true)
            }
        }
    }

    private fun initRequest(filter: Filter? = Filter()) {
        category = arguments?.getParcelable(KEY_ARGUMENT) ?: Category()
        val request = ProductsOfCategoryRequestParam(
            categoryID = category.id,
            accountId = CoreApplication.instance.account?.id,
            filter = filter
        )
        viewModelProduct.getPagingProductsCategories(request)
    }

    private fun intiAdapter() {
        adapter = CategoriesPagedAdapter(
            listener = this::eventListener, listenerAddProduct = this::listenerAddProduct
        )
        val layoutManager = GridLayoutManager(requireContext(), 2)
        viewBinding.rvCategories.layoutManager = layoutManager
        viewBinding.rvCategories.adapter = adapter
        viewBinding.rvCategories.addOnScrollListener(scrollListener)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when {
                    position == 0 || (position == layoutManager.itemCount - 1 && adapter.hasExtraRow()) -> 2
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
                    (viewBinding.rvCategories.layoutManager as? GridLayoutManager)?.findFirstVisibleItemPosition()
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
                bundle = bundleOf(KEY_ARGUMENT to product)
            )
        }
    }

    private fun listenerAddProduct(product: ProductClothes?) {
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
}