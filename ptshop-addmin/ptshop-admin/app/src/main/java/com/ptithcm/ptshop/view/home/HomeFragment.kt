package com.ptithcm.ptshop.view.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.ptithcm.core.model.TypeCarousel
import com.ptithcm.core.param.StoryParam
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.base.BaseFragment
import com.ptithcm.ptshop.constant.GOTO_ACTIVITY_STORY
import com.ptithcm.ptshop.constant.KEY_ARGUMENT
import com.ptithcm.ptshop.constant.KEY_ID
import com.ptithcm.ptshop.constant.KEY_TYPE
import com.ptithcm.ptshop.databinding.FragmentHomeBinding
import com.ptithcm.ptshop.ext.*
import com.ptithcm.ptshop.util.ScrollHandler
import com.ptithcm.ptshop.util.SpaceItemDecorator
import com.ptithcm.ptshop.view.MainActivity
import com.ptithcm.ptshop.viewmodel.HomeViewModel
import com.ptithcm.spannedgridlayout.SpanSize
import com.ptithcm.spannedgridlayout.SpannedGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override val layoutId: Int = R.layout.fragment_home

    private val homeViewModel: HomeViewModel by sharedViewModel()
    private val homePagingAdapter = HomePagingAdapter {
        if (isStartActivity){
            return@HomePagingAdapter
        }
        isStartActivity = true
        startActivityForResult(
            Intent(activity, StoryDetailActivity::class.java).apply {
                putExtra(KEY_ID, it?.brand?.id)
                putExtra(KEY_TYPE, if(it?.brand?.stores.isNullOrEmpty()) TypeCarousel.STORE.value else TypeCarousel.BRAND.value)
                putExtra(KEY_ARGUMENT, it?.copy(
                    brand = null, products = it.products?.copyArrProd(),
                    brand_name = it.brand?.brand_name
                ))
            },
            GOTO_ACTIVITY_STORY
        )
        activity?.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }
    private lateinit var scrollListener: RecyclerView.OnScrollListener
    private lateinit var listenerScrollFab: RecyclerView.OnScrollListener
    private var listTagChoose: ArrayList<Int>? = null
    private var scrollHandler : ScrollHandler? = null
    private var isStartActivity = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity() as? MainActivity)?.isShowLoading(true)
        homeViewModel.getAllStories(StoryParam())
    }

    override fun onResume() {
        super.onResume()
        isStartActivity = false
    }

    override fun bindEvent() {
        scrollHandler = ScrollHandler(viewBinding.rvStories)

        viewBinding.swipeRf.setOnRefreshListener {
            homeViewModel.refreshStories()
            viewBinding.rvStories.addOnScrollListener(scrollListener)
        }

        scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (!recyclerView.canScrollVertically(1)) {
                    loadMoreView.visibility = View.VISIBLE
                }
            }

        }

        listenerScrollFab = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if ((recyclerView.layoutManager as? SpannedGridLayoutManager)?.firstVisiblePosition != 0) {
                    viewBinding.btnFab.visible()
                } else {
                    if (viewBinding.btnFab.isVisible) {
                        viewBinding.appBarLayout.setExpanded(true, true)
                    }
                    viewBinding.btnFab.gone()
                }
            }
        }

        viewBinding.refine.root.setOnClickListener {
            navController.navigateAnimation(
                R.id.fragment_refine_home,
                bundle = bundleOf(KEY_ARGUMENT to listTagChoose.isNullOrEmpty())
            )
        }

        viewBinding.btnFab.setOnClickListener {
            scrollHandler?.runHandler()
        }

        setUpToolBar()
        setUpRv()
    }

    override fun bindViewModel() {
        homeViewModel.storyLiveData.observe(this, Observer {
            homePagingAdapter.submitList(it)
            viewBinding.swipeRf.isRefreshing = false
        })

        homeViewModel.networkLoadMore.observe(this, Observer {
            if (it == false) {
                loadMoreView.visibility = View.GONE
            }
        })

        homeViewModel.networkState.observe(this, Observer {
            (requireActivity() as? MainActivity)?.isShowLoading(it)
        })

        homeViewModel.error.observe(this, Observer {
            if (homePagingAdapter.currentList?.isEmpty() == false) {
                viewBinding.rvStories.removeOnScrollListener(scrollListener)
            } else {
                (activity as? MainActivity)?.isShowErrorNetwork(true)
            }
        })

        homeViewModel.listTagChoose.observe(this, Observer {
            if (listTagChoose != it) {
                listTagChoose = it
                homeViewModel.getAllStories(StoryParam(tags = it))
                (activity as? MainActivity)?.isShowLoading(true)
                viewBinding.rvStories.addOnScrollListener(scrollListener)
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when{
            requestCode == GOTO_ACTIVITY_STORY && resultCode == Activity.RESULT_OK -> {
                (activity as? MainActivity)?.goToShopFromWishList()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        scrollHandler?.removeHandler()
    }

    private fun setUpToolBar() {
        (requireActivity() as? MainActivity)?.apply {
            initToolBar(
                viewBinding.layoutToolbar.toolbar,
                hasBack = false,
                hasBackRight = false,
                hasLeft = false,
                hasRight = false
            )
            setupToolbar(viewBinding.layoutToolbar.toolbar)
        }
        activity?.btnNav?.visibility = View.VISIBLE
    }

    private fun setUpRv() {
        val spanned = SpannedGridLayoutManager(
            orientation = SpannedGridLayoutManager.Orientation.VERTICAL,
            spans = 3
        ).apply {
            itemOrderIsStable = true

            spanSizeLookup = SpannedGridLayoutManager.SpanSizeLookup { position ->
                return@SpanSizeLookup when {
                    (position % 6 == 1 && position / 6 % 2 == 0) -> {
                        SpanSize(2, 2)
                    }
                    position % 6 == 0 && position / 6 % 2 == 1 -> {
                        SpanSize(2, 2)
                    }
                    else -> {
                        SpanSize(1, 1)
                    }
                }
            }
        }

        viewBinding.rvStories.apply {
            layoutManager = spanned
            this.adapter = homePagingAdapter
            addOnScrollListener(listenerScrollFab)
            addOnScrollListener(scrollListener)
            addItemDecoration(SpaceItemDecorator(2, 2, 2, 2))
        }
    }
}