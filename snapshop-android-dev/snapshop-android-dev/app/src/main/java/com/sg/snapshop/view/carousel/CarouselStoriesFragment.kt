package com.sg.snapshop.view.carousel

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.sg.core.model.Brand
import com.sg.core.model.Stories
import com.sg.core.model.TypeCarousel
import com.sg.snapshop.R
import com.sg.snapshop.base.BaseFragment
import com.sg.snapshop.constant.*
import com.sg.snapshop.databinding.FragmentCarouselStoriesBinding
import com.sg.snapshop.ext.copyArrProd
import com.sg.snapshop.ext.gone
import com.sg.snapshop.ext.runAnimationAlpha
import com.sg.snapshop.ext.visible
import com.sg.snapshop.view.home.HomePagingAdapter
import com.sg.snapshop.view.home.StoryDetailActivity
import com.sg.snapshop.viewmodel.CarouselDetailViewModel
import com.sg.spannedgridlayout.SpanSize
import com.sg.spannedgridlayout.SpannedGridLayoutManager
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CarouselStoriesFragment(private val isFromBrandProfile: Boolean = false) : BaseFragment<FragmentCarouselStoriesBinding>(),
    View.OnClickListener {

    override val layoutId: Int = R.layout.fragment_carousel_stories

    private val viewModel: CarouselDetailViewModel by sharedViewModel(from = { requireParentFragment() })
    private var vendorBrand: Brand? = null
    private lateinit var adapter: HomePagingAdapter
    private var canScrolling = true
    private lateinit var scrollListener: RecyclerView.OnScrollListener
    private lateinit var scrollMoreListener: RecyclerView.OnScrollListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.storiesLiveData.observe(this, Observer {
            vendorBrand = it.first
            viewModel.getPagingStoresCarousel(it.first.store_id)
        })
    }

    override fun bindEvent() {
        super.bindEvent()
        initScroll()
        initAdapter()
        viewBinding.btnFab.setOnClickListener(this)
    }

    override fun bindViewModel() {
        super.bindViewModel()

        viewModel.storesCarouselLiveData.observe(this, Observer { list ->
            adapter.submitList(list)
            if (adapter.itemCount == 0) viewBinding.emptyContainer.visible() else viewBinding.emptyContainer.gone()
        })

        viewModel.networkState.observe(this, Observer {
            if (!it) {
                adapter.setNetworkState(it)
            }
        })

        viewModel.errorStorePaging.observe(this, Observer {
            if (adapter.currentList?.isEmpty() == false) {
                canScrolling = false
                adapter.removeLoading()
                viewBinding.rvStories.removeOnScrollListener(scrollMoreListener)
            }
        })
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.btnFab -> {
                viewBinding.rvStories.smoothScrollToPosition(0)
                viewBinding.btnFab.runAnimationAlpha(false)
            }
        }
    }

    private fun initScroll() {
        var isRunning = false
        scrollMoreListener = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (!recyclerView.canScrollVertically(1)) {
                    adapter.setNetworkState(true)
                }
            }
        }

        scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val position =
                    (viewBinding.rvStories.layoutManager as? SpannedGridLayoutManager)?.firstVisiblePosition
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

    private fun initAdapter() {
        val spanned = SpannedGridLayoutManager(
            orientation = SpannedGridLayoutManager.Orientation.VERTICAL,
            spans = 3
        ).apply {
            itemOrderIsStable = true

            spanSizeLookup = SpannedGridLayoutManager.SpanSizeLookup { position ->
                return@SpanSizeLookup when {
                    this.itemCount - 1 == position -> {
                        if (canScrolling)
                            SpanSize(3, 1)
                        else
                            SpanSize(1, 1)
                    }
                    position == itemCount -> {
                        SpanSize(1, 0)
                    }
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
        adapter = HomePagingAdapter(this::listener)
        viewBinding.rvStories.adapter = adapter
        viewBinding.rvStories.apply {
            layoutManager = spanned
            addOnScrollListener(scrollListener)
            addOnScrollListener(scrollMoreListener)
        }
    }

    private fun listener(item: Stories?) {
        activity?.startActivityForResult(
            Intent(activity, StoryDetailActivity::class.java).apply {
                putExtra(KEY_ID, item?.brand?.id)
                putExtra(KEY_TYPE, if(item?.brand?.stores.isNullOrEmpty()) TypeCarousel.STORE.value else TypeCarousel.BRAND.value)
                putExtra(KEY_ARGUMENT, item?.copy(
                    brand = null, products = item.products?.copyArrProd(),
                    brand_name = item.brand?.brand_name
                ))
                putExtra(IS_FROM_BRAND_PROFILE, isFromBrandProfile)
            },
            GOTO_ACTIVITY_STORY
        )
        activity?.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }
}