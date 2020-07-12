package com.sg.snapshop.view.uploadproduct

import android.view.ViewGroup
import com.sg.snapshop.R
import com.sg.snapshop.base.BaseFragment
import com.sg.snapshop.databinding.FragmentUploadProductBinding
import com.sg.snapshop.ext.initToolBar
import com.sg.snapshop.ext.setupToolbar
import com.sg.snapshop.view.uploadproduct.adapter.UploadViewpagerAdapter
import kotlinx.android.synthetic.main.layout_toolbar_upload_product.view.*

class UploadProductFragment : BaseFragment<FragmentUploadProductBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_upload_product

    private var pagerAdapter: UploadViewpagerAdapter? = null

    override fun bindEvent() {
        super.bindEvent()
        setupToolbar()
        initViewPager()
        initTabLayout()
    }

    private fun setupToolbar() {
        (requireActivity() as? UploadProductActivity)?.apply {
            initToolBar(
                viewBinding.layoutToolbar.toolbar,
                false,
                hasBackRight = false,
                hasTextLeft = true
            )
            setupToolbar(viewBinding.layoutToolbar.toolbar, getString(R.string.upload))
            viewBinding.layoutToolbar.toolbar.tvTitleToolbar.textSize = 14f
            viewBinding.layoutToolbar.tvCancelToolbar.setOnClickListener { finish() }
        }
    }

    private fun initViewPager() {
        pagerAdapter = UploadViewpagerAdapter(childFragmentManager)
        viewBinding.viewPager.offscreenPageLimit = 1
        viewBinding.viewPager.adapter = pagerAdapter
    }

    private fun initTabLayout() {

        for (i in 0 until viewBinding.tabLayout.tabCount) {
            val tab = (viewBinding.tabLayout.getChildAt(0) as? ViewGroup)?.getChildAt(i)
            val p = tab?.layoutParams as ViewGroup.MarginLayoutParams
            p.setMargins(0, 0, 50, 0)
            tab.requestLayout()
        }

        viewBinding.tabLayout.setupWithViewPager(viewBinding.viewPager)
        viewBinding.tabLayout.getTabAt(0)?.text = getString(R.string.photo)
        viewBinding.tabLayout.getTabAt(1)?.text = getString(R.string.video)
        viewBinding.tabLayout.getTabAt(2)?.text = getString(R.string.library)
    }
}