package com.sg.snapshop.view.carousel

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sg.core.CoreApplication
import com.sg.core.model.Brand
import com.sg.core.model.Gender
import com.sg.core.model.Product
import com.sg.core.model.TypeCarousel
import com.sg.core.param.BrandsRefine
import com.sg.core.param.RefineParam
import com.sg.core.param.StoriesRefine
import com.sg.core.vo.ItemViewModel
import com.sg.core.vo.Result
import com.sg.snapshop.R
import com.sg.snapshop.base.BaseActivity
import com.sg.snapshop.base.BaseFragment
import com.sg.snapshop.constant.IS_PRODUCT
import com.sg.snapshop.constant.KEY_ARGUMENT
import com.sg.snapshop.constant.KEY_ARGUMENT_REFINE
import com.sg.snapshop.databinding.FragmentCarouselProductBinding
import com.sg.snapshop.ext.*
import com.sg.snapshop.util.ScrollHandler
import com.sg.snapshop.view.carousel.adapter.CarouselProductPagedAdapter
import com.sg.snapshop.viewmodel.CarouselDetailViewModel
import com.sg.snapshop.viewmodel.RefineViewModel
import com.sg.snapshop.viewmodel.WishListViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CarouselProductFragment : BaseFragment<FragmentCarouselProductBinding>(),
    View.OnClickListener {

    override val layoutId: Int = R.layout.fragment_carousel_product

    private val viewModel: CarouselDetailViewModel by sharedViewModel(from = { requireParentFragment() })
    private val refineViewModel: RefineViewModel by sharedViewModel(from = { requireActivity() })
    private val wishListViewModel: WishListViewModel by viewModel()
    private lateinit var productAdapter: CarouselProductPagedAdapter
    private var vendorBrand: Brand? = null
    private lateinit var scrollListener: RecyclerView.OnScrollListener
    private var refineParam: RefineParam? = null
    private lateinit var typeCarousel: TypeCarousel
    private var isInitRefine = false
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
        viewModel.brandsProductDetailLiveData.observe(this, Observer {
            if (!isRequestRefine) {
                productAdapter.submitList(it)
            }
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

    private fun observeRefineData() {
        viewModel.refineProductLiveData.observe(this, Observer {
            if (isRequestRefine) {
                productAdapter.submitList(it)
            }
        })
    }

    private fun initBindViewModel() {
        observeRefineData()

        viewModel.brandLiveData.observe(this, Observer {
            if (!isInitRefine) {
                vendorBrand = it.first
                typeCarousel = it.third
                val designers = arrayListOf<String>()
                if (vendorBrand?.stores?.isNotEmpty() == true) {
                    vendorBrand?.stores?.forEach { item ->
                        designers.add("${vendorBrand?.id}:${item.id}")
                    }
                } else {
                    designers.add("${vendorBrand?.id}:")
                }
                viewModel.getPagingBrandsProductDetail(designers, it.second.value)
            }
        })


        viewModel.storiesLiveData.observe(this, Observer {
            if (!isInitRefine) {
                vendorBrand = it.first
                typeCarousel = it.third
                val designer =
                    if (vendorBrand?.id == 0) ":${vendorBrand?.store_id}" else "${vendorBrand?.id}:${vendorBrand?.store_id}"
                val designers = arrayListOf(designer)
                viewModel.getPagingBrandsProductDetail(designers, it.second.value)
            }
        })

        refineViewModel.refineLiveData.observe(this, Observer {
            it?.let {
                if (isInitRefine) {
                    refineParam = it.first
                    if (it.second) {
                        isRequestRefine = it.second
                        viewModel.getPagingRefineProduct(it.first)
                    } else {
                        observeRefineData()
                    }
                }
            }
        })

        wishListViewModel.addResult.observe(this, Observer {})
        wishListViewModel.removeResult.observe(this, Observer {})
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
                return when (position) {
                    0, layoutManager.itemCount - 1 -> 2
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

    private fun eventListener(product: Product?, isRefine: Boolean) {
        if (isRefine) {
            refineParam = if (!this.isInitRefine) {
                initRefineParam(initBrandRefine() ?: arrayListOf())
            } else {
                refineParam ?: RefineParam()
            }
            refineViewModel.sizeType.value = 0
            navController.navigateAnimation(
                R.id.nav_refine,
                bundle = bundleOf(IS_PRODUCT to true, KEY_ARGUMENT to refineParam)
            )
            this.isInitRefine = true
        } else {
            navController.navigateAnimation(
                R.id.fragment_product_detail,
                bundle = bundleOf(KEY_ARGUMENT to product,
                    KEY_ARGUMENT_REFINE to refineParam)
            )
        }
    }

    private fun listenerAddProduct(product: Product?, position: Int) {
        if (CoreApplication.instance.profile != null) {
            product?.isAddProduct = !(product?.isAddProduct ?: false)
            if (product?.isAddProduct == true) {
                wishListViewModel.addToWishList(product.id)
            } else {
                wishListViewModel.removeFromWishList(product?.id)
            }
        } else {
            messageHandler?.runMessageErrorHandler(getString(R.string.error_add_product))
        }
    }

    private fun initRefineParam(brands: ArrayList<BrandsRefine>): RefineParam {
        val genders = arrayListOf<Gender>()
        if (viewModel.brandLiveData.value?.second != null) {
            genders.add(viewModel.brandLiveData.value?.second ?: Gender.NONE)
        }
        val storiesRefine = if (typeCarousel == TypeCarousel.STORE) {
            StoriesRefine(true, viewModel.storiesLiveData.value?.first?.store_id)
        } else StoriesRefine()
        return RefineParam(
            newItems = false, ourPicks = false, priceLow = false, priceHigh = false,
            gender = genders, brands = brands, storiesRefine = storiesRefine
        )
    }

    private fun initBrandRefine(): ArrayList<BrandsRefine>? {
        val brandRefines = arrayListOf<BrandsRefine>()
        when (typeCarousel) {
            TypeCarousel.BRAND -> {
                if (vendorBrand?.stores?.isNotEmpty() == true) {
                    vendorBrand?.stores?.forEach { item ->
                        val brandRefine = BrandsRefine()
                        brandRefine.brandId = vendorBrand?.id
                        brandRefine.storeId = item.id
                        brandRefine.name =
                            "${vendorBrand?.name} (${item.brand_name})"
                        brandRefines.add(brandRefine)
                    }
                }

            }
            else -> {
                val brandRefine = BrandsRefine()
                brandRefine.brandId = vendorBrand?.id
                brandRefine.storeId = vendorBrand?.store_id
                if (vendorBrand?.name?.isNotEmpty() == true) {
                    if (vendorBrand?.name?.contains("${vendorBrand?.brand_name}") == false) {
                        brandRefine.name = "${vendorBrand?.name} (${vendorBrand?.brand_name})"
                    } else {
                        brandRefine.name = vendorBrand?.name
                    }
                } else {
                    brandRefine.name = vendorBrand?.brand_name
                }
                brandRefines.add(brandRefine)
            }
        }
        return brandRefines
    }

}