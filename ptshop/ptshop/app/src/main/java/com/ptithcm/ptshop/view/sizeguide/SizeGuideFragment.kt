package com.ptithcm.ptshop.view.sizeguide

import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.base.BaseActivity
import com.ptithcm.ptshop.base.BaseFragment
import com.ptithcm.ptshop.databinding.FragmentSizeGuideBinding
import com.ptithcm.ptshop.ext.initToolbar
import com.ptithcm.ptshop.ext.setupToolbar
import com.ptithcm.ptshop.view.sizeguide.adapter.SizeGuideViewPagerAdapter

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