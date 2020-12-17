package com.n16dccn159.admin.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.n16dccn159.core.model.SupportedCurrency
import com.n16dccn159.core.repository.CurrencyRepository
import com.n16dccn159.core.vo.Result
import kotlinx.coroutines.launch

class CurrencyViewModel(private val repository: CurrencyRepository): ViewModel() {

    val currencyLiveData = MediatorLiveData<ArrayList<SupportedCurrency>>()

    fun getSupportedCurrency(){
        viewModelScope.launch {
            currencyLiveData.addSource(repository.getAllSupportedCurrency()){
                when(it){
                    is Result.Loading, is Result.LoadingMore -> {}
                    is Result.Error -> {}
                    is Result.Success -> {
                        currencyLiveData.value = it.data
                    }
                }
            }
        }
    }

}