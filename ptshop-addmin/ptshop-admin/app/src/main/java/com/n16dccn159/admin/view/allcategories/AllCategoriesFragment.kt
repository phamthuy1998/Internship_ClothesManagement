package com.n16dccn159.admin.view.allcategories

import androidx.viewpager.widget.ViewPager
import com.n16dccn159.admin.R
import com.n16dccn159.admin.base.BaseFragment
import com.n16dccn159.admin.constant.KEY_ARGUMENT
import com.n16dccn159.admin.databinding.FragmentAllCategoriesBinding
import com.n16dccn159.admin.ext.initToolBar
import com.n16dccn159.admin.ext.navigateAnimation
import com.n16dccn159.admin.ext.setupToolbar
import com.n16dccn159.admin.view.MainActivity
import com.n16dccn159.admin.view.allcategories.adapter.AllCategoriesPagerAdapter

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