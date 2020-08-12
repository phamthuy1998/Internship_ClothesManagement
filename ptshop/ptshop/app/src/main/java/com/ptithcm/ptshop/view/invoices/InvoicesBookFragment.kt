package com.ptithcm.ptshop.view.invoices

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import com.ptithcm.core.CoreApplication
import com.ptithcm.core.model.Invoice
import com.ptithcm.core.model.ShoppingAddress
import com.ptithcm.core.vo.Result
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.base.BaseActivity
import com.ptithcm.ptshop.base.BaseFragment
import com.ptithcm.ptshop.constant.ERROR_CODE_404
import com.ptithcm.ptshop.constant.KEY_ARGUMENT
import com.ptithcm.ptshop.constant.KEY_IS_CHOOSE_ADDRESS
import com.ptithcm.ptshop.databinding.FragmentAddressBookBinding
import com.ptithcm.ptshop.databinding.FragmentInvoicesBookBinding
import com.ptithcm.ptshop.ext.*
import com.ptithcm.ptshop.view.MainActivity
import com.ptithcm.ptshop.view.addressbook.adapter.AddressAdapter
import com.ptithcm.ptshop.viewmodel.ListenerViewModel
import com.ptithcm.ptshop.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class InvoicesBookFragment : BaseFragment<FragmentInvoicesBookBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_invoices_book

    private val userViewModel: UserViewModel by viewModel()

    private val adapter = InvoicesPagedAdapter { it: Invoice?, pos: Int? -> }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userViewModel.getPagingInvoices()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.btnNav?.visibility = View.GONE
        (activity as? BaseActivity<*>)?.isShowLoading(true)

        setupToolbar()
        initAdapter()
    }

    private fun initAdapter() {
        viewBinding.rvInvoices.adapter = adapter
        viewBinding.swlRefresh.setOnRefreshListener {
            userViewModel.getPagingInvoices()
        }
    }

    override fun bindViewModel() {
        userViewModel.invoicesLiveData.observe(this, Observer {
            adapter.submitList(it)
        })

        userViewModel.invoiceLoadStatusX.observe(this, Observer {
            adapter.setNetworkState(it)
            when (it) {
                is Result.Loading -> viewBinding.layoutLoading.visible()
                is Result.Error -> {
                    if (adapter.currentList?.isEmpty() == true) {
                        (requireActivity() as? BaseActivity<*>)?.isShowErrorNetwork(true)
                    }
                }
                else -> viewBinding.layoutLoading.gone()
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
                getString(R.string.invoice_book).capitalize()
            )
        }
    }
}