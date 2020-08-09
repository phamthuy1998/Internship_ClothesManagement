package com.ptithcm.ptshop.view.card

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.ptithcm.core.CoreApplication
import com.ptithcm.core.model.CreditCard
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.base.BaseActivity
import com.ptithcm.ptshop.base.BaseFragment
import com.ptithcm.ptshop.constant.ERROR_CODE_404
import com.ptithcm.ptshop.constant.KEY_ARGUMENT_BOOLEAN
import com.ptithcm.ptshop.constant.KEY_IS_CHOOSE_ADDRESS
import com.ptithcm.ptshop.databinding.FragmentCheckoutBinding
import com.ptithcm.ptshop.databinding.LayoutBottomsheetDiscountCodeBinding
import com.ptithcm.ptshop.ext.initToolBar
import com.ptithcm.ptshop.ext.isShowErrorNetwork
import com.ptithcm.ptshop.ext.navigateAnimation
import com.ptithcm.ptshop.ext.setupToolbar
import com.ptithcm.ptshop.view.MainActivity
import com.ptithcm.ptshop.view.home.StoryDetailActivity
import com.ptithcm.ptshop.viewmodel.CheckoutViewModel
import com.ptithcm.ptshop.viewmodel.ListenerViewModel
import com.ptithcm.ptshop.viewmodel.PaymentViewModel
import com.ptithcm.ptshop.widget.RecyclerRefreshLayout
import com.stripe.android.model.Card
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class CheckoutFragment : BaseFragment<FragmentCheckoutBinding>(), View.OnClickListener {

    override val layoutId: Int
        get() = R.layout.fragment_checkout

    private val productCheckoutAdapter: ProductCheckoutAdapter = ProductCheckoutAdapter()

    private val checkoutViewModel: CheckoutViewModel by viewModel()
    private val paymentViewModel: PaymentViewModel by sharedViewModel()
    private val listenerViewModel: ListenerViewModel by sharedViewModel()

    private var isCallApi = false
    private var isError = false
    private var selectMethod: CreditCard? = null
    private var popUp: LayoutBottomsheetDiscountCodeBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isCallApi = true
        paymentViewModel.getPaymentMethods()
    }

    override fun bindEvent() {
        viewBinding.shippingAddress.item = CoreApplication.instance.cart?.shippingAddress

        viewBinding.fragment = this

        viewBinding.shippingAddress.root.setOnClickListener(this)

        viewBinding.includePayment.root.setOnClickListener(this)

        viewBinding.includeDiscount.setOnClickListener(this)

        viewBinding.includeHasDiscount.root.setOnClickListener(this)

        viewBinding.swipeRfCheckout.setRefreshView(
            RecyclerRefreshLayout(
                context ?: return,
                text = getString(R.string.prepare_checkout)
            ),
            ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        )

        setUpRv()
        setUpToolbar()
    }

    override fun bindViewModelOnce() {
        checkoutViewModel.error.observe(this, Observer {
            if (it.second == ERROR_CODE_404) {
                (requireActivity() as? BaseActivity<*>)?.isShowErrorNetwork(true)
            } else {
                messageHandler?.runMessageErrorHandler(it.first)
                popUp?.btnApply?.isLoading = false
            }
        })

        checkoutViewModel.isLoading.observe(this, Observer {
            if (it.not()) {
                viewBinding.swipeRfCheckout.setRefreshing(it)
                viewBinding.btnCheckOut.isLoading = it
            }
        })

        listenerViewModel.updateShippingAddress.observe(this, Observer {
            viewBinding.shippingAddress.item = CoreApplication.instance.cart?.shippingAddress
        })
    }

    override fun bindViewModel() {
        listenerViewModel.changePayment.observe(this, Observer { card ->
            selectMethod = card
            viewBinding.includePayment.data = card
            viewBinding.includePayment.ivCreditCard.setImageResource(Card.getBrandIcon(card?.brand))
        })
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnCheckOut -> {
                if (isError.not()) {
                    if (selectMethod == null) {
                        messageHandler?.runMessageErrorHandler(getString(R.string.select_payment_method))
                    } else {
                        viewBinding.btnCheckOut.isLoading = true
//                        checkoutViewModel.callShippingRatesPreOrder(listCheckoutBrand)
                    }
                }
            }
            R.id.shipping_address -> {
                navController.navigateAnimation(
                    R.id.fragment_shipping_address,
                    bundle = bundleOf(
                        KEY_IS_CHOOSE_ADDRESS to true
                    )
                )
            }

            R.id.includePayment -> {
                navController.navigateAnimation(
                    R.id.creditCardDetailFragment,
                    bundle = bundleOf(
                        KEY_ARGUMENT_BOOLEAN to true,
                        "card" to selectMethod
                    )
                )
            }
        }
    }

    private fun setUpRv() {
        viewBinding.rvProductCheckout.adapter = productCheckoutAdapter
        productCheckoutAdapter.addToList(CoreApplication.instance.cart?.products ?: arrayListOf())

        viewBinding.includePayment.spinnerDelivery.apply {
            val arr = arrayListOf(
                context.getString(R.string.select_delivery_method).toUpperCase(
                    Locale.getDefault()
                ),
                "Cash on Delivery",
                "Credit Card"
            )

            adapter = ArrayAdapter<String>(context, R.layout.item_spinner, arr)
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) {}

                override fun onItemSelected(
                    p0: AdapterView<*>?,
                    p1: View?,
                    position: Int,
                    p3: Long
                ) {
                    viewBinding.includePayment.clPayment.isVisible = position == 2
                }
            }
        }
    }

    private fun setUpToolbar() {
        (activity as? MainActivity)?.apply {
            initToolBar(
                viewBinding.layoutToolbar.toolbar,
                hasBack = true,
                hasBackRight = false,
                hasLeft = false,
                hasRight = false
            )
            setupToolbar(viewBinding.layoutToolbar.toolbar, getString(R.string.checkout))
        }
        (activity as? StoryDetailActivity)?.apply {
            initToolBar(
                viewBinding.layoutToolbar.toolbar,
                hasBack = true,
                hasBackRight = false,
                hasLeft = false,
                hasRight = false
            )
            setupToolbar(viewBinding.layoutToolbar.toolbar, getString(R.string.checkout))
        }
    }
}