package com.sg.snapshop.view.carousel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TabHost.TabSpec
import androidx.lifecycle.Observer
import com.sg.core.CoreApplication
import com.sg.core.model.Brand
import com.sg.core.model.Carousel
import com.sg.core.model.Gender
import com.sg.core.model.TypeCarousel
import com.sg.core.util.ObjectHandler
import com.sg.snapshop.R
import com.sg.snapshop.base.BaseActivity
import com.sg.snapshop.base.BaseFragment
import com.sg.snapshop.constant.KEY_ARGUMENT
import com.sg.snapshop.databinding.FragmentCarouselDetailBinding
import com.sg.snapshop.ext.*
import com.sg.snapshop.view.MainActivity
import com.sg.snapshop.view.carousel.adapter.CarouselViewPagerAdapter
import com.sg.snapshop.view.home.StoryDetailActivity
import com.sg.snapshop.viewmodel.CarouselDetailViewModel
import kotlinx.android.synthetic.main.fragment_carousel_detail.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class CarouselDetailFragment : BaseFragment<FragmentCarouselDetailBinding>(), View.OnClickListener {

    override val layoutId: Int = R.layout.fragment_carousel_detail

    private val viewModel: CarouselDetailViewModel by viewModel()
    private var carousel: Carousel? = null
    private var brand: Brand? = null
    private var adapter: CarouselViewPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        carousel = arguments?.getParcelable(KEY_ARGUMENT)
        requestBrand()
        initBindViewModel()
    }

    override fun bindEvent() {
        super.bindEvent()
        viewBinding.brand = brand
        viewBinding.isStore = carousel?.type == TypeCarousel.STORE.value
        setupToolbar()
        initViewPager()
        initTabLayout(carousel?.type)
        initEvent()
    }

    override fun bindViewModel() {
        super.bindViewModel()

        viewModel.networkState.observe(this, Observer {
            (requireActivity() as? BaseActivity<*>)?.isShowLoading(it)
            if (!it) {
                (requireActivity() as? MainActivity)?.apply {
                    viewBinding.layoutToolbar.toolbar.gone()
                    transparentStatusBar(true)
                }
                (requireActivity() as? StoryDetailActivity)?.apply {
                    viewBinding.layoutToolbar.toolbar.gone()
                    transparentStatusBar(true)
                }
            }
        })

        viewModel.error.observe(this, Observer {
                (requireActivity() as? BaseActivity<*>)?.isShowErrorNetwork(true)
        })
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.ivBack -> {
                navController.popBackStack()
            }
            R.id.ivLeft -> {
                navController.navigateAnimation(R.id.nav_search)
            }
            R.id.ivRight -> {
                navController.navigateAnimation(R.id.nav_shopping_card, isBotToTop = true)
            }

        }
    }

    private fun setupToolbar() {
        (requireActivity() as? MainActivity)?.viewBinding?.btnNav?.gone()
        if (carousel?.type == TypeCarousel.BRAND.value) {
            viewBinding.collapsingToolbarLayout.title = brand?.name
        } else {
            viewBinding.collapsingToolbarLayout.title = brand?.brand_name?.toUpperCase(Locale.ROOT)
        }
        val countBags =ObjectHandler.getNumberItem()
        viewBinding.toolbar.tvCount?.apply {
            if (CoreApplication.instance.profile?.user?.brand != null) this.gone()
            when {
                countBags > 0 -> text = countBags.toString()
                else -> this.gone()
            }
        }
    }

    private fun initEvent() {
        viewBinding.ivBack.setOnClickListener(this)
        if (CoreApplication.instance.profile?.user?.brand != null) {
            viewBinding.ivLeft.gone()
            viewBinding.ivRight.gone()
        } else {
            viewBinding.ivLeft.visible()
            viewBinding.ivRight.visible()
            viewBinding.ivLeft.setOnClickListener(this)
            viewBinding.ivRight.setOnClickListener(this)
        }

    }

    private fun initBindViewModel() {
        viewModel.vendorBrandLiveData.observe(this, Observer {
            it?.let {
                this.brand = it
                viewBinding.collapsingToolbarLayout.title = it.name
                viewModel.brandLiveData.postValue(
                    Triple(
                        it,
                        if (carousel?.isDesigner == true) Gender.NONE else switchGender(carousel?.gender),
                        switchTypeCarousel(carousel?.type)
                    )
                )
            }
        })

        viewModel.storeBrandLiveData.observe(this, Observer {
            it?.let {
                viewBinding.collapsingToolbarLayout.title = it.brand_name?.toUpperCase(Locale.ROOT)
                viewBinding.brand = it
                this.brand = it
                it.store_id = carousel?.storeId
                it.id = carousel?.brand_id
                it.name = carousel?.name
                viewModel.storiesLiveData.postValue(
                    Triple(it, switchGender(carousel?.gender), TypeCarousel.STORE)
                )
            }
        })
    }

    private fun requestBrand() {
        if (carousel?.type == TypeCarousel.BRAND.value) {
            viewModel.getVendorBrand(carousel?.brand_id)
        } else if (carousel?.type == TypeCarousel.STORE.value) {
            viewModel.getStoreBrand(carousel?.storeId)
        }
    }

    private fun initViewPager() {
        adapter = CarouselViewPagerAdapter(carousel?.type, childFragmentManager)
        viewBinding.viewPager.offscreenPageLimit = CarouselViewPagerAdapter.PAGE_ALL_NUMBER
        viewBinding.viewPager.adapter = adapter
    }

    private fun initTabLayout(type: String?) {
        viewBinding.tabLayout.setupWithViewPager(viewBinding.viewPager)
        viewBinding.tabLayout.getTabAt(0)?.text = getString(R.string.product)
        viewBinding.tabLayout.getTabAt(1)?.text = getString(R.string.stories)
        viewBinding.tabLayout.getTabAt(2)?.text = getString(R.string.about)
        when (type) {
            TypeCarousel.BRAND.value -> {
                viewBinding.tabLayout.setSelectedTabIndicatorHeight(0)
            }
        }
    }

}