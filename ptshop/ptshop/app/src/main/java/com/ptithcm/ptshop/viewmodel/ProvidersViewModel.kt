package com.ptithcm.ptshop.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ptithcm.core.model.Provider
import com.ptithcm.core.repository.ProvidersRepository
import com.ptithcm.core.vo.Result
import kotlinx.coroutines.launch

class ProvidersViewModel(private val repo: ProvidersRepository) : ViewModel() {
    var provider: Provider? = null

    val error = MutableLiveData<Pair<String, Int?>>()
    val isLoading = MutableLiveData<Boolean>()

    val providersResult = MediatorLiveData<ArrayList<Provider>>()
    fun getProviders() {
        viewModelScope.launch {
            providersResult.addSource(repo.getProviders()) {
                when (it) {
                    Result.Loading -> {
                        isLoading.value = true
                    }
                    is Result.Error -> {
                        isLoading.value = false
                        error.value = Pair(it.message, it.code)
                    }
                    is Result.Success -> {
                        isLoading.value = false
                        providersResult.postValue(it.data)
                    }
                }
            }
        }
    }

    val providerDetailResult = MediatorLiveData<Provider>()
    fun getDetailProvider(providerId: Int?) {
        viewModelScope.launch {
            providerDetailResult.addSource(repo.getDetailProvider(providerId)) {
                when (it) {
                    Result.Loading -> {
                        isLoading.value = true
                    }
                    is Result.Error -> {
                        isLoading.value = false
                        error.value = Pair(it.message, it.code)
                    }
                    is Result.Success -> {
                        providerDetailResult.postValue(it.data)
                    }
                }
            }
        }
    }
}