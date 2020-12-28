package com.n16dccn159.admin.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.n16dccn159.core.model.Basket
import com.n16dccn159.core.model.CreditCard
import com.n16dccn159.core.model.ListLocation
import com.n16dccn159.core.model.Location
import com.n16dccn159.core.param.PaymentMethodParam
import com.n16dccn159.core.param.UpdateAddressParam
import com.n16dccn159.core.param.UpdatePaymentMethodParam
import com.n16dccn159.core.repository.PaymentRepository
import com.n16dccn159.core.vo.Result
import kotlinx.coroutines.launch

class PaymentViewModel(val repository: PaymentRepository) : ViewModel() {
    val getListLocationLiveData = MediatorLiveData<ListLocation>()
    val getLocationLiveData = MediatorLiveData<Location>()
    val updateAddressBookLiveData = MediatorLiveData<Basket>()
    val getPaymentMethodLiveData = MediatorLiveData<ArrayList<CreditCard?>>()
    val addPaymentMethodLiveData = MediatorLiveData<String>()
    val deletePaymentMethodLiveData = MediatorLiveData<Void>()
    val updatePaymentMethodLiveData = MediatorLiveData<CreditCard>()
    var error = MutableLiveData<Pair<String, Int?>>()

    fun getListLocation() {
        viewModelScope.launch {
            getListLocationLiveData.addSource(repository.getListLocation()) {
                when (it) {
                    is Result.Loading -> {
                    }
                    is Result.Error -> {
                        error.value = Pair(it.message, it.code)
                    }
                    is Result.Success -> {
                        getListLocationLiveData.value = it.data
                    }
                }
            }
        }
    }

    fun getLocation(id: Int) {
        viewModelScope.launch {
            getLocationLiveData.addSource(repository.getLocation(id)) {
                when (it) {
                    is Result.Loading -> {
                    }
                    is Result.Error -> {
                        error.value = Pair(it.message, it.code)
                    }
                    is Result.Success -> {
                        getLocationLiveData.value = it.data
                    }
                }
            }
        }
    }

    fun updateBookAddress(param: UpdateAddressParam) {
        viewModelScope.launch {
            updateAddressBookLiveData.addSource(repository.updateBookAddressPayment(param)) {
                when (it) {
                    is Result.Error -> {
                        error.value = Pair(it.message, it.code)
                    }
                    is Result.Success -> {
                        updateAddressBookLiveData.value = it.data
                    }
                }
            }
        }
    }

    fun getPaymentMethods() {
        viewModelScope.launch {
            getPaymentMethodLiveData.addSource(repository.getPaymentMethods()) {
                when (it) {
                    is Result.Error -> {
                        error.value = Pair(it.message, it.code)
                    }
                    is Result.Success -> {
                        getPaymentMethodLiveData.value = it.data
                    }
                }
            }
        }
    }

    fun addPaymentMethod(param: PaymentMethodParam) {
        viewModelScope.launch {
            addPaymentMethodLiveData.addSource(repository.addPaymentMethod(param)) {
                when (it) {
                    is Result.Error -> {
                        error.value = Pair(it.message, it.code)
                    }
                    is Result.Success -> {
                        addPaymentMethodLiveData.value = it.data
                    }
                }
            }
        }
    }

    fun deletePaymentMethod(cardID: String) {
        viewModelScope.launch {
            deletePaymentMethodLiveData.addSource(repository.deletePaymentMethod(cardID)) {
                when (it) {
                    is Result.Error -> {
                        error.value = Pair(it.message, it.code)
                    }
                    is Result.Success -> {
                        deletePaymentMethodLiveData.value = it.data
                    }
                }
            }
        }
    }

    fun updatePaymentMethod(cardID:String,param: UpdatePaymentMethodParam){
        viewModelScope.launch {
            updatePaymentMethodLiveData.addSource(repository.updatePaymentMethod(cardID,param)) {
                when (it) {
                    is Result.Error -> {
                        error.value = Pair(it.message, it.code)
                    }
                    is Result.Success -> {
                        updatePaymentMethodLiveData.value = it.data
                    }
                }
            }
        }
    }
}