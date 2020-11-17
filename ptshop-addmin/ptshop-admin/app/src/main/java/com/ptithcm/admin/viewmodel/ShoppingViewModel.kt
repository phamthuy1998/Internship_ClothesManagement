package com.ptithcm.admin.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ptithcm.core.model.ProductClothesDetail
import com.ptithcm.core.repository.ShoppingCardRepository
import com.ptithcm.core.util.ObjectHandler
import com.ptithcm.core.vo.Result
import kotlinx.coroutines.launch

class ShoppingViewModel(val repo: ShoppingCardRepository) : ViewModel() {
    val cartResult = MediatorLiveData<ArrayList<ProductClothesDetail>>()
    val detailResult = MediatorLiveData<ProductClothesDetail>()

    val error = MutableLiveData<Pair<String, Int?>>()
    val isLoading = MutableLiveData<Boolean>()

    fun getAllProductsInCart(ids: List<Int>) {
        viewModelScope.launch {
            cartResult.addSource(repo.getAllProductsInCart(ids)) {
                when (it) {
                    Result.Loading -> {
                    }
                    is Result.Error -> {
                        error.value = Pair(it.message, it.code)
                    }
                    is Result.Success -> {
                        if (!it.data.isNullOrEmpty()) {
                            ObjectHandler.cart?.products = it.data ?: arrayListOf()
                            ObjectHandler.saveCartToPref()
                        }
                        cartResult.value = it.data
                    }
                }
            }
        }
    }


    fun getProdDetail(id: Int) {
        viewModelScope.launch {
            detailResult.addSource(repo.getProductDetail(id)) {
                when (it) {
                    Result.Loading -> {
                    }
                    is Result.Error -> {
                        error.value = Pair(it.message, it.code)
                    }
                    is Result.Success -> {
                        detailResult.value = it.data?.data
                    }
                }
            }
        }
    }
}