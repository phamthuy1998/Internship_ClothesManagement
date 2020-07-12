package com.sg.snapshop.view.card

import android.app.Activity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import androidx.lifecycle.Observer
import com.sg.core.model.Address
import com.sg.core.model.CreditCard
import com.sg.core.model.Location
import com.sg.core.param.UpdatePaymentMethodParam
import com.sg.core.util.capitalize
import com.sg.snapshop.R
import com.sg.snapshop.base.BaseFragment
import com.sg.snapshop.constant.ERROR_CODE_404
import com.sg.snapshop.constant.KEY_ARGUMENT
import com.sg.snapshop.constant.KEY_ARGUMENT_BOOLEAN
import com.sg.snapshop.constant.KEY_EMPTY
import com.sg.snapshop.databinding.FragmentDetailBillAddressBinding
import com.sg.snapshop.ext.initToolBar
import com.sg.snapshop.ext.isEmpty
import com.sg.snapshop.ext.isShowErrorNetwork
import com.sg.snapshop.ext.setupToolbar
import com.sg.snapshop.view.MainActivity
import com.sg.snapshop.view.addressbook.adapter.CountyAdapter
import com.sg.snapshop.view.addressbook.adapter.LocationAdapter
import com.sg.snapshop.viewmodel.ListenerViewModel
import com.sg.snapshop.viewmodel.PaymentViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class BillAddressDetailFragment : BaseFragment<FragmentDetailBillAddressBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_detail_bill_address

    private val paymentViewModel: PaymentViewModel by viewModel()
    private val listenerViewModel: ListenerViewModel by sharedViewModel()

    private var selectedCard: CreditCard? = null

    private lateinit var adapterCountry: LocationAdapter
    private lateinit var adapterCity: CountyAdapter
    private var fromPaymentMethod = false
    private var address: Address? = null
    private var isFromCheckout = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        paymentViewModel.getListLocation()
        val bundle = this.arguments
        if (bundle != null) {
            address = bundle.getParcelable("address") as Address?
            if (address != null) fromPaymentMethod = true
        }
        selectedCard = arguments?.getParcelable(KEY_ARGUMENT)
        isFromCheckout = arguments?.getBoolean(KEY_ARGUMENT_BOOLEAN) ?: false
        activity?.btnNav?.visibility = View.GONE
    }

    override fun bindEvent() {
        viewBinding.fragment = this
        viewBinding.creditCard = selectedCard
        setupToolbar()

        setUpSpCountry()

        setUpSpLocation()
    }

    private fun setUpSpCountry() {
        adapterCountry = LocationAdapter(requireContext())

        adapterCountry.setListLocation(
            arrayListOf(
                Location(
                    id = 0,
                    name = selectedCard?.country ?: "",
                    counties = null
                )
            )
        )

        viewBinding.spCountry.adapter = adapterCountry

        if (selectedCard?.country == null) {
            viewBinding.spCountry.setSelection(0)
        } else {
            viewBinding.spCountry.setSelection(1)
        }

        viewBinding.spCountry.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) {}
                override fun onItemSelected(
                    p0: AdapterView<*>?,
                    p1: View?,
                    position: Int,
                    p3: Long
                ) {
                    if (adapterCountry.count != 2) {
                        paymentViewModel.getLocation(adapterCountry.getItem(viewBinding.spCountry.selectedItemPosition).id)
                        adapterCity.setListLocation(arrayListOf())
                    }
                }

            }
    }

    private fun setUpSpLocation() {
        adapterCity = CountyAdapter(requireContext())

        adapterCity.setListLocation(
            arrayListOf(
                selectedCard?.address_state ?: ""
            )
        )

        viewBinding.spCity.adapter = adapterCity

        if (selectedCard?.address_state == null) {
            viewBinding.spCity.setSelection(0)
        } else {
            viewBinding.spCity.setSelection(1)
        }

        viewBinding.spCity.setOnTouchListener { _, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_UP) {
                if (adapterCity.count == 2) {
                    messageHandler?.runMessageErrorHandler("Could not load the couties list. Please try again")
                    true
                } else false
            } else false
        }

    }

    override fun bindViewModelOnce() {
        paymentViewModel.getListLocationLiveData.observe(this, Observer {
            adapterCountry.setListLocation(ArrayList(it.results))
            if (selectedCard?.address_country != null) {
                viewBinding.spCountry.setSelection(
                    adapterCountry.getPosition(selectedCard?.address_country ?: KEY_EMPTY)
                )
                paymentViewModel.getLocation(adapterCountry.getItem(viewBinding.spCountry.selectedItemPosition).id)
            } else {
                viewBinding.spCountry.setSelection(0)
            }
        })

        paymentViewModel.getLocationLiveData.observe(this, Observer {
            adapterCity.setListLocation(ArrayList(it.counties ?: ArrayList()))
            if (it.counties?.indexOf(selectedCard?.address_state) != -1) {
                viewBinding.spCity.setSelection(
                    (it.counties?.indexOf(selectedCard?.address_state)!! + 1)
                )
            }
        })

        paymentViewModel.updatePaymentMethodLiveData.observe(this, Observer {
            changeStatusButton(false)
            if (isFromCheckout) {
                listenerViewModel.changePayment.value = it
            }
            navController.popBackStack()
        })

        paymentViewModel.error.observe(this, Observer {
            if (it.second == ERROR_CODE_404) {
                (requireActivity() as? MainActivity)?.isShowErrorNetwork(true)
            } else {
                changeStatusButton(false)
                messageHandler?.runMessageErrorHandler(it.first)
            }
        })
    }

    fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_update_address -> {
                if (checkValidField()) {
                    changeStatusButton()
                    paymentViewModel.updatePaymentMethod(
                        selectedCard?.id ?: return,
                        UpdatePaymentMethodParam(
                            selectedCard?.default_card ?: false,
                            (viewBinding.spCountry.selectedItem as Location).name,
                            viewBinding.spCity.selectedItem as String,
                            viewBinding.edtAddressLine1.text.toString(),
                            viewBinding.edtAddressLine2.text.toString(),
                            viewBinding.edtPostCode.text.toString(),
                            viewBinding.edtAddressTownCity.text.toString()
                        )
                    )
                }
            }

            R.id.container -> {
                hideKeyboard()
            }
        }
    }

    private fun checkValidField(): Boolean {
        return when {
            viewBinding.spCountry.selectedItemPosition == 0 ||
                    viewBinding.spCity.selectedItemPosition == 0 ||
                    viewBinding.edtAddressLine1.isEmpty() ||
                    viewBinding.edtAddressTownCity.isEmpty() ||
                    viewBinding.edtPostCode.isEmpty() -> {
                messageHandler?.runMessageErrorHandler("Please complete all required fields")
                false
            }
            else -> true
        }
    }

    private fun changeStatusButton(isLoading: Boolean = true) {
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
                getString(R.string.billing_address).capitalize()
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