package com.sg.snapshop.view.sizeguide

import com.sg.snapshop.R
import com.sg.snapshop.base.BaseActivity
import com.sg.snapshop.base.BaseFragment
import com.sg.snapshop.databinding.FragmentSizeGuideBinding
import com.sg.snapshop.ext.gone
import com.sg.snapshop.ext.initToolBar
import com.sg.snapshop.ext.initToolbar
import com.sg.snapshop.ext.setupToolbar
import com.sg.snapshop.view.MainActivity
import com.sg.snapshop.view.home.StoryDetailActivity
import com.sg.snapshop.view.sizeguide.adapter.SizeGuideViewPagerAdapter

class SizeGuideFragment : BaseFragment<FragmentSizeGuideBinding>(){
    override val layoutId: Int
        get() = R.layout.fragment_size_guide
    private lateinit var adapter: SizeGuideViewPagerAdapter
    override fun bindEvent() {

        setUpToolBar()

        initViewPager()
    }

    private fun setUpToolBar(){
        (requireActivity() as? BaseActivity<*>)?.apply {
            initToolbar(true, hasBackRight = false, hasLeft = false, hasRight = false)
            setupToolbar(getString(R.string.size_chart))
        }
    }

    private fun initViewPager() {
        adapter = SizeGuideViewPagerAdapter(childFragmentManager)
        viewBinding.viewPager.offscreenPageLimit = SizeGuideViewPagerAdapter.PAGE_NUMBER
        viewBinding.viewPager.adapter = adapter
        viewBinding.tabLayout.setupWithViewPager(viewBinding.viewPager)
        viewBinding.tabLayout.getTabAt(0)?.text = getString(R.string.women)
        viewBinding.tabLayout.getTabAt(1)?.text = getString(R.string.men)
        viewBinding.tabLayout.getTabAt(2)?.text ="KID"
    }

}