package com.ptithcm.ptshop.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ptithcm.core.model.SupportedCurrency
import com.ptithcm.core.repository.CurrencyRepository
import com.ptithcm.core.vo.Result
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