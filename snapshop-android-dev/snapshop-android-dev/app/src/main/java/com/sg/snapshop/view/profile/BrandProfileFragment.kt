package com.sg.snapshop.view.profile

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.sg.core.CoreApplication
import com.sg.core.model.Carousel
import com.sg.core.model.TypeCarousel
import com.sg.snapshop.R
import com.sg.snapshop.base.BaseActivity
import com.sg.snapshop.base.BaseFragment
import com.sg.snapshop.constant.ERROR_CODE_404
import com.sg.snapshop.databinding.FragmentCarouselDetailBinding
import com.sg.snapshop.databinding.LayoutBottomSheetBinding
import com.sg.snapshop.databinding.LayoutPopUpBinding
import com.sg.snapshop.ext.*
import com.sg.snapshop.util.PopUp
import com.sg.snapshop.view.MainActivity
import com.sg.snapshop.view.carousel.adapter.CarouselViewPagerAdapter
import com.sg.snapshop.viewmodel.AuthenticateViewModel
import com.sg.snapshop.viewmodel.CarouselDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class BrandProfileFragment : BaseFragment<FragmentCarouselDetailBinding>(), View.OnClickListener {

    override val layoutId: Int = R.layout.fragment_carousel_detail

    private val viewModel: CarouselDetailViewModel by viewModel()
    private val authViewModel: AuthenticateViewModel by viewModel()
    private var carousel: Carousel? = null
    private var adapter: CarouselViewPagerAdapter? = null
    private var brandName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val story = CoreApplication.instance.profile?.user?.brand
        carousel = Carousel(storeId = story?.id, gender = -1, type = TypeCarousel.STORE.value)
        requestBrand()
        initBindViewModel()
        observeViewModel()
    }

    private fun initBindViewModel() {
        viewModel.vendorBrandLiveData.observe(this, Observer {
            it?.let {
                brandName = it.brand_name ?: ""
                viewBinding.collapsingToolbarLayout.title = it.brand_name
                viewBinding.brand = it
                viewModel.brandLiveData.postValue(
                    Triple(it, switchGender(carousel?.gender), switchTypeCarousel(carousel?.type))
                )
            }
        })

        viewModel.storeBrandLiveData.observe(this, Observer {
            it?.let {
                viewBinding.collapsingToolbarLayout.title = it.brand_name?.toUpperCase(Locale.ROOT)
                viewBinding.brand = it
                brandName = it.brand_name ?: ""
                it.store_id = carousel?.storeId
                it.id = carousel?.brand_id
                it.name = carousel?.name
                viewModel.storiesLiveData.postValue(
                    Triple(it, switchGender(carousel?.gender), TypeCarousel.STORE)
                )
            }
        })
    }

    override fun bindEvent() {
        super.bindEvent()
        (requireActivity() as? MainActivity)?.apply {
            viewBinding.btnNav.visible()
            viewBinding.layoutToolbar.toolbar.gone()
        }
        viewBinding.ivBack.gone()
        viewBinding.ivLeft.gone()
        viewBinding.tvCount.gone()
        viewBinding.ivRight.setImageResource(R.drawable.ic_more)
        viewBinding.ivRight.setOnClickListener(this)
        viewBinding.collapsingToolbarLayout.title = brandName
        if (carousel?.type == TypeCarousel.STORE.value) {
            viewBinding.isStore = true
        }
        initViewPager()
        initTabLayout(carousel?.type)
    }

    private fun observeViewModel() {
        viewModel.networkState.observe(this, Observer {
            if (!it) {
                (requireActivity() as? MainActivity)?.isShowLoading(it)
            }
        })

        authViewModel.logOutLiveData.observe(this, Observer {
            CoreApplication.instance.clearAccount()
            Handler().postDelayed({
                (requireActivity() as? BaseActivity<*>)?.closePopup()
                (activity as? MainActivity)?.updateUIBottomNav()
                navController.popBackStack()
            }, 500)
        })
        authViewModel.error.observe(this, Observer {
            if (it.second == ERROR_CODE_404){
                (requireActivity() as? MainActivity)?.isShowErrorNetwork(true)
            } else {
                messageHandler?.runMessageErrorHandler(it.first)
            }
        })
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.ivBack -> {
                navController.popBackStack()
            }

            R.id.ivRight -> {
                showBottomSheetLogOut()
            }

            R.id.tvDelete -> {
                (requireActivity() as? BaseActivity<*>)?.closePopup()
                (requireActivity() as? BaseActivity<*>)?.showPopup(
                    PopUp(
                        R.layout.layout_pop_up,
                        messageQueue = this::popEvent
                    )
                )
            }
            R.id.btnCancel, R.id.tvCancel -> {
                (requireActivity() as? BaseActivity<*>)?.closePopup()
            }
            R.id.btnOk -> {
                (requireActivity() as? BaseActivity<*>)?.closePopup()
                authViewModel.logOut()
            }

        }
    }

    private fun requestBrand() {
        (requireActivity() as? MainActivity)?.isShowLoading(true)
        when (carousel?.type) {
            TypeCarousel.BRAND.value -> {
                viewModel.getVendorBrand(carousel?.brand_id)
            }
            TypeCarousel.STORE.value -> {
                viewModel.getStoreBrand(carousel?.storeId)
            }
            else -> {

            }
        }
    }

    private fun initViewPager() {
        adapter = CarouselViewPagerAdapter(carousel?.type, childFragmentManager, isFromBrandProfile = true)
        viewBinding.viewPager.offscreenPageLimit = CarouselViewPagerAdapter.PAGE_ALL_NUMBER
        viewBinding.viewPager.adapter = adapter
    }

    private fun initTabLayout(type: String?) {
        viewBinding.tabLayout.setupWithViewPager(viewBinding.viewPager)
        viewBinding.tabLayout.getTabAt(0)?.text = getString(R.string.product)
        when (type) {
            TypeCarousel.STORE.value, TypeCarousel.PRODUCT_LIST.value -> {
                viewBinding.tabLayout.getTabAt(1)?.text = getString(R.string.stories)
                viewBinding.tabLayout.getTabAt(2)?.text = getString(R.string.about)
            }
        }
    }

    private fun showBottomSheetLogOut() {
        (requireActivity() as? BaseActivity<*>)?.showPopup(
            PopUp(
                R.layout.layout_bottom_sheet,
                isBottomSheet = true,
                isCancelable = true,
                messageQueue = { binding: ViewDataBinding? ->
                    (binding as? LayoutBottomSheetBinding)?.apply {
                        delete = getString(R.string.log_out)
                        this.tvCancel.setOnClickListener(this@BrandProfileFragment)
                        this.tvDelete.setOnClickListener(this@BrandProfileFragment)
                        this.tvTitle.gone()
                        this.vLine1.gone()
                        this.vLine2.gone()
                        this.vLine3.gone()
                    }
                }
            )
        )
    }

    private fun popEvent(popupBinding: ViewDataBinding?) {
        (popupBinding as? LayoutPopUpBinding)?.apply {
            title = getString(R.string.continue_logout)
            left = getString(R.string.cancel)
            right = getString(R.string.logout_me_out)
            btnCancel.setOnClickListener(this@BrandProfileFragment)
            btnOk.setOnClickListener(this@BrandProfileFragment)
        }
    }

    override fun onDetach() {
        super.onDetach()
        (requireActivity() as? MainActivity)?.apply {
            viewBinding.layoutToolbar.toolbar.visible()
            transparentStatusBar(false)
        }
    }

}