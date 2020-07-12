package com.sg.snapshop.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sg.core.model.CreditCard

class ListenerViewModel: ViewModel() {

    val updateShippingAddress = MutableLiveData<Boolean>()
    val changePayment = MutableLiveData<CreditCard>()
    val goToShop = MutableLiveData<Boolean>()

    fun setUpdate(){
        updateShippingAddress.value = true
    }

}