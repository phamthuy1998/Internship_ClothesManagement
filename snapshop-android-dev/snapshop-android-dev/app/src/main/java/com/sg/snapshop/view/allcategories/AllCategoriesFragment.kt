package com.sg.snapshop.view.allcategories

import androidx.viewpager.widget.ViewPager
import com.sg.snapshop.R
import com.sg.snapshop.base.BaseFragment
import com.sg.snapshop.constant.KEY_ARGUMENT
import com.sg.snapshop.databinding.FragmentAllCategoriesBinding
import com.sg.snapshop.ext.initToolBar
import com.sg.snapshop.ext.navigateAnimation
import com.sg.snapshop.ext.setupToolbar
import com.sg.snapshop.view.MainActivity
import com.sg.snapshop.view.allcategories.adapter.AllCategoriesPagerAdapter

class AllCategoriesFragment : BaseFragment<FragmentAllCategoriesBinding>(),
    ViewPager.OnPageChangeListener {

    override val layoutId: Int = R.layout.fragment_all_categories

    private lateinit var adapter: AllCategoriesPagerAdapter

    override fun bindEvent() {
        super.bindEvent()
        initView()
        initViewPager()
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        (adapter.getItem(position) as? BaseAllCategoriesFragment)?.reloadPage()
    }

    private fun initView() {
        (requireActivity() as? MainActivity)?.apply {
            initToolBar(viewBinding.layoutToolbar.toolbar, hasBackRight = false)
            setupToolbar(
                viewBinding.layoutToolbar.toolbar, getString(R.string.all_categories), messageQueue = {
                    when (it.id) {
                        R.id.ivRight, R.id.tvCount -> {
                            navController.navigateAnimation(R.id.nav_shopping_card, isBotToTop = true)
                        }
                        R.id.ivLeft -> {
                            navController.navigateAnimation(R.id.nav_search)
                        }
                    }
                })
        }
    }

    private fun initViewPager() {
        adapter = AllCategoriesPagerAdapter(childFragmentManager)
        viewBinding.viewPager.offscreenPageLimit = AllCategoriesPagerAdapter.PAGE_NUMBER
        viewBinding.viewPager.adapter = adapter
        viewBinding.tabLayout.setupWithViewPager(viewBinding.viewPager)
        viewBinding.tabLayout.getTabAt(0)?.text = getString(R.string.women)
        viewBinding.tabLayout.getTabAt(1)?.text = getString(R.string.men)
        viewBinding.tabLayout.getTabAt(2)?.text = getString(R.string.kids)
        viewBinding.tabLayout.getTabAt(3)?.text = getString(R.string.unisex)
        viewBinding.viewPager.currentItem = arguments?.getInt(KEY_ARGUMENT) ?: 0
        viewBinding.viewPager.addOnPageChangeListener(this)
    }

}