package com.ptithcm.ptshop.view.addressbook

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ptithcm.core.CoreApplication
import com.ptithcm.core.model.Address
import com.ptithcm.core.model.Location
import com.ptithcm.core.param.UpdateAddressParam
import com.ptithcm.core.param.UpdatePaymentMethodParam
import com.ptithcm.core.util.capitalize
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.base.BaseFragment
import com.ptithcm.ptshop.constant.ERROR_CODE_404
import com.ptithcm.ptshop.constant.KEY_ARGUMENT
import com.ptithcm.ptshop.constant.KEY_EMPTY
import com.ptithcm.ptshop.databinding.FragmentDetailBillingAddressBinding
import com.ptithcm.ptshop.ext.initToolBar
import com.ptithcm.ptshop.ext.isEmpty
import com.ptithcm.ptshop.ext.isShowErrorNetwork
import com.ptithcm.ptshop.ext.setupToolbar
import com.ptithcm.ptshop.view.MainActivity
import com.ptithcm.ptshop.view.addressbook.adapter.CountyAdapter
import com.ptithcm.ptshop.view.addressbook.adapter.LocationAdapter
import com.ptithcm.ptshop.viewmodel.PaymentViewModel
import com.ptithcm.ptshop.viewmodel.SharedViewModel
import com.ptithcm.ptshop.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class BillingAddressDetailFragment : BaseFragment<FragmentDetailBillingAddressBinding>() {
    private val paymentViewModel: PaymentViewModel by viewModel()
    private val userViewModel: UserViewModel by viewModel()
    override val layoutId: Int
        get() = R.layout.fragment_detail_billing_address
    private lateinit var adapterCountry: LocationAdapter
    private lateinit var adapterCity: CountyAdapter
    private var fromPaymentMethod = false
    private var address: Address? = null
    private lateinit var sharedViewModel: SharedViewModel
    private var cardIdDefault: String? = null
    private val user = CoreApplication.instance.profile?.user

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.btnNav?.visibility = View.GONE
        if (fromPaymentMethod) {
            user?.billing_address_country = address?.address_country
            user?.billing_address_county_area = address?.address_county_area
            user?.billing_address_line_1 = address?.address_line_1
            user?.billing_address_line_2 = address?.address_line_2
            user?.billing_address_postcode_zip = address?.address_postcode_zip
            user?.billing_address_town_city = address?.address_town_city
        }
        cardIdDefault = arguments?.getString(KEY_ARGUMENT)
        adapterCountry = LocationAdapter(requireContext())
        adapterCountry.setListLocation(
            arrayListOf(
                Location(
                    id = 0,
                    name = user?.billing_address_country ?: "",
                    counties = null
                )
            )
        )
        viewBinding.spCountry.adapter = adapterCountry
        if (user?.billing_address_country == null) {
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
        adapterCity = CountyAdapter(requireContext())
        adapterCity.setListLocation(
            arrayListOf(
                user?.billing_address_county_area ?: ""
            )
        )
        viewBinding.spCity.adapter = adapterCity
        if (user?.billing_address_county_area == null) {
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
        val bundle = this.arguments
        if (bundle != null) {
            address = bundle.getParcelable("address") as Address?
            if (address != null) fromPaymentMethod = true
        }
        paymentViewModel.getPaymentMethods()
        observeViewModel()
    }

    private fun observeViewModel() {
        paymentViewModel.getListLocationLiveData.observe(this, Observer {
            adapterCountry.setListLocation(ArrayList(it.results))
            if (user?.billing_address_country != null) {
                viewBinding.spCountry.setSelection(
                    adapterCountry.getPosition(user.billing_address_country ?: KEY_EMPTY)
                )
                paymentViewModel.getLocation(adapterCountry.getItem(viewBinding.spCountry.selectedItemPosition).id)
            } else {
                viewBinding.spCountry.setSelection(0)
            }
        })
        paymentViewModel.getLocationLiveData.observe(this, Observer {
            adapterCity.setListLocation(ArrayList(it.counties ?: ArrayList()))
            if (it.counties?.indexOf(user?.billing_address_county_area) != -1) {
                viewBinding.spCity.setSelection(
                    (it.counties?.indexOf(user?.billing_address_county_area)!! + 1)
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
            if (cardIdDefault != null) {
                paymentViewModel.updatePaymentMethod(
                    cardIdDefault!!,
                    UpdatePaymentMethodParam(
                        true,
                        (viewBinding.spCountry.selectedItem as Location).name,
                        viewBinding.spCity.selectedItem as String,
                        viewBinding.edtAddressLine1.text.toString(),
                        viewBinding.edtAddressLine2.text.toString(),
                        viewBinding.edtPostCode.text.toString(),
                        viewBinding.edtAddressTownCity.text.toString()

                    )
                )
            }
        })
        userViewModel.error.observe(this, Observer {
            responseError(it)
        })
        paymentViewModel.updateAddressBookLiveData.observe(this, Observer {
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
                    if (!fromPaymentMethod) {
                        changeStatusButton(true)
                        val user = CoreApplication.instance.profile?.user
                        user?.let {
                            val param = UpdateAddressParam(
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
                            userViewModel.updateBookAddress(param)

                        }
                    } else {
                        sharedViewModel = activity?.let {
                            ViewModelProviders.of(it).get(SharedViewModel::class.java)
                        }!!
                        sharedViewModel.setAddress(
                            Address(
                                (viewBinding.spCountry.selectedItem as Location).name,
                                viewBinding.spCity.selectedItem as String,
                                viewBinding.edtAddressLine1.text.toString(),
                                viewBinding.edtAddressLine2.text.toString(),
                                viewBinding.edtPostCode.text.toString(),
                                viewBinding.edtAddressTownCity.text.toString()
                            )
                        )
                        navController.popBackStack()
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
                    viewBinding.edtPostCode.isEmpty() -> {
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