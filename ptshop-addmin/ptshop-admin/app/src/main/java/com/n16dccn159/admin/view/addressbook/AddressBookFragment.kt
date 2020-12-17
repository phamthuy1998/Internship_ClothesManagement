package com.n16dccn159.admin.view.addressbook

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import com.n16dccn159.core.CoreApplication
import com.n16dccn159.core.model.ShoppingAddress
import com.n16dccn159.admin.R
import com.n16dccn159.admin.base.BaseActivity
import com.n16dccn159.admin.base.BaseFragment
import com.n16dccn159.admin.constant.ERROR_CODE_404
import com.n16dccn159.admin.constant.KEY_ARGUMENT
import com.n16dccn159.admin.constant.KEY_IS_CHOOSE_ADDRESS
import com.n16dccn159.admin.databinding.FragmentAddressBookBinding
import com.n16dccn159.admin.ext.*
import com.n16dccn159.admin.view.MainActivity
import com.n16dccn159.admin.view.addressbook.adapter.AddressAdapter
import com.n16dccn159.admin.viewmodel.ListenerViewModel
import com.n16dccn159.admin.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddressBookFragment : BaseFragment<FragmentAddressBookBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_address_book

    private val userViewModel: UserViewModel by viewModel()
    private val listenerViewModel: ListenerViewModel by sharedViewModel()

    private var isChooseAddress = false

    private val adapter = AddressAdapter { it: ShoppingAddress?, pos: Int? ->
        if (pos == null) {
            if (!isChooseAddress) {
                navController.navigateAnimation(
                    R.id.shippingAddressDetailFragment,
                    bundle = bundleOf(KEY_ARGUMENT to it)
                )
            } else {
                CoreApplication.instance.cart?.shippingAddress = it
                listenerViewModel.updateShippingAddress.value = true
                navController.popBackStack()
            }
        } else {
            userViewModel.deleteAddress(it?.id)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.btnNav?.visibility = View.GONE
        (activity as? BaseActivity<*>)?.isShowLoading(true)

        if (arguments?.containsKey(KEY_IS_CHOOSE_ADDRESS) == true)
            isChooseAddress = requireArguments().getBoolean(KEY_IS_CHOOSE_ADDRESS, false)

        userViewModel.getAllAddress()
        setupToolbar()
        initAdapter()
    }

    private fun initAdapter() {
        viewBinding.rvAddress.adapter = adapter
        viewBinding.swlRefresh.setOnRefreshListener {
            userViewModel.getAllAddress()
        }
    }

    override fun bindEvent() {
        viewBinding.fabCreateAddress.setOnClickListener {
            navController.navigateAnimation(
                R.id.shippingAddressDetailFragment
            )
        }
    }

    override fun bindViewModelOnce() {
        super.bindViewModelOnce()
        userViewModel.allAddressLiveData.observe(this, Observer {
            adapter.submitList(it)
            viewBinding.swlRefresh.isRefreshing = false
            (activity as? BaseActivity<*>)?.isShowLoading(false)
        })

        userViewModel.updateAddressResultLiveData.observe(this, Observer {
            messageHandler?.runMessageHandler(it)
            adapter.removeItem(adapter.currentPosition)
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