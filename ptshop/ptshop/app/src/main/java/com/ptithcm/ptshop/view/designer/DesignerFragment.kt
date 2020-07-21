package com.ptithcm.ptshop.view.designer

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ptithcm.core.model.Brand
import com.ptithcm.core.param.BrandParam
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.base.BaseFragment
import com.ptithcm.ptshop.constant.ERROR_CODE_404
import com.ptithcm.ptshop.constant.KEY_ARGUMENT
import com.ptithcm.ptshop.constant.KEY_DESIGNER
import com.ptithcm.ptshop.databinding.FragmentDesignerBinding
import com.ptithcm.ptshop.ext.*
import com.ptithcm.ptshop.listener.EndlessRecyclerViewScrollListener
import com.ptithcm.ptshop.view.MainActivity
import com.ptithcm.ptshop.viewmodel.BrandsViewModel
import com.ptithcm.ptshop.viewmodel.DesignerViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DesignerFragment : BaseFragment<FragmentDesignerBinding>() {

    override val layoutId: Int = R.layout.fragment_designer
    private val designerViewModel: DesignerViewModel by viewModel()
    private val brandViewModel: BrandsViewModel by sharedViewModel(from = { requireActivity() })
    private val designerAdapter = DesignerAdapter(this::eventListener, this::viewAllDesigners)
    private var page = 1
    private var additionList = arrayListOf<Brand>()
    private lateinit var listener: RecyclerView.OnScrollListener
    private var isError = false
    private var isReadyAllStore = false

    private fun setupToolbar() {
        (requireActivity() as? MainActivity)?.apply {
            viewBinding.btnNav.visible()
            initToolBar(
                viewBinding.layoutToolbar.toolbar, false, hasBackRight = false
            )
            setupToolbar(viewBinding.layoutToolbar.toolbar, getString(R.string.designer),
                messageQueue = {
                    when (it.id) {
                        R.id.ivRight, R.id.tvCount -> {
                            navController.navigateAnimation(
                                R.id.nav_shopping_card,
                                isBotToTop = true, bundle = bundleOf(KEY_ARGUMENT to true)
                            )
                        }
                        R.id.ivLeft -> {
                            navController.navigate(R.id.nav_search)
                        }
                    }
                }
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getPopularBrands()
        response()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setUpAdapter()
    }

    private fun response() {
        brandViewModel.pagedList.observe(this, Observer {
            isReadyAllStore = true
            (requireActivity() as? MainActivity)?.isShowLoading(false)
        })

        brandViewModel.storesLiveData.observe(this, Observer { listStories->
            listStories.removeAll { it.avatar_image.isNullOrEmpty() }
            additionList.clear()
            additionList.addAll(listStories.map { story ->
                Brand(id = story.id, avatar_image = story.avatar_image)
            })
        })

        designerViewModel.brandResult.observe(this, Observer {
            it?.let {
                if (page == 1) {
                    // the first item is Banner, the second item is Load more
                    designerAdapter.setList(arrayListOf(Brand(), Brand()))
                }
                // add new list between Banner and Load more
                designerAdapter.addAll(it)

            }

        })

        designerViewModel.networkState.observe(this, Observer {
            if (page == 1) {
                if (isReadyAllStore)
                    (requireActivity() as? MainActivity)?.isShowLoading(it)
                else
                    (requireActivity() as? MainActivity)?.isShowLoading(true)
            }

            if (!it) {
                viewBinding.swDesigner.isRefreshing = false
            }
        })

        designerViewModel.error.observe(this, Observer {
            if (it.second == ERROR_CODE_404 && !isError) {
                isError = true
                viewBinding.rcvBrand.removeOnScrollListener(listener)
                designerAdapter.addAll(additionList)
                (requireActivity() as? MainActivity)?.isShowLoading(false)
                designerAdapter.hideLoadMore()
            } else if (page == 1) {
                (requireActivity() as? MainActivity)?.isShowErrorNetwork(true)
            }
        })

    }

    override fun bindEvent() {
        (requireActivity() as MainActivity).btnNav.visible()
        viewBinding.btnFab.setOnClickListener {
            viewBinding.rcvBrand.smoothScrollToPosition(0)
        }
        viewBinding.swDesigner.setOnRefreshListener {
            isError = false
            page = 1
            getPopularBrands()
        }
    }

    private fun setUpAdapter() {
        val gridLayoutManager = GridLayoutManager(requireContext(), 3)
        viewBinding.rcvBrand.apply {
            layoutManager = gridLayoutManager
            adapter = designerAdapter
            listener = object :
                EndlessRecyclerViewScrollListener(this.layoutManager as? LinearLayoutManager) {
                override fun onLoadMore(x: Int, y: Int) {
                    if (!isError) {
                        page++
                        getPopularBrands()
                    }
                }
            }
            val listenerScrollFab = object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if ((recyclerView.layoutManager as? GridLayoutManager)?.findFirstVisibleItemPosition() != 0) {
                        viewBinding.btnFab.visible()
                    } else {
                        viewBinding.btnFab.gone()
                    }
                }
            }
            addOnScrollListener(listener)
            addOnScrollListener(listenerScrollFab)
        }
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (position) {
                    0 -> 3
                    gridLayoutManager.itemCount - 1 -> {
                        if (!isError)
                            3
                        else 1
                    }
                    else -> 1
                }
            }
        }
    }

    private fun eventListener(brand: Brand?) {
        val carousel = brand?.copyCarousel()
        navController.navigate(R.id.action_carousel_detail, bundleOf(KEY_ARGUMENT to carousel, KEY_DESIGNER to true))
    }

    private fun viewAllDesigners() {
        navController.navigate(R.id.action_all_designers)
    }

    private fun getPopularBrands() {
        designerViewModel.getPopularBrands(
            BrandParam(
                filter_popular = true, only_image = true,
                ordering = "-popular_score", page = page
            )
        )
    }

}