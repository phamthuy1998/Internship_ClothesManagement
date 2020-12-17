package com.n16dccn159.admin.view.uploadproduct

import android.view.ViewGroup
import com.n16dccn159.admin.R
import com.n16dccn159.admin.base.BaseFragment
import com.n16dccn159.admin.databinding.FragmentUploadProductBinding
import com.n16dccn159.admin.ext.initToolBar
import com.n16dccn159.admin.ext.setupToolbar
import com.n16dccn159.admin.view.uploadproduct.adapter.UploadViewpagerAdapter
import kotlinx.android.synthetic.main.layout_register_toolbar.view.*

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