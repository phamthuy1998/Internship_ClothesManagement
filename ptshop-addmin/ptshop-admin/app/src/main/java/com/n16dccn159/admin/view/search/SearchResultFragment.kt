package com.n16dccn159.admin.view.search

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.n16dccn159.core.CoreApplication
import com.n16dccn159.core.model.Filter
import com.n16dccn159.core.model.ProductClothes
import com.n16dccn159.core.model.SearchParams
import com.n16dccn159.core.vo.Result
import com.n16dccn159.admin.R
import com.n16dccn159.admin.base.BaseActivity
import com.n16dccn159.admin.base.BaseFragment
import com.n16dccn159.admin.constant.KEY_ARGUMENT
import com.n16dccn159.admin.constant.KEY_IS_SHOW_FILTER_BY
import com.n16dccn159.admin.constant.KEY_SEARCH
import com.n16dccn159.admin.databinding.FragmentSearchResultBinding
import com.n16dccn159.admin.ext.*
import com.n16dccn159.admin.view.MainActivity
import com.n16dccn159.admin.view.search.adapter.SearchProductPagedAdapter
import com.n16dccn159.admin.viewmodel.CarouselDetailViewModel
import com.n16dccn159.admin.viewmodel.RefineViewModel
import com.n16dccn159.admin.viewmodel.WishListViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchResultFragment : BaseFragment<FragmentSearchResultBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_search_result

    private lateinit var adapter: SearchProductPagedAdapter

    private val viewModelProduct: CarouselDetailViewModel by viewModel()
    private val viewModelRefine: RefineViewModel by sharedViewModel(from = { requireActivity() })
    private val wishListViewModel: WishListViewModel by viewModel()

    private var key: String? = null

    private var filterParam: Filter? = Filter()

    private lateinit var scrollListener: RecyclerView.OnScrollListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        key = arguments?.getString(KEY_SEARCH)
        initRequest(keySearch = key)
    }

    override fun bindEvent() {
        super.bindEvent()
        (activity as? MainActivity)?.apply {
            viewBinding.btnNav.visible()
        }
        viewBinding.fragment = this
        setupToolbar()
        initScroll()
        initAdapter()
    }

    override fun bindViewModel() {
        super.bindViewModel()
        viewModelProduct.refineProductLiveData.observe(this, Observer {
            adapter.submitList(it)
        })

        viewModelProduct.productLoadStatusX.observe(this, Observer {
            adapter.setNetworkState(it)
            when (it) {
                is Result.Error -> {
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
                        initRequest(keySearch = key, filter = filterParam)
                        viewModelRefine.filterLiveData.value = Pair(filterParam, false)
                    }
                }
            })

        wishListViewModel.addAndRemoveResult.observe(this, Observer {})
        wishListViewModel.error.observe(this, Observer {
            (requireActivity() as? BaseActivity<*>)?.isShowErrorNetwork(true)
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

    private fun initAdapter() {
        adapter = SearchProductPagedAdapter(
            listener = this::eventListener, listenerAddProduct = this::listenerAddProduct
        )
        val layoutManager = GridLayoutManager(requireContext(), 2)
        viewBinding.rvProducts.layoutManager = layoutManager
        viewBinding.rvProducts.adapter = adapter
        viewBinding.rvProducts.addOnScrollListener(scrollListener)
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
                bundle = bundleOf(KEY_ARGUMENT to product)
            )
        }
    }

    private fun listenerAddProduct(product: ProductClothes?, position: Int?) {
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

    private fun setupToolbar() {
        (activity as? MainActivity)?.apply {
            val toolbar = viewBinding.layoutToolbar.toolbar
            toolbar.visible()
            initToolBar(toolbar, hasBackRight = false)
            setupToolbar(toolbar, key ?: "All", messageQueue = {
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

    private fun initRequest(keySearch: String? = "", filter: Filter? = Filter()) {
        val request = SearchParams(
            keySearch = keySearch,
            accountId = CoreApplication.instance.account?.id,
            filter = filter
        )
        viewModelProduct.searchPagingProducts(request)
    }
}