package com.ptithcm.ptshop.view.invoices

import android.view.View
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.base.BaseFragment
import com.ptithcm.ptshop.databinding.FragmentInvoicePagerBinding
import kotlinx.android.synthetic.main.activity_main.*

class InvoicePagerFragment : BaseFragment<FragmentInvoicePagerBinding>(),
    ViewPager.OnPageChangeListener {
    override val layoutId: Int
        get() = R.layout.fragment_invoice_pager

    private lateinit var adapter: InvoicePagerAdapter

    private var pageSelected = 0
    override fun bindEvent() {
        super.bindEvent()

        val bundle = this.arguments
        if (bundle != null) {
            pageSelected = bundle.getInt("invoiceId", 0)
        }
        initViewPager()
    }

    override fun onResume() {
        super.onResume()
        activity?.btnNav?.visibility = View.GONE
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

    override fun onPageScrollStateChanged(state: Int) {

    }
}