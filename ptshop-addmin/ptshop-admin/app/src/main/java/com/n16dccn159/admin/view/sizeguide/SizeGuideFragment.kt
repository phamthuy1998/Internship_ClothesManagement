package com.n16dccn159.admin.view.sizeguide

import com.n16dccn159.admin.R
import com.n16dccn159.admin.base.BaseActivity
import com.n16dccn159.admin.base.BaseFragment
import com.n16dccn159.admin.databinding.FragmentSizeGuideBinding
import com.n16dccn159.admin.ext.initToolbar
import com.n16dccn159.admin.ext.setupToolbar
import com.n16dccn159.admin.view.sizeguide.adapter.SizeGuideViewPagerAdapter

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