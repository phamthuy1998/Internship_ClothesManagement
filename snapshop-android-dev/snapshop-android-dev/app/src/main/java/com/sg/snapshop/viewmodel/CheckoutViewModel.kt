package com.sg.snapshop.viewmodel

import androidx.lifecycle.*
import com.sg.core.CoreApplication
import com.sg.core.model.*
import com.sg.core.param.CheckoutParam
import com.sg.core.param.DiscountParam
import com.sg.core.param.ShippingRateParam
import com.sg.core.repository.CheckoutRepository
import com.sg.core.vo.Result
import com.sg.snapshop.ext.toCheckout
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class CheckoutViewModel(private val repo: CheckoutRepository): ViewModel() {
    val shippingRate = MediatorLiveData<ShippingRate>()
    val checkoutResult = MediatorLiveData<Checkout>()
    val taxCheckoutResult = MediatorLiveData<TaxCheckout>()
    private var checkout: Checkout? = null

    private val mutableShippingRate = MutableLiveData<ArrayList<Pair<ShippingRate?, Int?>>>()
    private val arr = arrayListOf<Pair<ShippingRate?, Int?>>()
    private var shippingRateCount = 0

    val finalCheckoutResult = Transformations.switchMap(mutableShippingRate){rates ->
        val data = checkout?.brands?.values?.toMutableList()
        taxArr.clear()
        data?.forEach {checkoutBrand ->
            val index = rates.indexOfFirst { it.second == checkoutBrand.brand?.id }
            if ( index != -1 ){
                checkoutBrand.rates = rates[index].first
            }
            getTaxCheckout(checkoutBrand.brand?.id, checkoutBrand.checkout_identifier)
        }
        val result = MutableLiveData<Pair<MutableList<CheckoutBrand>?, Discount?>>()
        result.value = Pair(data, checkout?.discount)
        result
    }

    val discountResult = MediatorLiveData<Checkout>()

    private val taxArrLiveData = MutableLiveData<ArrayList<Pair<TaxCheckout?, Int?>>>()
    private val taxArr = arrayListOf<Pair<TaxCheckout?, Int?>>()
    private var taxCount = 0

    val checkoutResultWithTax = Transformations.switchMap(taxArrLiveData){taxes ->
        val result = MutableLiveData<Pair<MutableList<CheckoutBrand>?, Discount?>>()
        val checkout = finalCheckoutResult.value
        checkout?.first?.toMutableList()?.forEach { checkoutBrand ->
            val index = taxes?.indexOfFirst { it.second == checkoutBrand.brand?.id } ?: -1
            if (index != -1){
                checkoutBrand.taxCheckout = taxes?.get(index)?.first
            }
        }
        result.value = Pair(checkout?.first, checkout?.second)
        result
    }

    private var shippingPreOrderCount = 0
    val shippingPreOrder = MediatorLiveData<Checkout>()
    val setShippingPreOrderFinish = MutableLiveData<Boolean>()

    val saveCardPreOrder = MediatorLiveData<Checkout>()

    val error = MutableLiveData<Pair<String, Int?>>()
    val errorCheckout = MutableLiveData<Pair<MutableList<CheckoutBrand>?, Discount?>>()
    val isLoading = MutableLiveData<Boolean>()

    fun getCheckout(){
        viewModelScope.launch {
            checkoutResult.addSource(repo.getCheckout()){
                when(it){
                    Result.Loading -> {
                        isLoading.value = true
                    }
                    is Result.Error -> {
                        isLoading.value = false
                        error.value = Pair(it.message, it.code)
                        errorCheckout.value = CoreApplication.instance.basket?.toCheckout()
                    }
                    is Result.Success -> {
                        checkoutResult.value = it.data
                        checkout = it.data
                        taxCount = it.data?.brands?.values?.size ?: 0
                        shippingRateCount = it.data?.brands?.values?.size ?: 0
                        arr.clear()
                        it.data?.brands?.values?.forEach {checkoutBrand ->
                            getRates(checkoutBrand.brand?.id, checkoutBrand.checkout_identifier)
                        }
                    }
                }
            }
        }
    }

    private fun getRates(id: Int?, identifier: String?){
        viewModelScope.launch {
            shippingRate.addSource(repo.getShippingRates(id, identifier)){
                when(it){
                    Result.Loading -> {
                        isLoading.value = true
                    }
                    is Result.Error -> {
                        error.value = Pair(it.message, it.code)
                        isLoading.value = false
                    }
                    is  Result.Success -> {
                        arr.add(Pair(it.data, id))
                        shippingRateCount--
                        if (shippingRateCount == 0){
                            mutableShippingRate.value = arr
                        }
                    }
                }
            }
        }
    }

    private fun getTaxCheckout(id: Int?, identifier: String?){
        viewModelScope.launch {
            taxCheckoutResult.addSource(repo.getTaxCheckout(id, identifier)){
                when(it){
                    Result.Loading -> {
                        isLoading.value = true
                    }
                    is Result.Error -> {
                        isLoading.value = false
                    }
                    is Result.Success -> {
                        isLoading.value = false
                        taxArr.add(Pair(it.data, id))
                        taxCount--
                        if (taxCount == 0) {
                            taxArrLiveData.value = taxArr
                        }
                    }
                }
            }
        }
    }

    fun addDiscountCode(str: String){
        reset()
        viewModelScope.launch {
            discountResult.addSource(repo.addDiscountCode(DiscountParam(str))){
                when(it){
                    Result.Loading -> {}

                    is Result.Error -> {
                        error.value = Pair(it.message, it.code)
                    }

                    is Result.Success -> {
                        getCheckout()
                    }
                }
            }
        }
    }

    private fun reset(){
        arr.clear()
        taxArr.clear()
        shippingRateCount = 0
        taxCount = 0
    }

    private fun checkShippingRatePreOrder(id: Int?, identifier: String?, shippingRateParam: ShippingRateParam){
        viewModelScope.launch {
            shippingPreOrder.addSource(repo.shippingRatesPreOrder(id, identifier, shippingRateParam)){
                when(it){
                    Result.Loading -> {
                        isLoading.value = true
                    }
                    is Result.Error -> {
                        error.value = Pair(it.message, it.code)
                        isLoading.value = false
                    }
                    is Result.Success -> {
                        shippingPreOrderCount--
                        if (shippingPreOrderCount == 0) {
                            setShippingPreOrderFinish.value = true
                        }
                    }
                }
            }
        }
    }

    fun callShippingRatesPreOrder(listBrands: MutableList<CheckoutBrand>){
        runBlocking {
            launch {
                shippingPreOrderCount = listBrands.size
                setShippingPreOrderFinish.value = false
                listBrands.forEach {
                    if (it.selectedShippingId == null){
                        isLoading.value = false
                        error.value = Pair("Please select delivery methods for all products ",111)
                        return@launch
                    }
                    checkShippingRatePreOrder(
                        it.brand?.id,
                        it.checkout_identifier,
                        ShippingRateParam(
                            it.selectedShippingId ?: return@forEach
                        )
                    )
                }
            }
        }
    }

    fun saveCardPreOrder(checkoutParam: CheckoutParam){
        viewModelScope.launch {
            saveCardPreOrder.addSource(repo.checkoutPlaceOrder(checkoutParam)){
                when(it){
                    Result.Loading -> {
                        isLoading.value = true
                    }
                    is Result.Error -> {
                        error.value = Pair(it.message, it.code)
                    }
                    is Result.Success -> {
                        isLoading.value = false
                        saveCardPreOrder.value = it.data
                    }
                }
            }
        }
    }
}