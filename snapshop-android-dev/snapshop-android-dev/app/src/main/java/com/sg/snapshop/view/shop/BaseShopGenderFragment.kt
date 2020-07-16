package com.sg.snapshop.view.shop

import android.os.Bundle
import android.os.Handler
import androidx.core.os.bundleOf
import androidx.databinding.ObservableField
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.sg.core.model.Carousel
import com.sg.core.model.Category
import com.sg.core.model.Gender
import com.sg.core.model.TypeCarousel
import com.sg.snapshop.R
import com.sg.snapshop.base.BaseFragment
import com.sg.snapshop.constant.KEY_ARGUMENT
import com.sg.snapshop.databinding.FragmentBaseShopGenderBinding
import com.sg.snapshop.ext.isShowErrorNetwork
import com.sg.snapshop.ext.isShowLoading
import com.sg.snapshop.ext.navigateAnimation
import com.sg.snapshop.view.MainActivity
import com.sg.snapshop.view.shop.adapter.CarouselRecyclerViewAdapter
import com.sg.snapshop.view.shop.adapter.CategoriesRecyclerViewAdapter
import com.sg.snapshop.viewmodel.CarouselViewModel
import com.sg.snapshop.viewmodel.ShopViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

abstract class BaseShopGenderFragment : BaseFragment<FragmentBaseShopGenderBinding>() {

    override val layoutId: Int = R.layout.fragment_base_shop_gender

    abstract fun getShopType(): Gender

    val curCarousel: ObservableField<String> = ObservableField("1")

    private val viewModel: ShopViewModel by viewModel()
    private val carouselViewModel: CarouselViewModel by sharedViewModel(from = { requireParentFragment() })
    private lateinit var carouselsAdapter: CarouselRecyclerViewAdapter
    private lateinit var categoriesAdapter: CategoriesRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        curCarousel.set("1")
        viewModel.getMainCategories(getShopType().value)
    }

    override fun bindEvent() {
        super.bindEvent()
        viewBinding.fragment = this
        initAdapter()
        initCategoriesAdapter()
    }

    override fun bindViewModel() {
        super.bindViewModel()
        carouselViewModel.carouselLiveData.observe(this, Observer {
            initProgressLine(it.count)
            carouselsAdapter.setCarousels(it.results)
        })

        viewModel.networkState.observe(this, Observer {
            when (it) {
                true -> (requireActivity() as? MainActivity)?.isShowLoading(it)
                false -> {
                    Handler().postDelayed({
                        (requireActivity() as? MainActivity)?.isShowLoading(it)
                    }, 3000)
                }
            }
        })

        viewModel.categoriesLiveData.observe(this, Observer {
            if (it != null) {
                categoriesAdapter.setCategories(it, getShopType())
            }
        })

        viewModel.error.observe(this, Observer {
            (requireActivity() as? MainActivity)?.isShowErrorNetwork(true)
        })
    }

    private fun initAdapter() {
        carouselsAdapter = CarouselRecyclerViewAdapter(arrayListOf(), this::listenerCarousel)
        viewBinding.rvCarousel.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        viewBinding.rvCarousel.adapter = carouselsAdapter
        val snapHelperCenter = LinearSnapHelper()
        snapHelperCenter.attachToRecyclerView(viewBinding.rvCarousel)
        viewBinding.rvCarousel.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                handleProgressLine(recyclerView, snapHelperCenter)
            }
        })
    }

    private fun initCategoriesAdapter() {
        categoriesAdapter =
            CategoriesRecyclerViewAdapter(arrayListOf(), getShopType(), this::listenerCategories)
        val layoutManager = GridLayoutManager(requireContext(), 3)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val category = categoriesAdapter.getCategories()?.get(position)
                if (category?.isSection == false) {
                    return 1
                }
                return category?.countSection ?: 3
            }
        }
        viewBinding.rvPopularCategories.layoutManager = layoutManager
        viewBinding.rvPopularCategories.adapter = categoriesAdapter
    }

    private fun initProgressLine(count: Int?) {
        viewBinding.carouselsSize = count.toString()
        viewBinding.progressBarLine.max = count ?: 0
        viewBinding.progressBarLine.progress = curCarousel.get()?.toInt() ?: 1
    }

    private fun handleProgressLine(recyclerView: RecyclerView, snapHelperCenter: LinearSnapHelper) {
        val curView =
            snapHelperCenter.findSnapView(recyclerView.layoutManager) ?: recyclerView.rootView
        val pos = recyclerView.layoutManager?.getPosition(curView) ?: 0
        viewBinding.progressBarLine.progress = pos + 1
        curCarousel.set((pos + 1).toString())
    }

    private fun listenerCarousel(carousel: Carousel?) {
        if (carousel?.type != TypeCarousel.PRODUCT_LIST.value) {
            navController.navigateAnimation(
                R.id.nav_carousel_detail,
                bundle = bundleOf(KEY_ARGUMENT to carousel)
            )
        } else {
//            navController.navigateAnimation(
//                R.id.nav_categories_detail, bundle = bundleOf(
//                    KEY_ARGUMENT to carousel.copyProductParam(),
//                    IS_PRODUCT to true,
//                    KEY_BANNER_CATEGORY to categoriesAdapter.getCategories()?.getBannerCategory(
//                        carousel.main_categories?.first(),
//                        switchGender(carousel.gender)
//                    )
//                )
//            )
        }
    }


    // On click item category
    private fun listenerCategories(category: Category?) {
//        navController.navigateAnimation(
//            R.id.nav_categories_detail, bundle =
//            bundleOf(
//                KEY_ARGUMENT to category?.copyCategoryParam(getShopType()),
//                KEY_BANNER_CATEGORY to categories?.images?.first { it.gender == getShopType().value }?.banner_image_url
//            )
//        )
    }
}