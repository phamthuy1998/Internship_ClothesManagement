package com.n16dccn159.admin.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.n16dccn159.core.param.RequestCheckoutParam
import com.n16dccn159.core.repository.CheckoutRepository
import com.n16dccn159.core.vo.Result
import kotlinx.coroutines.launch

class CheckoutViewModel(private val repo: CheckoutRepository): ViewModel() {

    val resultRequestCheckout = MediatorLiveData<String>()

    val error = MutableLiveData<Pair<String, Int?>>()
    val isLoading = MutableLiveData<Boolean>()

    fun requestCheckout(checkoutParam: RequestCheckoutParam) {
        viewModelScope.launch {
            resultRequestCheckout.addSource(repo.requestCheckout(checkoutParam)) {
                when (it) {
                    Result.Loading -> {
                        isLoading.value = true
                    }
                    is Result.Error -> {
                        error.value = Pair(it.message, it.code)
                    }
                    is Result.Success -> {
                        isLoading.value = false
                        if (it.data?.status == true)
                            resultRequestCheckout.value = it.data?.message
                        else
                            error.value = Pair(it.data?.message ?: "", it.data?.code?.toInt())
                    }
                }
            }
        }
    }
}