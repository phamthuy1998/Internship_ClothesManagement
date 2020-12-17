package com.n16dccn159.admin.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.n16dccn159.core.model.Product
import com.n16dccn159.core.repository.ProductFilterRepository
import com.n16dccn159.core.vo.ListResponse
import com.n16dccn159.core.vo.Result
import kotlinx.coroutines.launch

class AddProductViewModel(private val repo: ProductFilterRepository) : ViewModel() {

    val networkState = MutableLiveData<Boolean>()
    val productLiveData = MediatorLiveData<ListResponse<Product>>()
    val error = MutableLiveData<Pair<String, Int?>>()

    fun searchProduct(name: String?, page: Int?) {
        viewModelScope.launch {
            productLiveData.addSource(repo.searchProduct(name, page)){
                when(it){
                    is Result.Loading -> {
                        networkState.value = true
                    }
                    is Result.Error -> {
                        networkState.value = false
                        error.value = Pair(it.message, it.code)
                    }
                    is Result.Success -> {
                        networkState.value = false
                        productLiveData.value = it.data
                    }
                }
            }
        }
    }

}