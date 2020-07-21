package com.ptithcm.ptshop.view.card

import android.os.Bundle
import android.text.InputFilter
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.text.HtmlCompat
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import com.ptithcm.core.CoreApplication
import com.ptithcm.core.model.CheckoutBrand
import com.ptithcm.core.model.CreditCard
import com.ptithcm.core.model.Discount
import com.ptithcm.core.param.CheckoutParam
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.base.BaseActivity
import com.ptithcm.ptshop.base.BaseFragment
import com.ptithcm.ptshop.constant.*
import com.ptithcm.ptshop.databinding.FragmentCheckoutBinding
import com.ptithcm.ptshop.databinding.LayoutBottomsheetDiscountCodeBinding
import com.ptithcm.ptshop.ext.*
import com.ptithcm.ptshop.util.PopUp
import com.ptithcm.ptshop.widget.RecyclerRefreshLayout
import com.ptithcm.ptshop.view.MainActivity
import com.ptithcm.ptshop.view.home.StoryDetailActivity
import com.ptithcm.ptshop.viewmodel.CheckoutViewModel
import com.ptithcm.ptshop.viewmodel.ListenerViewModel
import com.ptithcm.ptshop.viewmodel.PaymentViewModel
import com.stripe.android.model.Card
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CheckoutFragment : BaseFragment<FragmentCheckoutBinding>(), View.OnClickListener {

    override val layoutId: Int
        get() = R.layout.fragment_checkout

    private lateinit var brandCheckoutAdapter: BrandCheckoutAdapter

    private val checkoutViewModel: CheckoutViewModel by viewModel()
    private val paymentViewModel: PaymentViewModel by sharedViewModel()
    private val listenerViewModel: ListenerViewModel by sharedViewModel()

    private var isCallApi = false
    private var isError = false
    private var selectMethod: CreditCard? = null
    private var popUp: LayoutBottomsheetDiscountCodeBinding? = null

    private var listCheckoutBrand = mutableListOf<CheckoutBrand>()
    // just for show shipping fee in ui
    private var listSelectedShippingRates = arrayListOf<Pair<Int, String>>()

    private var result: Pair<MutableList<CheckoutBrand>?, Discount?>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkoutViewModel.getCheckout()
        isCallApi = true
        paymentViewModel.getPaymentMethods()
    }

    override fun bindEvent() {
        if (isCallApi.not()) {
            isHideContent(false)
        } else {
            viewBinding.swipeRfCheckout.setRefreshing(true)
        }

        viewBinding.fragment = this

        viewBinding.shippingAddress.basket = CoreApplication.instance.profile?.user

        if (selectMethod != null) {
            // base on address of credit card
            viewBinding.billingAddress.creditCard = selectMethod
        }

        viewBinding.shippingAddress.root.setOnClickListener(this)

        viewBinding.billingAddress.root.setOnClickListener(this)

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

        viewBinding.swipeRfCheckout.setOnRefreshListener {
            viewBinding.swipeRfCheckout.setRefreshing(true)
            checkoutViewModel.getCheckout()
        }

        viewBinding.sbAccept.setOnCheckedChangeListener { _, isChecked ->
            viewBinding.btnCheckOut.apply {
                btnOutOfStock = isChecked.not() || isError
                isEnabled = isChecked && isError.not()
            }
        }

        setUpRv()

        result?.let { it ->
            brandCheckoutAdapter.addList(it.first as? ArrayList<CheckoutBrand> ?: arrayListOf())
            if (listSelectedShippingRates.isNullOrEmpty()) {
                it.first?.forEach {
                    it.selectedShippingId = it.rates?.rates?.first()?.id
                }
            }
            viewBinding.discount = it.second
            setTaxCheckout(it)
        }

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
            isHideContent()
            checkoutViewModel.getCheckout()
            paymentViewModel.getPaymentMethods()
        })

        checkoutViewModel.checkoutResultWithTax.observe(this, Observer {
            brandCheckoutAdapter.addList(it.first as? ArrayList<CheckoutBrand> ?: arrayListOf())
            if (listSelectedShippingRates.isNullOrEmpty()) {
                it.first?.forEach { checkout ->
                    if (checkout.rates?.rates?.size ?: 0 > 0) {
                        checkout.selectedShippingId = checkout.rates?.rates?.first()?.id
                    }
                }
            }
            isError = false
            viewBinding.discount = it.second
            setTaxCheckout(it)
            result = it
            (activity as? BaseActivity<*>)?.closePopup()
        })

        checkoutViewModel.errorCheckout.observe(this, Observer {
            (activity as? BaseActivity<*>)?.isShowErrorNetwork(false)
            brandCheckoutAdapter.addList(it.first as? ArrayList<CheckoutBrand> ?: arrayListOf())
            setTaxCheckout(it)
            isError = true
            if (result == null) {
                result = it
            }
        })

        checkoutViewModel.setShippingPreOrderFinish.observe(this, Observer {
            if (it) {
                checkoutViewModel.saveCardPreOrder(
                    CheckoutParam(
                        card_token = selectMethod?.id ?: return@Observer,
                        save_card = false
                    )
                )
            }
        })
    }

    override fun bindViewModel() {
        checkoutViewModel.checkoutResult.observe(this, Observer {})
        checkoutViewModel.shippingRate.observe(this, Observer {})
        checkoutViewModel.taxCheckoutResult.observe(this, Observer {})
        checkoutViewModel.finalCheckoutResult.observe(this, Observer {})
        checkoutViewModel.shippingPreOrder.observe(this, Observer {})
        checkoutViewModel.discountResult.observe(this, Observer {})

        checkoutViewModel.saveCardPreOrder.observe(this, Observer {
            // After finish checkout must remove item that in wish list
            // BE NOT SUPPORT remove from wish list
            // so FE must call remove item ONE BY ONE
            (activity as? MainActivity)?.removeFromWishListAfterCheckout(false)
            val cusOption = NavOptions.Builder().apply {
                setLaunchSingleTop(true)
                setEnterAnim(R.anim.slide_in_right)
                setExitAnim(R.anim.slide_out_left)
                setPopEnterAnim(R.anim.slide_in_left)
                setPopExitAnim(R.anim.slide_out_right)
                setPopUpTo(R.id.fragment_shopping_bag, true)
            }.build()
            navController.navigateAnimation(
                R.id.fragment_confirm_checkout,
                customNavOption = cusOption
            )
        })

        paymentViewModel.getPaymentMethodLiveData.observe(this, Observer { arr ->
            var card = arr.find { it?.default_card == true }
            if (card == null && arr.size > 0)
                card = arr.first()
            selectMethod = card
            viewBinding.billingAddress.creditCard = selectMethod
            viewBinding.includePayment.data = card
            viewBinding.includePayment.ivCreditCard.setImageResource(Card.getBrandIcon(card?.brand))
        })

        paymentViewModel.error.observe(this, Observer {
            if (selectMethod == null) {
                val user = CoreApplication.instance.profile?.user
                viewBinding.billingAddress.apply {
                    this.emptyBillingAddressText.gone()
                    this.billingAddressLine1.apply {
                        text = user?.billing_address_line_1
                        visible()
                    }
                    this.billingAddressLine2.apply {
                        text = user?.billing_address_line_2
                        visible()
                    }
                    this.billingAddressTownCity.apply {
                        text = user?.billing_address_town_city
                        visible()
                    }
                    this.billingAddressCountryArea.apply {
                        text = user?.billing_address_county_area
                        visible()
                    }
                    this.billingAddressCountry.apply {
                        text = user?.billing_address_country
                        visible()
                    }
                    this.billingAddressPostCode.apply {
                        text = user?.billing_address_postcode_zip
                        visible()
                    }
                }
            }
        })

        listenerViewModel.changePayment.observe(this, Observer { card ->
            selectMethod = card
            viewBinding.billingAddress.creditCard = selectMethod
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
                        checkoutViewModel.callShippingRatesPreOrder(listCheckoutBrand)
                    }
                }
            }
            R.id.shipping_address -> {
                navController.navigateAnimation(
                    R.id.fragment_shipping_address,
                    bundle = bundleOf(
                        KEY_ARGUMENT to selectMethod
                    )
                )
            }
            R.id.billing_address -> {
                if (selectMethod == null) {
                    navController.navigateAnimation(
                        R.id.fragment_billing_address
                    )
                } else {
                    navController.navigateAnimation(
                        R.id.fragment_bill_address,
                        bundle = bundleOf(
                            KEY_ARGUMENT to selectMethod,
                            KEY_ARGUMENT_BOOLEAN to true
                        )
                    )
                }
            }
            R.id.includePayment -> {
                navController.navigateAnimation(
                    R.id.fragment_payment,
                    bundle = bundleOf(
                        KEY_ARGUMENT_BOOLEAN to true,
                        KEY_ARGUMENT_STRING to selectMethod?.id
                    )
                )
            }
            R.id.includeDiscount -> {
                showDialog()
            }
            R.id.includeHasDiscount -> {
                showDialog()
            }
        }
    }

    private fun recyclerListenerId(id: Int?) {
        navController.navigateAnimation(
            R.id.fragment_brand_term_condition,
            bundle = bundleOf(KEY_ARGUMENT_INT to id)
        )
    }

    private fun recyclerListenerPair(item: Pair<Int, String>) {
        // position and shipping_rate_id when change shipping type
        listSelectedShippingRates.removeOrAdd(item)
        listCheckoutBrand.forEach { checkoutBrand ->
            val ratePosition = listSelectedShippingRates.findShippingRatePosition(
                checkoutBrand.checkout_identifier
            )
            val rate = if (checkoutBrand.rates?.rates?.size ?: 0 > 0)
                checkoutBrand.rates?.rates?.get(ratePosition)
            else null
            checkoutBrand.selectedShippingId = rate?.id
        } // find and apply shipping method
        setTaxCheckout(Pair(listCheckoutBrand, viewBinding.discount))
    }

    private fun setUpRv() {
        brandCheckoutAdapter = BrandCheckoutAdapter(
            listenerId = this::recyclerListenerId,
            listenerPair = this::recyclerListenerPair
        )
        viewBinding.rvBrandCheckout.adapter = brandCheckoutAdapter
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

    private fun showDialog() {
        (activity as? BaseActivity<*>)?.showPopup(
            PopUp(
                popupId = R.layout.layout_bottomsheet_discount_code,
                isBottomSheet = true,
                isCancelable = true,
                isMatchParent = true,
                messageQueue = this::popEvent
            )
        )

    }

    private fun popEvent(binding: ViewDataBinding?) {
        (binding as? LayoutBottomsheetDiscountCodeBinding)?.apply {
            popUp = this
            discount = viewBinding.discount
            this.edtDiscountCode.filters = arrayOf(InputFilter.AllCaps())
            this.btnApply.setOnClickListener {
                val str = this.edtDiscountCode.text.toString()
                if (str.length != 8) {
                    messageHandler?.runMessageErrorHandler(getString(R.string.your_code_must_has_8_characters))
                } else {
                    listSelectedShippingRates.clear()
                    this.btnApply.isLoading = true
                    checkoutViewModel.addDiscountCode(str)
                }
            }
            this.tvRemoveDiscount.apply {
                text = HtmlCompat.fromHtml(
                    getString(R.string.remove_discount),
                    HtmlCompat.FROM_HTML_MODE_COMPACT
                )
                setOnClickListener {
                    listSelectedShippingRates.clear()
                    binding.btnApply.isLoading = true
                    checkoutViewModel.addDiscountCode("")
                }
            }
        }
    }

    private fun setTaxCheckout(item: Pair<MutableList<CheckoutBrand>?, Discount?>) {
        isCallApi = false
        isHideContent(isCallApi)
        val locale = CoreApplication.instance.currency.getLocale()!!
        // reset total price
        var sum = 0.0

        item.first?.apply {
            listCheckoutBrand = this
            var itemCost = 0.0
            var merchantDiscount = 0.0
            var tax = 0.0
            var shippingTax = 0.0
            val shippingFee = this.fold(0.0, { x, y ->
                itemCost += y.local_products_price?.toDouble() ?: 0.0
                merchantDiscount += (y.applied_discount?.amount ?: 0.0)
                tax += if (y.taxes_included == false) y.taxCheckout?.tax_info?.total_tax?.toDouble()
                    ?: 0.0 else 0.0
                if (y.taxes_included == false && y.tax_shipping == true) {
                    val shipping = y.getSelectedRatesPrice()
                    shippingTax += (y.taxCheckout?.tax_info?.tax_lines?.first()?.rate
                        ?: 0.0) * (shipping?.price?.toDouble() ?: 0.0)
                }
                val ratePosition = listSelectedShippingRates.findShippingRatePosition(
                    y.checkout_identifier
                )
                val rate = if (y.rates?.rates?.size ?: 0 > 0) {
                    y.rates?.rates?.get(ratePosition)
                } else null
                (rate?.price?.toDouble() ?: 0.0) + x
            })

            val subTotal = itemCost + merchantDiscount

            if (merchantDiscount > 0.0) {
                viewBinding.includeTaxCheckout.merchantdiscount =
                    merchantDiscount.toString().roundPrice(locale)
                viewBinding.includeTaxCheckout.tvMerchantDiscount.text = HtmlCompat.fromHtml(
                    getString(R.string.discount_from_merchant),
                    HtmlCompat.FROM_HTML_MODE_COMPACT
                )
            } else {
                viewBinding.includeTaxCheckout.merchantdiscount = null
            }
            viewBinding.includeTaxCheckout.subtotal = subTotal.toString().roundPrice(locale)
            viewBinding.includeTaxCheckout.shipping =
                if (shippingFee == 0.0) "FREE" else shippingFee.toString().roundPrice(locale)
            viewBinding.includeTaxCheckout.tax = tax.toString().roundPrice(locale)
            if (shippingTax != 0.0) {
                viewBinding.includeTaxCheckout.shippingTax =
                    shippingTax.toString().roundPrice(locale)
            } else {
                viewBinding.includeTaxCheckout.shippingTax = null
            }
            sum += subTotal + shippingFee + tax + shippingTax - merchantDiscount
        }

        if (item.second != null) {
            viewBinding.includeTaxCheckout.tvDiscount.text = HtmlCompat.fromHtml(
                getString(R.string.discount_from_code),
                HtmlCompat.FROM_HTML_MODE_COMPACT
            )
            var discountAmount = item.second?.local_discount_code_applied ?: 0.0
            if ((item.second?.local_discount_code_applied
                    ?: 0.0) > sum && item.second?.discount_code_type == 3
            ) {
                discountAmount = sum // just care if discount is voucher
            }
            sum -= discountAmount
            viewBinding.includeTaxCheckout.discount = discountAmount.toString().roundPrice(locale)
        } else {
            viewBinding.includeTaxCheckout.discount = null
        }
        if (sum < 0.0) {
            sum = 0.0
        }
        viewBinding.includeTaxCheckout.total = sum.toString().roundPrice(locale)
    }

    private fun isHideContent(value: Boolean = true) {
        viewBinding.swipeRfCheckout.setRefreshing(value)
        if (value) {
            viewBinding.clContainer.gone()
            viewBinding.btnCheckOut.gone()
        } else {
            viewBinding.clContainer.visible()
            viewBinding.btnCheckOut.visible()
        }
    }
}