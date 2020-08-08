package com.ptithcm.ptshop.view.carousel

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ptithcm.core.CoreApplication
import com.ptithcm.core.model.Brand
import com.ptithcm.core.model.ProductClothes
import com.ptithcm.core.model.SearchParams
import com.ptithcm.core.model.TypeCarousel
import com.ptithcm.core.param.RefineParam
import com.ptithcm.core.util.INIT_PAGE
import com.ptithcm.core.util.PAGE_SIZE
import com.ptithcm.core.vo.Result
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.base.BaseActivity
import com.ptithcm.ptshop.base.BaseFragment
import com.ptithcm.ptshop.constant.*
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
    private var vendorBrand: Brand? = null
    private lateinit var scrollListener: RecyclerView.OnScrollListener
    private var refineParam: RefineParam? = null
    private var filterParam: SearchParams? = null
    private lateinit var typeCarousel: TypeCarousel
    private var isInitRefine = true
    private var isRequestRefine = false
    private var scrollHandler: ScrollHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBindViewModel()
    }

    override fun bindEvent() {
        super.bindEvent()
        scrollHandler = ScrollHandler(viewBinding.rvProducts)
        initScroll()
        intiAdapter()
        viewBinding.btnFab.setOnClickListener(this)
    }

    override fun bindViewModel() {
        super.bindViewModel()
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

    private fun initBindViewModel() {
        viewModel.getPagingProductsProvider(
            providersViewModel.provider?.id ?: 0,
            20,
            1,
            CoreApplication.instance.account?.id ?: 0
        )

        viewModel.refineProductLiveData.observe(this, Observer {
            if (isRequestRefine) {
                productAdapter.submitList(it)
            }
        })

        refineViewModel.filterLiveData.observe(this, Observer {
            it?.let {
                filterParam = it.first
                if (it.second) {
                    isRequestRefine = it.second
                    viewModel.getPagingRefineProduct(it.first)
                }
            }
        })

        wishListViewModel.addAndRemoveResult.observe(this, Observer {})
        wishListViewModel.error.observe(this, Observer {
            (requireActivity() as? BaseActivity<*>)?.isShowErrorNetwork(true)
        })
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
            if (isInitRefine)
                filterParam = initRefineParam()

            navController.navigateAnimation(
                R.id.nav_refine,
                bundle = bundleOf(
                    KEY_SEARCH to filterParam,
                    KEY_IS_SHOW_FILTER_BY to false
                )
            )
            this.isInitRefine = false
        } else {
            navController.navigateAnimation(
                R.id.fragment_product_detail,
                bundle = bundleOf(
                    KEY_ARGUMENT to product,
                    KEY_ARGUMENT_REFINE to refineParam
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

    private fun initRefineParam(): SearchParams {
        return SearchParams(
            accountId = CoreApplication.instance.account?.id,
            idTypeSearch = providersViewModel.provider?.id,
            typeSearch = SEARCH_BY_DESIGNER,
            typeFilter = null,
            typeSearchFilter = 2,
            pageNumber = INIT_PAGE,
            pageSize = PAGE_SIZE,
            keySearch = KEY_EMPTY
        )
    }

//    private fun initBrandRefine(): ArrayList<BrandsRefine>? {
//        val brandRefines = arrayListOf<BrandsRefine>()
//        when (typeCarousel) {
//            TypeCarousel.BRAND -> {
//                if (vendorBrand?.stores?.isNotEmpty() == true) {
//                    vendorBrand?.stores?.forEach { item ->
//                        val brandRefine = BrandsRefine()
//                        brandRefine.brandId = vendorBrand?.id
//                        brandRefine.storeId = item.id
//                        brandRefine.name =
//                            "${vendorBrand?.name} (${item.brand_name})"
//                        brandRefines.add(brandRefine)
//                    }
//                }
//
//            }
//            else -> {
//                val brandRefine = BrandsRefine()
//                brandRefine.brandId = vendorBrand?.id
//                brandRefine.storeId = vendorBrand?.store_id
//                if (vendorBrand?.name?.isNotEmpty() == true) {
//                    if (vendorBrand?.name?.contains("${vendorBrand?.brand_name}") == false) {
//                        brandRefine.name = "${vendorBrand?.name} (${vendorBrand?.brand_name})"
//                    } else {
//                        brandRefine.name = vendorBrand?.name
//                    }
//                } else {
//                    brandRefine.name = vendorBrand?.brand_name
//                }
//                brandRefines.add(brandRefine)
//            }
//        }
//        return brandRefines
//    }

}