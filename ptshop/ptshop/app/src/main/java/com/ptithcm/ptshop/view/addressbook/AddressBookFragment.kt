package com.ptithcm.ptshop.view.addressbook

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import com.ptithcm.core.CoreApplication
import com.ptithcm.core.model.CreditCard
import com.ptithcm.core.model.Profile
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.base.BaseActivity
import com.ptithcm.ptshop.base.BaseFragment
import com.ptithcm.ptshop.constant.ERROR_CODE_404
import com.ptithcm.ptshop.constant.KEY_ARGUMENT
import com.ptithcm.ptshop.databinding.FragmentAddressBookBinding
import com.ptithcm.ptshop.ext.initToolBar
import com.ptithcm.ptshop.ext.isShowErrorNetwork
import com.ptithcm.ptshop.ext.isShowLoading
import com.ptithcm.ptshop.ext.setupToolbar
import com.ptithcm.ptshop.view.MainActivity
import com.ptithcm.ptshop.viewmodel.PaymentViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddressBookFragment : BaseFragment<FragmentAddressBookBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_address_book
    private val paymentViewModel: PaymentViewModel by viewModel()
    private var cardDefault: CreditCard? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        paymentViewModel.getPaymentMethods()
        (activity as? BaseActivity<*>)?.isShowLoading(true)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.btnNav?.visibility = View.GONE
        setupToolbar()
    }

    override fun bindEvent() {
        super.bindEvent()
        viewBinding.user = CoreApplication.instance.profile?.user
        viewBinding.cardDefault = CoreApplication.instance.profile?.user?.copyCreditCard()
        viewBinding.fragment = this
    }

    override fun bindViewModelOnce() {
        super.bindViewModelOnce()
        paymentViewModel.getPaymentMethodLiveData.observe(this, Observer {
            (activity as? MainActivity)?.isShowLoading(false)
            cardDefault = it.find { item -> item?.default_card == true }
            cardDefault?.apply {
                updateBillingAddress(this)
            }
            viewBinding.cardDefault = cardDefault
            paymentViewModel.getListLocationLiveData.removeObservers(this)
        })
        paymentViewModel.error.observe(this, Observer {
            if (it.second == ERROR_CODE_404) {
                (requireActivity() as? MainActivity)?.isShowErrorNetwork(true)
            } else {
                messageHandler?.runMessageHandler(it.first)
            }
        })
    }

    private fun updateBillingAddress(card: CreditCard) {
        CoreApplication.instance.profile?.copy()
        CoreApplication.instance.saveUser(
            Profile(
                CoreApplication.instance.profile?.token,
                user = CoreApplication.instance.profile?.user?.copyBillingAddress(
                    billing_address_country = card.address_country,
                    billing_address_county_area = card.address_state,
                    billing_address_line_1 = card.address_line1,
                    billing_address_line_2 = card.address_line2,
                    billing_address_postcode_zip = card.address_zip,
                    billing_address_town_city = card.address_city
                )
            )
        )
    }

    fun onClick(v: View?) {
        when (v?.id) {
            R.id.billingAddressContainer -> {
                navController.navigate(
                    R.id.billingAddressDetailFragment,
                    bundleOf(KEY_ARGUMENT to cardDefault?.id.toString())
                )
            }
            R.id.shippingAddressContainer -> {
                navController.navigate(
                    R.id.shippingAddressDetailFragment,
                    bundleOf(KEY_ARGUMENT to cardDefault)
                )
            }
        }
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