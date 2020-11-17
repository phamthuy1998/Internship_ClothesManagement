package com.ptithcm.admin.view.invoices

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import com.ptithcm.core.model.Invoice
import com.ptithcm.core.vo.Result
import com.ptithcm.admin.R
import com.ptithcm.admin.base.BaseActivity
import com.ptithcm.admin.base.BaseFragment
import com.ptithcm.admin.constant.KEY_ARGUMENT
import com.ptithcm.admin.databinding.FragmentInvoicesBookBinding
import com.ptithcm.admin.ext.*
import com.ptithcm.admin.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class InvoicesBookFragment : BaseFragment<FragmentInvoicesBookBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_invoices_book

    private val userViewModel: UserViewModel by viewModel()

    private val mActivity: BaseActivity<*> by lazy {
        requireActivity() as BaseActivity<*>
    }

    companion object {
        fun newInstance(invoiceId: Int): InvoicesBookFragment = InvoicesBookFragment().apply {
            arguments = Bundle().apply {
                putInt("invoiceId", invoiceId)
            }
        }
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
        arguments?.let {
            invoiceStatusId = it.getInt("invoiceId", 0)
            userViewModel.getPagingInvoices(statusId = invoiceStatusId ?: 0)
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mActivity.isShowLoading(true)
//        setupToolbar()
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
            viewBinding.swlRefresh.isRefreshing = false
            mActivity.isShowLoading(false)
            if (it != null && it.size != 0) {
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

}