package com.n16dccn159.admin.view.addressbook

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import com.n16dccn159.core.model.ShoppingAddress
import com.n16dccn159.core.util.capitalize
import com.n16dccn159.admin.R
import com.n16dccn159.admin.base.BaseFragment
import com.n16dccn159.admin.constant.ERROR_CODE_404
import com.n16dccn159.admin.constant.KEY_ARGUMENT
import com.n16dccn159.admin.databinding.FragmentDetailShippingAddressBinding
import com.n16dccn159.admin.ext.gone
import com.n16dccn159.admin.ext.initToolBar
import com.n16dccn159.admin.ext.isShowErrorNetwork
import com.n16dccn159.admin.ext.setupToolbar
import com.n16dccn159.admin.view.MainActivity
import com.n16dccn159.admin.viewmodel.ListenerViewModel
import com.n16dccn159.admin.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.support.v4.toast
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShippingAddressDetailFragment : BaseFragment<FragmentDetailShippingAddressBinding>() {
    private val userViewModel: UserViewModel by viewModel()
    private val listenerViewModel: ListenerViewModel by sharedViewModel()

    override val layoutId: Int
        get() = R.layout.fragment_detail_shipping_address

    private var isAddAddress = true
    var address: ShoppingAddress = ShoppingAddress()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        address = arguments?.getParcelable(KEY_ARGUMENT) ?: ShoppingAddress()
        isAddAddress = arguments?.getParcelable<ShoppingAddress>(KEY_ARGUMENT) == null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.btnNav?.gone()
        setupToolbar()
        viewBinding.lifecycleOwner = this
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun bindEvent() {
        super.bindEvent()
        viewBinding.fragment = this
        viewBinding.isAdd = isAddAddress
        viewBinding.sbDefaultAddress.isChecked = address.isDefault == 1

        viewBinding.container.setOnTouchListener { _, event ->
            if (event != null && event.action == MotionEvent.ACTION_MOVE) {
                hideKeyboard()
            }
            false
        }
    }

    override fun bindViewModelOnce() {
        userViewModel.updateAddressResultLiveData.observe(this, Observer {
            changeStatusButton(false)
            messageHandler?.runMessageHandler(it)
            toast(it)
        })
        userViewModel.error.observe(this, Observer {
            changeStatusButton(false)
            responseError(it)
        })
//        paymentViewModel.updateAddressBookLiveData.observe(this, Observer {
//            listenerViewModel.setUpdate()
//            navController.popBackStack()
//        })
//        paymentViewModel.error.observe(this, Observer {
//           responseError(it)
//        })
    }

    private fun responseError(it: Pair<String, Int?>) {
        if (it.second == ERROR_CODE_404) {
            (requireActivity() as? MainActivity)?.isShowErrorNetwork(true)
        } else {
            changeStatusButton(false)
            messageHandler?.runMessageErrorHandler(it.first)
        }
    }

    fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_update_address -> {
                if (checkValidField()) {
                    changeStatusButton(true)
                    address.isDefault = if (viewBinding.sbDefaultAddress.isChecked) 1 else 0
                    if (isAddAddress) {
                        userViewModel.addAddress(address)
                    } else {
                        userViewModel.updateAddress(address)
                    }
                }
            }
        }
    }

    private fun checkValidField(): Boolean {
        return if (address.isEmpty()) {
            messageHandler?.runMessageErrorHandler("Please complete all required fields")
            false
        } else true
    }

    private fun changeStatusButton(isLoading: Boolean) {
        viewBinding.btnUpdateAddress.isLoading = isLoading
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
                getString(R.string.shipping_address).capitalize()
            )
        }
    }

    private fun hideKeyboard() {
        val inputMethodManager = requireActivity().getSystemService(
            Activity.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
            requireActivity().currentFocus?.windowToken, 0
        )
    }
}