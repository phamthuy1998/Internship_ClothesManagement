package com.ptithcm.ptshop.view.shop

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.base.BaseFragment
import com.ptithcm.ptshop.databinding.FragmentShopBinding
import com.ptithcm.ptshop.ext.*
import com.ptithcm.ptshop.view.MainActivity
import com.ptithcm.ptshop.view.shop.adapter.ShopViewPagerAdapter
import com.ptithcm.ptshop.viewmodel.CarouselViewModel
import kotlinx.android.synthetic.main.layout_toolbar.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShopFragment : BaseFragment<FragmentShopBinding>() {

    override val layoutId: Int = R.layout.fragment_shop

    private val carouselViewModel: CarouselViewModel by viewModel()
    private lateinit var adapter: ShopViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        carouselViewModel.getCarousels()
    }

    override fun bindEvent() {
        super.bindEvent()
        setupToolbar()
        initViewPager()
    }

    override fun bindViewModel() {
        super.bindViewModel()
        carouselViewModel.carouselLiveData.observe(this, Observer {
            Log.d("annna", it.toString())
        })
        carouselViewModel.errorCarousel.observe(this, Observer {
            (requireActivity() as? MainActivity)?.isShowErrorNetwork(true)
        })
    }

    private fun initViewPager() {
        adapter = ShopViewPagerAdapter(childFragmentManager)
        viewBinding.viewPager.offscreenPageLimit = ShopViewPagerAdapter.PAGE_NUMBER
        viewBinding.viewPager.adapter = adapter
        viewBinding.tabLayout.setupWithViewPager(viewBinding.viewPager)
        viewBinding.tabLayout.getTabAt(0)?.text = getString(R.string.women)
        viewBinding.tabLayout.getTabAt(1)?.text = getString(R.string.men)
//        viewBinding.tabLayout.getTabAt(2)?.text = getString(R.string.kids)
        viewBinding.tabLayout.getTabAt(2)?.text = getString(R.string.unisex)
    }

    private fun setupToolbar() {
        (requireActivity() as? MainActivity)?.apply {
            viewBinding.btnNav.visible()
            initToolBar(viewBinding.layoutToolbar.toolbar, false)
            viewBinding.layoutToolbar.toolbar.tvTitleToolbar.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_flash, 0, 0, 0)
            setupToolbar(viewBinding.layoutToolbar.toolbar, getString(R.string.shop),
                messageQueue = {
                    when (it.id) {
                        R.id.ivRight, R.id.tvCount -> {
                            navController.navigateAnimation(
                                R.id.nav_shopping_card,
                                isBotToTop = true
                            )
                        }
                        R.id.ivLeft -> {
                            navController.navigateAnimation(R.id.nav_search)
                        }
                    }
                }
            )
        }
    }

}