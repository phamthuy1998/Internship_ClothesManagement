package com.ptithcm.ptshop.view.categories

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ptithcm.core.CoreApplication
import com.ptithcm.core.model.Category
import com.ptithcm.core.model.Product
import com.ptithcm.core.model.ProductClothes
import com.ptithcm.core.model.TypeCategories
import com.ptithcm.core.param.CategoriesParam
import com.ptithcm.core.param.ProductsParam
import com.ptithcm.core.param.RefineParam
import com.ptithcm.core.param.StoriesRefine
import com.ptithcm.core.vo.Result
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.base.BaseFragment
import com.ptithcm.ptshop.constant.*
import com.ptithcm.ptshop.databinding.FragmentCategoriesDetailBinding
import com.ptithcm.ptshop.ext.*
import com.ptithcm.ptshop.util.ScrollHandler
import com.ptithcm.ptshop.view.MainActivity
import com.ptithcm.ptshop.view.categories.adapter.CategoriesPagedAdapter
import com.ptithcm.ptshop.viewmodel.CarouselDetailViewModel
import com.ptithcm.ptshop.viewmodel.ProductFilterViewModel
import com.ptithcm.ptshop.viewmodel.RefineViewModel
import com.ptithcm.ptshop.viewmodel.WishListViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoriesDetailFragment : BaseFragment<FragmentCategoriesDetailBinding>(),
    View.OnClickListener {

    override val layoutId: Int = R.layout.fragment_categories_detail

    private lateinit var adapter: CategoriesPagedAdapter

    private val viewModelProduct: CarouselDetailViewModel by viewModel()
    private val viewModelFilter: ProductFilterViewModel by sharedViewModel(from = { requireActivity() })
    private val viewModelRefine: RefineViewModel by sharedViewModel(from = { requireActivity() })
    private val wishListViewModel: WishListViewModel by viewModel()

    private lateinit var categoriesParam: CategoriesParam
    private lateinit var productCategory: ProductsParam
    private lateinit var category: Category
    private var refineParam: RefineParam? = null
    private var banner: String? = null
    private var sizeType: Int? = null
    private var isInitRefine = false
    private var isRequestRefine = false
    private lateinit var scrollListener: RecyclerView.OnScrollListener
    private var scrollHandler: ScrollHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
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
//        viewModelFilter.allCategoriesRefineLiveData.observe(this, Observer {
//            if (!isInitRefine) {
//                if (arguments?.getBoolean(IS_PRODUCT) == true) {
//                    val categoriesRefine = it.getItemCategories(productCategory)
//                    refineParam?.categories = categoriesRefine
//                } else {
//                    sizeType = if (categoriesParam.main_category_id != 0) {
//                        it.first { item -> item.value == categoriesParam.main_category_id }
//                            .size_type
//                    } else 0
//                }
//            }
//        })
//
//        viewModelProduct.productsCategoriesLiveData.observe(this, Observer {
//            if (!isRequestRefine) {
//                adapter.submitList(it)
//            }
//        })
//
//        viewModelProduct.productLoadStatusX.observe(this, Observer {
//            adapter.setNetworkState(it)
//            when (it) {
//                is Result.Error -> {
//                    if (adapter.currentList?.isEmpty() == true) {
//                        (requireActivity() as? MainActivity)?.isShowErrorNetwork(true)
//                    }
//                }
//            }
//        })
//
//        viewModelProduct.networkStateRefine.observe(this, Observer {
//            (requireActivity() as? MainActivity)?.isShowLoading(it)
//        })
//
//        wishListViewModel.addResult.observe(this, Observer { })
//        wishListViewModel.removeResult.observe(this, Observer { })
//        wishListViewModel.error.observe(this, Observer {
//            (requireActivity() as? MainActivity)?.isShowErrorNetwork(true)
//        })

        viewModelProduct.refineProductLiveData.observe(this, Observer {
            adapter.submitList(it)
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
            viewBinding.btnNav.visible()
            initToolBar(viewBinding.layoutToolbar.toolbar, hasBackRight = false)
            viewModelFilter.requestAllCategories(null)
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

    private fun initView() {
        category = arguments?.getParcelable(KEY_ARGUMENT) ?: Category()
        viewModelProduct.getProducts(
            category.id ?: 1,
            20,
            1,
            CoreApplication.instance.account?.id ?: 0
        )
//        refineParam = RefineParam()
//        banner = arguments?.getString(KEY_BANNER_CATEGORY)
//        if (arguments?.getBoolean(IS_PRODUCT) == true) {
//            productCategory = arguments?.getParcelable(KEY_ARGUMENT) ?: ProductsParam()
//            viewModelProduct.getPagingProductsCategories(
//                productCategory.mainCategories, productCategory.categories,
//                arrayListOf(), productCategory.gender
//            )
//        } else {
//            categoriesParam = arguments?.getParcelable(KEY_ARGUMENT) ?: CategoriesParam()
//            viewModelProduct.getPagingProductsCategories(
//                if (categoriesParam.main_category_id == 0) arrayListOf() else arrayListOf(
//                    categoriesParam.main_category_id
//                ),
//                if (categoriesParam.category_id == 0) arrayListOf() else arrayListOf(categoriesParam.category_id),
//                if (categoriesParam.filter_id == 0) arrayListOf() else arrayListOf(categoriesParam.filter_id),
//                categoriesParam.gender
//            )
//        }
//        viewModelRefine.refineLiveData.observe(this, Observer {
//            it?.let {
//                if (isInitRefine) {
//                    refineParam = it.first
//                    if (it.second) {
//                        isRequestRefine = true
//                        viewModelProduct.getPagingRefineProduct(it.first)
//                    } else {
//                        observeRefineData()
//                    }
//                }
//            }
//        })
    }

    private fun intiAdapter() {
        adapter = CategoriesPagedAdapter(
            banner,
            listener = this::eventListener, listenerAddProduct = this::listenerAddProduct
        )
        val layoutManager = GridLayoutManager(requireContext(), 2)
        viewBinding.rvCategories.layoutManager = layoutManager
        viewBinding.rvCategories.adapter = adapter
        viewBinding.rvCategories.addOnScrollListener(scrollListener)
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
            when (arguments?.getBoolean(IS_PRODUCT)) {
                true -> {
                    refineParam?.gender = arrayListOf(switchGender(productCategory.gender))
                    navController.navigateAnimation(
                        R.id.nav_refine,
                        bundle = bundleOf(KEY_ARGUMENT to refineParam, IS_PRODUCT to true)
                    )
                }
                else -> {
                    if (!this.isInitRefine) {
                        initRefineParam()
                    }
                    viewModelRefine.categoriesParamLiveData.value =
                        Pair(categoriesParam, switchGender(categoriesParam.gender))
                    viewModelRefine.sizeType.value = sizeType
                    navController.navigateAnimation(
                        R.id.nav_refine,
                        bundle = bundleOf(
                            KEY_ARGUMENT to refineParam,
                            IS_PRODUCT to false,
                            KEY_MAIN_CATEGORIES to (categoriesParam.typeCategories == TypeCategories.MAIN_CATEGORIES)
                        )
                    )
                }
            }
            this.isInitRefine = true
        } else {
            navController.navigateAnimation(
                R.id.fragment_product_detail,
                bundle = bundleOf(KEY_ARGUMENT to product, KEY_ARGUMENT_REFINE to refineParam)
            )
        }
    }

    private fun listenerAddProduct(product: ProductClothes?) {
//        if (CoreApplication.instance.profile != null) {
//            product?.isAddProduct = !(product?.isAddProduct ?: false)
//            if (product?.isAddProduct == true) {
//                wishListViewModel.addToWishList(product.id)
//            } else {
//                wishListViewModel.removeFromWishList(product?.id)
//            }
//        } else {
//            messageHandler?.runMessageErrorHandler(getString(R.string.error_add_product))
//        }
    }

    private fun initRefineParam() {
        refineParam =
            RefineParam(
                ourPicks = false, newItems = false, priceHigh = false,
                priceLow = false, storiesRefine = StoriesRefine()
            )
        val categoriesRefine = categoriesParam.copyItemAllCategoryRefine()
        refineParam?.categories = arrayListOf(categoriesRefine)
        refineParam?.gender = arrayListOf(switchGender(categoriesParam.gender))
    }

}