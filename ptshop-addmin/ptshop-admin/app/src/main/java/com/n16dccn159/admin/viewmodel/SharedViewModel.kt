package com.n16dccn159.admin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.n16dccn159.core.model.Address

class SharedViewModel : ViewModel() {
    val addressLiveData = MutableLiveData<Address?>()

    fun setAddress(address: Address?) {
        addressLiveData.value = address
    }
}