package com.ptithcm.admin.view.invoices

import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.ptithcm.admin.R
import com.ptithcm.admin.base.BaseFragment
import com.ptithcm.admin.databinding.FragmentInvoicePagerBinding
import com.ptithcm.admin.ext.initToolBar
import com.ptithcm.admin.ext.setupToolbar
import com.ptithcm.admin.view.MainActivity

class InvoicePagerFragment : BaseFragment<FragmentInvoicePagerBinding>(),
    ViewPager.OnPageChangeListener {
    override val layoutId: Int
        get() = R.layout.fragment_invoice_pager

    private lateinit var adapter: InvoicePagerAdapter

    private var pageSelected = 0
    override fun bindEvent() {
        super.bindEvent()
        setupToolbar()
        val bundle = this.arguments
        if (bundle != null) {
            pageSelected = bundle.getInt("invoiceId", 0)
        }
        initViewPager()
    }

    private fun initViewPager() {
        adapter = InvoicePagerAdapter(childFragmentManager, arrayListOf())
        adapter.addFragment(InvoicesBookFragment.newInstance(0))
        adapter.addFragment(InvoicesBookFragment.newInstance(1))
        adapter.addFragment(InvoicesBookFragment.newInstance(2))
        adapter.addFragment(InvoicesBookFragment.newInstance(3))
        adapter.addFragment(InvoicesBookFragment.newInstance(4))
        viewBinding.viewPager.offscreenPageLimit =  adapter.count
        viewBinding.viewPager.adapter = adapter
        viewBinding.tabLayout.tabMode = TabLayout.MODE_SCROLLABLE
        viewBinding.tabLayout.setupWithViewPager(viewBinding.viewPager)
        viewBinding.tabLayout.getTabAt(0)?.text = getString(R.string.all_invoices)
        viewBinding.tabLayout.getTabAt(1)?.text = getString(R.string.has_received)
        viewBinding.tabLayout.getTabAt(2)?.text = getString(R.string.shipping)
        viewBinding.tabLayout.getTabAt(3)?.text = getString(R.string.shipped)
        viewBinding.tabLayout.getTabAt(4)?.text = getString(R.string.canceled)
        viewBinding.viewPager.currentItem = pageSelected
        viewBinding.viewPager.addOnPageChangeListener(this)
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        adapter.getItem(position)
    }

    private fun setTitleToolbar(pos: Int){
        val title= when (pos) {
            0 -> getString(R.string.all_invoices)
            1 -> getString(R.string.has_received)
            2 -> getString(R.string.shipping)
            3 -> getString(R.string.shipped)
            4 -> getString(R.string.canceled)
            else -> return
        }

        (requireActivity() as? MainActivity)?.apply {
            setupToolbar(
                viewBinding.layoutToolbar.toolbar,
                title ?: ""
            )
        }
    }

    private fun setupToolbar() {
        (requireActivity() as? MainActivity)?.apply {
            initToolBar(
                viewBinding.layoutToolbar.toolbar, false,
                hasBackRight = false,
                hasLeft = false,
                hasRight = false
            )
            setupToolbar(
                viewBinding.layoutToolbar.toolbar,
                getString(R.string.invoiceTitle)
            )
        }
    }


    override fun onPageScrollStateChanged(state: Int) {

    }
}