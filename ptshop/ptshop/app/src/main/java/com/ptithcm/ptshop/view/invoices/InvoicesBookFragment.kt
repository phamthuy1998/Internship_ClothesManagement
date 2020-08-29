package com.ptithcm.ptshop.view.invoices

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import com.ptithcm.core.model.Invoice
import com.ptithcm.core.vo.Result
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.base.BaseActivity
import com.ptithcm.ptshop.base.BaseFragment
import com.ptithcm.ptshop.constant.KEY_ARGUMENT
import com.ptithcm.ptshop.databinding.FragmentInvoicesBookBinding
import com.ptithcm.ptshop.ext.*
import com.ptithcm.ptshop.view.MainActivity
import com.ptithcm.ptshop.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class InvoicesBookFragment : BaseFragment<FragmentInvoicesBookBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_invoices_book

    private val userViewModel: UserViewModel by viewModel()

    private val mActivity: BaseActivity<*> by lazy {
        requireActivity() as BaseActivity<*>
    }

    private var invoiceTitle: String? = ""
    private var invoiceStatusId: Int? = 0

    private val adapter = InvoicesPagedAdapter { it: Invoice?, _: Int? ->
        navController.navigateAnimation(
            R.id.invoiceDetailFragment,
            bundle = bundleOf(
                KEY_ARGUMENT to it?.id
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = this.arguments
        if (bundle != null) {
            invoiceTitle = bundle.getString("invoiceTitle", "")
            invoiceStatusId = bundle.getInt("invoiceId", 0)
            userViewModel.getPagingInvoices(statusId = invoiceStatusId ?: 0)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mActivity.btnNav?.visibility = View.GONE
        mActivity.isShowLoading(true)

        setupToolbar()
        initAdapter()
    }

    private fun initAdapter() {
        viewBinding.rvInvoices.adapter = adapter
        viewBinding.swlRefresh.setOnRefreshListener {
            userViewModel.getPagingInvoices(statusId = invoiceStatusId ?: 0)
        }
    }

    override fun bindViewModel() {
        userViewModel.invoicesLiveData.observe(this, Observer {
            if (it != null && it.size != 0) {
                mActivity.isShowLoading(false)
                viewBinding.swlRefresh.isRefreshing = false
                viewBinding.tvEmpty.gone()
                adapter.submitList(it)
            } else viewBinding.tvEmpty.visible()
        })

        userViewModel.invoiceLoadStatusX.observe(this, Observer {
            adapter.setNetworkState(it)
            when (it) {
                is Result.Error -> {
                    if (adapter.currentList?.isEmpty() == true) {
                        (requireActivity() as? BaseActivity<*>)?.isShowErrorNetwork(true)
                    }
                    mActivity.isShowLoading(false)
                }
                is Result.Loading -> mActivity.isShowLoading(true)
                else -> mActivity.isShowLoading(false)
            }
        })
    }

    private fun setupToolbar() {
        (requireActivity() as? MainActivity)?.apply {
            initToolBar(
                viewBinding.layoutToolbar.toolbar, true,
                hasBackRight = false,
                hasLeft = false,
                hasRight = false
            )
            setupToolbar(
                viewBinding.layoutToolbar.toolbar,
//                getString(R.string.invoice_book).capitalize()
                invoiceTitle ?: ""
            )
        }
    }
}