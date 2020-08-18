package com.ptithcm.ptshop.view.search

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ptithcm.core.CoreApplication
import com.ptithcm.core.model.ProductClothes
import com.ptithcm.core.param.RefineParam
import com.ptithcm.core.vo.Result
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.base.BaseFragment
import com.ptithcm.ptshop.constant.KEY_EMPTY
import com.ptithcm.ptshop.constant.KEY_SEARCH
import com.ptithcm.ptshop.databinding.FragmentSearchResultBinding
import com.ptithcm.ptshop.ext.*
import com.ptithcm.ptshop.view.MainActivity
import com.ptithcm.ptshop.view.categories.adapter.CategoriesPagedAdapter
import com.ptithcm.ptshop.viewmodel.RefineViewModel
import com.ptithcm.ptshop.viewmodel.SearchViewModel
import com.ptithcm.ptshop.viewmodel.WishListViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchResultFragment : BaseFragment<FragmentSearchResultBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_search_result
    private val searchViewModel: SearchViewModel by viewModel()
    private val refineViewModel: RefineViewModel by sharedViewModel(from = { requireActivity() })
    private val wishListViewModel: WishListViewModel by viewModel()

    private var key: String? = null
    private var sport: String? = null
    private var style: String? = null
    private var mTitle: String? = null
    private var isFirstLoad = true
    private var refineParam: RefineParam? = null

    private lateinit var scrollListener: RecyclerView.OnScrollListener
    private lateinit var productAdapter: CategoriesPagedAdapter
    private var isInitRefine = false
    private var isRequestRefine = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        key = arguments?.getString(KEY_SEARCH)
        sport = arguments?.getString("key_sport")
        style = arguments?.getString("key_style")
        mTitle = arguments?.getString("title")

        searchViewModel.getPagingSearchProduct(key, sport, style)
        observeRefineData()
    }

    override fun bindEvent() {
        super.bindEvent()
        (activity as? MainActivity)?.apply {
            viewBinding.btnNav.visible()
        }
        viewBinding.fragment = this
        setupToolbar()
        initScroll()
        intiAdapter()
        observeViewModel()
    }

    private fun observeViewModel() {
        searchViewModel.searchProductLiveData.observe(this, Observer {
            if (!isRequestRefine) {
//                productAdapter.submitList(it)
            }
        })

        searchViewModel.productLoadStatusX.observe(this, Observer {
            productAdapter.setNetworkState(it)
            when(it){
                is Result.Loading -> (requireActivity() as? MainActivity)?.isShowLoading(true)
                is Result.Error -> {
                    if (productAdapter.currentList?.isEmpty() == true){
                        (requireActivity() as? MainActivity)?.isShowErrorNetwork(true)
                    }
                }
                else -> (requireActivity() as? MainActivity)?.isShowLoading(false)
            }
        })
    }

    private fun observeRefineData() {
        searchViewModel.refineProductLiveData.observe(this, Observer {
            if (isRequestRefine) {
//                productAdapter.submitList(it)
            }
        })
    }

    fun onClick(view: View?) {
        when (view?.id) {
            R.id.btnFab -> {
                viewBinding.rvProducts.smoothScrollToPosition(0)
                viewBinding.btnFab.runAnimationAlpha(false)
            }
        }
    }

    private fun intiAdapter() {
//        productAdapter =
//            CategoriesPagedAdapter(
//                listener = this::eventListener,
//                listenerAddProduct = this::listenerAddProduct
//            )
        val layoutManager = GridLayoutManager(requireContext(), 2)
        viewBinding.rvProducts.layoutManager = layoutManager
        viewBinding.rvProducts.adapter = productAdapter
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
                if (position > 1) {
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

//    private fun eventListener(product: Product?, isRefine: Boolean) {
//        if (isRefine) {
//            isFirstLoad = true
//            refineParam = if (!this.isInitRefine)
//                RefineParam(key = key, sport = sport, style = style) else refineParam
//                ?: RefineParam()
//            navController.navigateAnimation(
//                R.id.nav_refine,
//                bundle = bundleOf(IS_PRODUCT to true, KEY_ARGUMENT to refineParam)
//            )
//        } else {
//            navController.navigateAnimation(
//                R.id.fragment_product_detail,
//                bundle = bundleOf(KEY_ARGUMENT to product, KEY_ARGUMENT_REFINE to refineParam)
//            )
//        }
//        this.isInitRefine = isRefine
//    }

    private fun listenerAddProduct(product: ProductClothes?) {
        if (CoreApplication.instance.account != null) {
            wishListViewModel.addAndRemoveToWishList(product?.id)
        } else {
            messageHandler?.runMessageErrorHandler(getString(R.string.error_add_product))
        }
    }

    private fun setupToolbar() {
        (activity as? MainActivity)?.apply {
            val toolbar = viewBinding.layoutToolbar.toolbar
            toolbar.visible()
            initToolBar(toolbar, hasBackRight = false)
            setupToolbar(toolbar, key ?: mTitle ?: KEY_EMPTY, messageQueue = {
                when (it.id) {
                    R.id.ivRight, R.id.tvCount -> {
                        navController.navigateAnimation(
                            R.id.nav_shopping_card,
                            isBotToTop = true
                        )
                    }
                    R.id.ivLeft -> {
                        navController.navigateAnimation(R.id.fragment_search)
                    }
                }
            })

        }
    }
}