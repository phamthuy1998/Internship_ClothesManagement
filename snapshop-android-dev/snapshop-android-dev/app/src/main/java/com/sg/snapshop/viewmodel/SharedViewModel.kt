package com.sg.snapshop.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sg.core.model.Address

class SharedViewModel : ViewModel() {
    val addressLiveData = MutableLiveData<Address?>()

    fun setAddress(address: Address?) {
        addressLiveData.value = address
    }
}