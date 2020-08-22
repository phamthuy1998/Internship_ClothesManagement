package com.ptithcm.ptshop.view.payment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders
import com.ptithcm.core.BuildConfig
import com.ptithcm.core.model.CreditCard
import com.ptithcm.core.util.ObjectHandler
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.base.BaseActivity
import com.ptithcm.ptshop.base.BaseFragment
import com.ptithcm.ptshop.databinding.FragmentDetailCreditCardBinding
import com.ptithcm.ptshop.databinding.LayoutPopUpBinding
import com.ptithcm.ptshop.ext.addInfo
import com.ptithcm.ptshop.ext.initToolBar
import com.ptithcm.ptshop.ext.isEmpty
import com.ptithcm.ptshop.ext.setMaxLength
import com.ptithcm.ptshop.util.CreditUtil
import com.ptithcm.ptshop.util.PopUp
import com.ptithcm.ptshop.view.MainActivity
import com.ptithcm.ptshop.view.addressbook.adapter.ExpiryDateAdapter
import com.ptithcm.ptshop.viewmodel.ListenerViewModel
import com.ptithcm.ptshop.viewmodel.SharedViewModel
import com.stripe.android.ApiResultCallback
import com.stripe.android.Stripe
import com.stripe.android.model.Card
import com.stripe.android.model.Token
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CreditCardDetailFragment : BaseFragment<FragmentDetailCreditCardBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_detail_credit_card

    private val listenerViewModel: ListenerViewModel by sharedViewModel()

    private lateinit var expiryDate: ArrayList<String>
    private lateinit var adapterExpiryDate: ExpiryDateAdapter
    private var expiryMonth = 0
    private var expiryYear = 0
    private var creditCard: CreditCard? = null
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var stripe: Stripe
    private var numberCreditCard = -1
    private var isPopBackStack = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = this.arguments
        if (bundle != null) {
            creditCard = bundle.getParcelable("card") as CreditCard?
            numberCreditCard = bundle.getInt("numberOfCards")
        }
        sharedViewModel =
            activity?.let { ViewModelProviders.of(it).get(SharedViewModel::class.java) }!!
        stripe = Stripe(requireContext(), BuildConfig.PUBLISHABLE_KEY)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        val icon = requireContext().getDrawable(com.stripe.android.R.drawable.ic_unknown)
        viewBinding.mCardIconImageView.setImageDrawable(icon)
        viewBinding.imgCheckCVV.visibility = View.INVISIBLE
        viewBinding.fragment = this
        initExpiryDateSpinner()
        eventCheckValidCardNumberListener()
        checkCVVListener()
        checkFullNameListener()
        changeExpiryDateListener()
        if (creditCard == null) {
            viewBinding.isValidForm = false
        } else {
            viewBinding.isValidForm = true
            bindCreditCard()
        }
    }

    private fun changeExpiryDateListener() {
        viewBinding.spExpiryDate.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) {}
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long
                ) {
                    if (creditCard == null) {
                        viewBinding.isValidForm = isValidForm()
                        if (position != 0) {
                            expiryMonth =
                                adapterExpiryDate.getItem(position).substring(0, 2).toInt()
                            expiryYear = adapterExpiryDate.getItem(position).substring(3).toInt()
                        }
                    }
                }

            }
    }

    private fun checkCVVListener() {
        viewBinding.edtCVV.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (creditCard == null) {
                    var validNumberLength = 3
                    if (viewBinding.etCardNumber.cardBrand == Card.CardBrand.AMERICAN_EXPRESS) validNumberLength =
                        4
                    if (p0?.length ?: 0 == validNumberLength) {
                        viewBinding.imgCheckCVV.visibility = View.VISIBLE
                    } else {
                        viewBinding.imgCheckCVV.visibility = View.INVISIBLE
                    }
                    viewBinding.isValidForm = isValidForm()
                }
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        })
    }

    private fun bindAddress() {
        creditCard = ObjectHandler.cart?.copyCreditCard()
    }

    private fun bindCreditCard() {
        viewBinding.etCardNumber.setText(getString(R.string.number_card, creditCard?.last4))
        viewBinding.etCardNumber.setTextColor(requireContext().getColor(R.color.black))
        creditCard?.brand?.let { updateCardIcon(it) }
        viewBinding.edtName.setText(creditCard?.name)
        viewBinding.spExpiryDate.setSelection(
            adapterExpiryDate.getPosition(
                "" + String.format(
                    "%02d",
                    creditCard?.exp_month
                ) + "/" + creditCard?.exp_year
            )
        )
        if (creditCard?.brand == Card.CardBrand.AMERICAN_EXPRESS) {
            viewBinding.edtCVV.setText("****")
        } else {
            viewBinding.edtCVV.setText("***")
        }
    }

    private fun initExpiryDateSpinner() {
        expiryDate = CreditUtil.getExpiryDate()
        adapterExpiryDate = ExpiryDateAdapter(requireContext())
        adapterExpiryDate.setListData(expiryDate)
        viewBinding.spExpiryDate.adapter = adapterExpiryDate
    }

    private fun eventCheckValidCardNumberListener() {
        viewBinding.etCardNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                if (creditCard == null) {
                    updateCardIcon(viewBinding.etCardNumber.cardBrand)
                    if (viewBinding.etCardNumber.cardBrand == Card.CardBrand.AMERICAN_EXPRESS) {
                        viewBinding.edtCVV.setMaxLength(4)
                    } else {
                        viewBinding.edtCVV.setMaxLength(3)
                    }
                    if (viewBinding.etCardNumber.text?.length in 16..19) viewBinding.isValidCardNumer =
                        viewBinding.etCardNumber.isCardNumberValid
                    viewBinding.isValidForm = isValidForm()
                } else {
                    if (creditCard?.brand == Card.CardBrand.AMERICAN_EXPRESS) {
                        viewBinding.edtCVV.setMaxLength(4)
                    } else {
                        viewBinding.edtCVV.setMaxLength(3)
                    }
                    viewBinding.etCardNumber.setTextColor(requireContext().getColor(R.color.black))
                }
            }

        })
    }

    private fun checkFullNameListener() {
        viewBinding.edtName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (creditCard == null) {
                    viewBinding.isValidForm = isValidForm()
                    viewBinding.isValidName =
                        viewBinding.edtName.text.toString().trim().split(" ").size > 1
                }
            }
        })
    }

    fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnAddPayment -> {
                isPopBackStack = true
                    if (viewBinding.edtName.text.toString().trim().split(" ").size == 1) {
                        messageHandler?.runMessageErrorHandler(getString(R.string.please_enter_your_full_name))
                    } else {
                        changStatusButton(true)
                        var cardToSave = Card.create(
                            viewBinding.etCardNumber.text.toString(), expiryMonth, expiryYear,
                            viewBinding.edtCVV.text.toString()
                        )
                        cardToSave = cardToSave.addInfo(
                            name = viewBinding.edtName.text.toString(),
                            creditCard = creditCard
                        )
                        tokenizeCard(cardToSave)
                    }
            }
            R.id.btnCancel -> {
                (requireActivity() as? BaseActivity<*>)?.closePopup()
            }
            R.id.btnOk -> {
                (requireActivity() as? BaseActivity<*>)?.closePopup()
                listenerViewModel.changePayment.value = null
                navController.popBackStack()
            }
            R.id.ivRight -> {
                isPopBackStack = false
                (requireActivity() as? BaseActivity<*>)?.showPopup(
                    PopUp(
                        R.layout.layout_pop_up,
                        messageQueue = this::popDeleteEvent
                    )
                )
            }
        }
    }

    private fun changStatusButton(isLoading: Boolean) {
        viewBinding.btnAddPayment.isLoading = isLoading
    }

    private fun tokenizeCard(card: Card) {
        stripe.createToken(card,
            object : ApiResultCallback<Token> {
                override fun onError(e: Exception) {
                    messageHandler?.runMessageErrorHandler(e.message ?: "")
                    changStatusButton(false)
                }

                override fun onSuccess(result: Token) {
                    if (creditCard == null)
                        bindAddress()
                    creditCard?.brand = viewBinding.etCardNumber.cardBrand
                    creditCard?.mapCreditCard(ObjectHandler.cart?.shippingAddress)
                    creditCard?.tokenCard = result.id
                    listenerViewModel.changePayment.value = creditCard
                    navController.popBackStack()
                }
            })
    }

    private fun updateCardIcon(@Card.CardBrand brand: String) {
        if (Card.CardBrand.UNKNOWN == brand) {
            val icon = requireContext().getDrawable(com.stripe.android.R.drawable.ic_unknown)
            viewBinding.mCardIconImageView.setImageDrawable(icon)
        } else {
            viewBinding.mCardIconImageView.setImageResource(Card.getBrandIcon(brand))
        }
    }

    private fun isValidForm(): Boolean {
        return when {
            !viewBinding.etCardNumber.isCardNumberValid
                    || viewBinding.edtName.isEmpty()
                    || viewBinding.edtCVV.isEmpty()
                    || (viewBinding.edtCVV.length() < 3 && viewBinding.etCardNumber.cardBrand != Card.CardBrand.AMERICAN_EXPRESS)
                    || (viewBinding.edtCVV.length() < 4 && viewBinding.etCardNumber.cardBrand == Card.CardBrand.AMERICAN_EXPRESS)
                    || viewBinding.spExpiryDate.selectedItemPosition == 0 -> {
                false
            }
            else -> true
        }
    }

    private fun setupToolbar() {
        (requireActivity() as? MainActivity)?.apply {
            //toolbar for edit credit card
            if (creditCard != null) {
                initToolBar(
                    viewBinding.layoutToolbar.toolbar,
                    hasBackRight = false, hasLeft = false, hasCount = false
                )
                viewBinding.layoutToolbar.ivRight.setOnClickListener(this@CreditCardDetailFragment::onClick)
                viewBinding.layoutToolbar.ivRight.setImageResource(R.drawable.ic_delete)
            } else {
                //toolbar for add new credit card
                initToolBar(
                    viewBinding.layoutToolbar.toolbar, hasBackRight = false, hasLeft = false,
                    hasRight = false, hasCount = false
                )
            }
            viewBinding.layoutToolbar.ivBack.setImageResource(R.drawable.ic_back)
        }
    }

    private fun popDeleteEvent(popupBinding: ViewDataBinding?) {
        (popupBinding as? LayoutPopUpBinding)?.apply {
            left = getString(R.string.cancel)
            right = getString(R.string.delete)
            btnCancel.setOnClickListener(this@CreditCardDetailFragment::onClick)
            btnOk.setOnClickListener(this@CreditCardDetailFragment::onClick)
            title = getString(R.string.delete_credit_card)
        }
    }
}