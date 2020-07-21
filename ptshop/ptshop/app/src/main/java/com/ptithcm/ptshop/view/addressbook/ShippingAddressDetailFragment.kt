package com.ptithcm.ptshop.view.addressbook

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import androidx.lifecycle.Observer
import com.ptithcm.core.CoreApplication
import com.ptithcm.core.model.CreditCard
import com.ptithcm.core.model.Location
import com.ptithcm.core.param.UpdateAddressParam
import com.ptithcm.core.param.UpdatePaymentMethodParam
import com.ptithcm.core.util.capitalize
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.base.BaseFragment
import com.ptithcm.ptshop.constant.ERROR_CODE_404
import com.ptithcm.ptshop.constant.KEY_ARGUMENT
import com.ptithcm.ptshop.constant.KEY_EMPTY
import com.ptithcm.ptshop.databinding.FragmentDetailShippingAddressBinding
import com.ptithcm.ptshop.ext.initToolBar
import com.ptithcm.ptshop.ext.isEmpty
import com.ptithcm.ptshop.ext.isShowErrorNetwork
import com.ptithcm.ptshop.ext.setupToolbar
import com.ptithcm.ptshop.view.MainActivity
import com.ptithcm.ptshop.view.addressbook.adapter.CountyAdapter
import com.ptithcm.ptshop.view.addressbook.adapter.LocationAdapter
import com.ptithcm.ptshop.viewmodel.ListenerViewModel
import com.ptithcm.ptshop.viewmodel.PaymentViewModel
import com.ptithcm.ptshop.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShippingAddressDetailFragment : BaseFragment<FragmentDetailShippingAddressBinding>() {
    private val paymentViewModel: PaymentViewModel by viewModel()
    private val userViewModel: UserViewModel by viewModel()
    private val listenerViewModel: ListenerViewModel by sharedViewModel()

    override val layoutId: Int
        get() = R.layout.fragment_detail_shipping_address
    private lateinit var adapterCountry: LocationAdapter
    private lateinit var adapterCity: CountyAdapter
    private var creditCard: CreditCard? = null
    private val user = CoreApplication.instance.profile?.user

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.btnNav?.visibility = View.GONE
        adapterCountry = LocationAdapter(requireContext())
        adapterCountry.setListLocation(
            arrayListOf(
                Location(
                    id = 0,
                    name = user?.shipping_address_country ?: "",
                    counties = null
                )
            )
        )
        viewBinding.spCountry.adapter = adapterCountry
        if (user?.shipping_address_country == null) {
            viewBinding.spCountry.setSelection(0)
        } else {
            viewBinding.spCountry.setSelection(1)
        }
        viewBinding.spCountry.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                if (adapterCountry.count != 2) {
                    paymentViewModel.getLocation(adapterCountry.getItem(viewBinding.spCountry.selectedItemPosition).id)
                    adapterCity.setListLocation(arrayListOf())
                }
            }

        }
        adapterCity = CountyAdapter(requireContext())
        adapterCity.setListLocation(
            arrayListOf(
                user?.shipping_address_county_area ?: ""
            )
        )
        viewBinding.spCity.adapter = adapterCity
        if (user?.shipping_address_county_area == null) {
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

        setupToolbar()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun bindEvent() {
        super.bindEvent()
        viewBinding.fragment = this
        viewBinding.user = user
        viewBinding.container.setOnTouchListener { _, event ->
            if (event != null && event.action == MotionEvent.ACTION_MOVE) {
                hideKeyboard()
            }
            false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        paymentViewModel.getListLocation()
        creditCard = arguments?.getParcelable(KEY_ARGUMENT)
        observeViewModel()
    }

    private fun observeViewModel() {
        paymentViewModel.getListLocationLiveData.observe(this, Observer {
            adapterCountry.setListLocation(ArrayList(it.results))
            if (user?.shipping_address_country != null) {
                viewBinding.spCountry.setSelection(
                    adapterCountry.getPosition(user.shipping_address_country ?: KEY_EMPTY)
                )
                paymentViewModel.getLocation(adapterCountry.getItem(viewBinding.spCountry.selectedItemPosition).id)
            } else {
                viewBinding.spCountry.setSelection(0)
            }
        })
        paymentViewModel.getLocationLiveData.observe(this, Observer {
            adapterCity.setListLocation(ArrayList(it.counties ?: ArrayList()))
            if (it.counties?.indexOf(user?.shipping_address_county_area) != -1) {
                viewBinding.spCity.setSelection(
                    (it.counties?.indexOf(user?.shipping_address_county_area)!! + 1)
                )
            }
        })
        userViewModel.updateAddressBookLiveData.observe(this, Observer {
            CoreApplication.instance.profile?.user = it
            CoreApplication.instance.saveUser(CoreApplication.instance.profile!!)
            paymentViewModel.updateBookAddress(
                UpdateAddressParam(
                    (viewBinding.spCountry.selectedItem as Location).name,
                    viewBinding.spCity.selectedItem as String,
                    viewBinding.edtAddressLine1.text.toString(),
                    viewBinding.edtAddressLine2.text.toString(),
                    viewBinding.edtPostCode.text.toString(),
                    viewBinding.edtAddressTownCity.text.toString(),
                    it.shipping_address_country,
                    it.shipping_address_county_area,
                    it.shipping_address_line_1,
                    it.shipping_address_line_2,
                    it.shipping_address_postcode_zip,
                    it.shipping_address_town_city,
                    it.shipping_telephone
                )
            )
        })
        userViewModel.error.observe(this, Observer {
            responseError(it)
        })
        paymentViewModel.updateAddressBookLiveData.observe(this, Observer {
            listenerViewModel.setUpdate()
            navController.popBackStack()
        })
        paymentViewModel.error.observe(this, Observer {
           responseError(it)
        })
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
                    val user = CoreApplication.instance.profile?.user
                    if (viewBinding.asBillAddressCheck.isChecked) {
                        userViewModel.updateBookAddress(
                            UpdateAddressParam(
                                (viewBinding.spCountry.selectedItem as Location).name,
                                viewBinding.spCity.selectedItem as String,
                                viewBinding.edtAddressLine1.text.toString(),
                                viewBinding.edtAddressLine2.text.toString(),
                                viewBinding.edtPostCode.text.toString(),
                                viewBinding.edtAddressTownCity.text.toString(),
                                (viewBinding.spCountry.selectedItem as Location).name,
                                viewBinding.spCity.selectedItem as String,
                                viewBinding.edtAddressLine1.text.toString(),
                                viewBinding.edtAddressLine2.text.toString(),
                                viewBinding.edtPostCode.text.toString(),
                                viewBinding.edtAddressTownCity.text.toString(),
                                viewBinding.edtPhone.text.toString()
                            )
                        )
                        // check if enter from checkout then update credit card detail
                        if (creditCard != null) {
                            paymentViewModel.updatePaymentMethod(
                                creditCard?.id ?: return,
                                UpdatePaymentMethodParam(
                                    set_default = creditCard?.default_card ?: false,
                                    address_country = (viewBinding.spCountry.selectedItem as Location).name,
                                    address_state = viewBinding.spCity.selectedItem as String,
                                    address_line1 = viewBinding.edtAddressLine1.text.toString(),
                                    address_line2 = viewBinding.edtAddressLine2.text.toString(),
                                    address_zip = viewBinding.edtPostCode.text.toString(),
                                    address_city = viewBinding.edtAddressTownCity.text.toString()
                                )
                            )
                        }
                    } else {
                        user?.let {
                            userViewModel.updateBookAddress(
                                UpdateAddressParam(
                                    it.billing_address_country,
                                    it.billing_address_county_area,
                                    it.billing_address_line_1,
                                    it.billing_address_line_2,
                                    it.billing_address_postcode_zip,
                                    it.billing_address_town_city,
                                    (viewBinding.spCountry.selectedItem as Location).name,
                                    viewBinding.spCity.selectedItem as String,
                                    viewBinding.edtAddressLine1.text.toString(),
                                    viewBinding.edtAddressLine2.text.toString(),
                                    viewBinding.edtPostCode.text.toString(),
                                    viewBinding.edtAddressTownCity.text.toString(),
                                    viewBinding.edtPhone.text.toString()
                                )
                            )
                        }
                    }

                }
            }
        }
    }

    private fun checkValidField(): Boolean {
        return when {
            viewBinding.spCountry.selectedItemPosition == 0 ||
                    viewBinding.spCity.selectedItemPosition == 0 ||
                    viewBinding.edtAddressLine1.isEmpty() ||
                    viewBinding.edtAddressTownCity.isEmpty() ||
                    viewBinding.edtPostCode.isEmpty() ||
                    viewBinding.edtPhone.isEmpty()-> {
                messageHandler?.runMessageErrorHandler("Please complete all required fields")
                false
            }
            else -> true
        }
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