package com.ptithcm.ptshop.view.addressbook

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import com.ptithcm.core.model.ShoppingAddress
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.base.BaseActivity
import com.ptithcm.ptshop.base.BaseFragment
import com.ptithcm.ptshop.constant.ERROR_CODE_404
import com.ptithcm.ptshop.constant.KEY_ARGUMENT
import com.ptithcm.ptshop.databinding.FragmentAddressBookBinding
import com.ptithcm.ptshop.ext.*
import com.ptithcm.ptshop.view.MainActivity
import com.ptithcm.ptshop.view.addressbook.adapter.AddressAdapter
import com.ptithcm.ptshop.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddressBookFragment : BaseFragment<FragmentAddressBookBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_address_book

    private val userViewModel: UserViewModel by viewModel()

    private val adapter = AddressAdapter { it: ShoppingAddress?, pos: Int? ->
        if (pos == null) {
            navController.navigateAnimation(
                R.id.shippingAddressDetailFragment,
                bundle = bundleOf(KEY_ARGUMENT to it)
            )
        } else {
            userViewModel.deleteAddress(it?.id)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userViewModel.getAllAddress()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.btnNav?.visibility = View.GONE
        setupToolbar()
        initAdapter()
    }

    private fun initAdapter() {
        viewBinding.rvAddress.adapter = adapter
        viewBinding.swlRefresh.setOnRefreshListener {
            userViewModel.getAllAddress()
        }
    }

    override fun bindViewModelOnce() {
        super.bindViewModelOnce()
        userViewModel.allAddressLiveData.observe(this, Observer {
            adapter.submitList(it)
            viewBinding.swlRefresh.isRefreshing = false
        })

        userViewModel.updateAddressResultLiveData.observe(this, Observer {
            messageHandler?.runMessageHandler(it)
            adapter.currentList.removeAt(adapter.currentPosition)
            adapter.notifyItemRemoved(adapter.currentPosition)
        })

        userViewModel.isLoading.observe(this, Observer {
            (activity as? BaseActivity<*>)?.isShowLoading(it)
        })

        userViewModel.error.observe(this, Observer {
            if (it.second == ERROR_CODE_404) {
                (requireActivity() as? MainActivity)?.isShowErrorNetwork(true)
            } else {
                messageHandler?.runMessageHandler(it.first)
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
                getString(R.string.address_book).capitalize()
            )
        }
    }
}