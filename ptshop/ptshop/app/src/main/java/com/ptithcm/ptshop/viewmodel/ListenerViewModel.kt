package com.ptithcm.ptshop.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ptithcm.core.model.CreditCard

class ListenerViewModel: ViewModel() {

    val updateShippingAddress = MutableLiveData<Boolean>()
    val changePayment = MutableLiveData<CreditCard>()
    val goToShop = MutableLiveData<Boolean>()

    fun setUpdate(){
        updateShippingAddress.value = true
    }

}