package com.ptithcm.ptshop.view.card

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.ptithcm.core.CoreApplication
import com.ptithcm.core.model.Cart
import com.ptithcm.core.model.PromotionType
import com.ptithcm.core.param.RequestCheckoutParam
import com.ptithcm.core.util.ObjectHandler
import com.ptithcm.core.util.PriceFormat
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.base.BaseActivity
import com.ptithcm.ptshop.base.BaseFragment
import com.ptithcm.ptshop.constant.KEY_ARGUMENT_BOOLEAN
import com.ptithcm.ptshop.constant.KEY_IS_CHOOSE_ADDRESS
import com.ptithcm.ptshop.databinding.FragmentCheckoutBinding
import com.ptithcm.ptshop.databinding.LayoutPopUpBinding
import com.ptithcm.ptshop.ext.initToolBar
import com.ptithcm.ptshop.ext.navigateAnimation
import com.ptithcm.ptshop.ext.setupToolbar
import com.ptithcm.ptshop.ext.visible
import com.ptithcm.ptshop.util.PopUp
import com.ptithcm.ptshop.view.MainActivity
import com.ptithcm.ptshop.view.home.StoryDetailActivity
import com.ptithcm.ptshop.viewmodel.CheckoutViewModel
import com.ptithcm.ptshop.viewmodel.ListenerViewModel
import com.ptithcm.ptshop.viewmodel.ShoppingViewModel
import com.ptithcm.ptshop.viewmodel.UserViewModel
import com.ptithcm.ptshop.widget.RecyclerRefreshLayout
import com.stripe.android.model.Card
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CheckoutFragment : BaseFragment<FragmentCheckoutBinding>(), View.OnClickListener {

    override val layoutId: Int
        get() = R.layout.fragment_checkout

    private val productCheckoutAdapter: ProductCheckoutAdapter = ProductCheckoutAdapter()

    private val checkoutViewModel: CheckoutViewModel by viewModel()
    private val basketViewModel: ShoppingViewModel by viewModel()
    private val userViewModel: UserViewModel by viewModel()

    private val listenerViewModel: ListenerViewModel by sharedViewModel()

    private var isCallApi = false
    private var isClickCheckout = false
    private var curPositionSelectedMethod = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isCallApi = true
    }

    override fun bindEvent() {
        setUpToolbar()
        setUpRv()
        setupPullToRefresh()

        viewBinding.fragment = this

        if (isCallApi) {
            basketViewModel.getAllProductsInCart(ObjectHandler.getAllIdProdsInCart())
            userViewModel.getAllAddress()
            viewBinding.swipeRfCheckout.setRefreshing(true)
            isCallApi = false
        } else {
            setTaxCheckout()
            viewBinding.btnCheckOut.visible()
        }
        viewBinding.shippingAddress.item = CoreApplication.instance.cart?.shippingAddress
        viewBinding.includePayment.data = ObjectHandler.cart?.creditCard
        viewBinding.includePayment.ivCreditCard.setImageResource(Card.getBrandIcon(ObjectHandler.cart?.creditCard?.brand))
        viewBinding.includePayment.spinnerDelivery.setSelection(curPositionSelectedMethod)

        viewBinding.shippingAddress.root.setOnClickListener(this)
        viewBinding.includePayment.root.setOnClickListener(this)
        viewBinding.includeDiscount.setOnClickListener(this)
        viewBinding.includeHasDiscount.root.setOnClickListener(this)
    }

    private fun setupPullToRefresh() {
        viewBinding.swipeRfCheckout.setRefreshView(
            RecyclerRefreshLayout(
                requireContext(),
                text = getString(R.string.prepare_checkout)
            ),
            ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        )

        viewBinding.swipeRfCheckout.setOnRefreshListener {
            viewBinding.swipeRfCheckout.setRefreshing(true)
            basketViewModel.getAllProductsInCart(ObjectHandler.getAllIdProdsInCart())
        }
    }

    override fun bindViewModelOnce() {
        basketViewModel.cartResult.observe(this, Observer { productCart ->
            if (viewBinding.btnCheckOut.isLoading) {
                val cart = CoreApplication.instance.cart ?: return@Observer

                val products = cart.products.map { it.quantityInCart }
                products.forEach { product ->
                    product?.price = productCart.find { pr -> pr.id == product?.productID }
                        ?.getProductDiscountPrice()
                }
                val checkoutParam = RequestCheckoutParam(
                    accountID = CoreApplication.instance.account?.id,
                    address = cart.shippingAddress?.getFullAddress(),
                    price = total,
                    name = cart.shippingAddress?.name,
                    phone = cart.shippingAddress?.phone,
                    products = products,
                    note = viewBinding.includeNote.edtNote.text?.toString(),
                    tokenCard = null
                )
                if (curPositionSelectedMethod == 2)
                    checkoutParam.tokenCard = cart.creditCard?.tokenCard

                checkoutViewModel.requestCheckout(checkoutParam)
                return@Observer
            }

            productCheckoutAdapter.addToList(productCart)
            viewBinding.swipeRfCheckout.setRefreshing(false)
            viewBinding.btnCheckOut.visible()
            setTaxCheckout()
            val indexOfItemChanged = productCart.indexOfFirst { it.hasChanged }
            if (indexOfItemChanged != -1) {
                (requireActivity() as? BaseActivity<*>)?.showPopup(
                    PopUp(
                        R.layout.layout_pop_up,
                        messageQueue = this::popHasChangedItem
                    )
                )
                return@Observer
            }
        })

        checkoutViewModel.resultRequestCheckout.observe(this, Observer {
            messageHandler?.runMessageHandler(it)
            ObjectHandler.cart = Cart()
            CoreApplication.instance.clearCart()
            navController.popBackStack()
        })

        checkoutViewModel.error.observe(this, Observer {
            messageHandler?.runMessageErrorHandler(it.first)
            viewBinding.btnCheckOut.isLoading = false
        })

        listenerViewModel.updateShippingAddress.observe(this, Observer {
            viewBinding.shippingAddress.item = CoreApplication.instance.cart?.shippingAddress
        })

        listenerViewModel.changePayment.observe(this, Observer { card ->
            ObjectHandler.cart?.creditCard = card
            viewBinding.includePayment.data = card
            viewBinding.includePayment.ivCreditCard.setImageResource(Card.getBrandIcon(card?.brand))
            if (card?.tokenCard.isNullOrEmpty())
                viewBinding.includePayment.data = null
        })

        userViewModel.allAddressLiveData.observe(this, androidx.lifecycle.Observer {
            val defaultAddress = it.firstOrNull { address -> address.isDefault == 1 }
            CoreApplication.instance.saveDefaultAddress(defaultAddress)
            viewBinding.shippingAddress.item = CoreApplication.instance.cart?.shippingAddress
        })
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnCheckOut -> {
                when (viewBinding.includePayment.spinnerDelivery.selectedItemPosition) {
                    0 -> messageHandler?.runMessageErrorHandler(getString(R.string.select_payment_method))
                    1 -> {
                        if (validateInfo()) {
                            isClickCheckout = true
                            basketViewModel.getAllProductsInCart(ObjectHandler.getAllIdProdsInCart())
                            viewBinding.btnCheckOut.isLoading = true
                        }
                    }
                    2 -> {
                        if (ObjectHandler.cart?.creditCard == null || ObjectHandler.cart?.creditCard?.tokenCard.isNullOrEmpty())
                            messageHandler?.runMessageErrorHandler(getString(R.string.select_payment_method_credit_card))
                        else if (validateInfo()) {
                            isClickCheckout = true
                            basketViewModel.getAllProductsInCart(ObjectHandler.getAllIdProdsInCart())
                            viewBinding.btnCheckOut.isLoading = true
                        }
                    }
                }
            }
            R.id.shipping_address -> {
                navController.navigateAnimation(
                    R.id.nav_book_address,
                    bundle = bundleOf(
                        KEY_IS_CHOOSE_ADDRESS to true
                    )
                )
            }

            R.id.includePayment -> {
                if (viewBinding.includePayment.spinnerDelivery.selectedItemPosition == 2)
                    navController.navigateAnimation(
                        R.id.creditCardDetailFragment,
                        bundle = bundleOf(
                            KEY_ARGUMENT_BOOLEAN to true,
                            "card" to ObjectHandler.cart?.creditCard
                        )
                    )
            }

            R.id.btnOk -> {
                navController.popBackStack()
            }
        }
    }

    private fun validateInfo(): Boolean {
        var isValidate = true
        val cart = CoreApplication.instance.cart ?: return false
        if (cart.shippingAddress?.getFullAddress().isNullOrEmpty()) {
            messageHandler?.runMessageErrorHandler(getString(R.string.select_address))
            isValidate = false
        }
        return isValidate
    }

    private fun setUpRv() {
        viewBinding.rvProductCheckout.adapter = productCheckoutAdapter
        productCheckoutAdapter.addToList(CoreApplication.instance.cart?.products ?: arrayListOf())

        viewBinding.includePayment.spinnerDelivery.apply {
            if (adapter != null)
                return@apply

            val arr = arrayListOf(
                context.getString(R.string.select_delivery_method),
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
                    curPositionSelectedMethod = position
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

    private fun popHasChangedItem(popupBinding: ViewDataBinding?) {
        (popupBinding as? LayoutPopUpBinding)?.apply {
            left = getString(R.string.close)
            btnCancel.setOnClickListener(this@CheckoutFragment::onClick)
            btnOk.visibility = View.GONE
            title = getString(R.string.some_item_changed_in_cart)
        }
    }

    var total = 0.0
    private fun setTaxCheckout() {
        isCallApi = false
        // reset total price
        var sum = 0.0
        var sumDiscount = 0.0
        CoreApplication.instance.cart?.products?.forEach {
            sum += (it.price ?: 0.0) * (it.quantityInCart?.quantity ?: 1)

            when (it.typePromotion) {
                PromotionType.PERCENT -> {
                    val discount = (it.price ?: 0.0) * (it.valuePromotion
                        ?: 0.0) * (it.quantityInCart?.quantity ?: 0)
                    sumDiscount += discount
                }
                PromotionType.ABSOLUTE -> {
                    sumDiscount += (it.valuePromotion ?: 0.0) * (it.quantityInCart?.quantity ?: 0)
                }
            }
        }

        total = sum - sumDiscount

        viewBinding.includeTaxCheckout.subtotal = PriceFormat.priceFormat(sum)
        viewBinding.includeTaxCheckout.total = PriceFormat.priceFormat(total)
        if (sumDiscount != 0.0)
            viewBinding.includeTaxCheckout.discount = PriceFormat.priceFormat(sumDiscount)
    }
}