package com.ptithcm.ptshop.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ptithcm.core.model.Basket
import com.ptithcm.core.model.Brand
import com.ptithcm.core.model.ProductClothesDetail
import com.ptithcm.core.repository.ShoppingCardRepository
import com.ptithcm.core.vo.MessageResponse
import com.ptithcm.core.vo.Result
import kotlinx.coroutines.launch

class ShoppingViewModel(val repo: ShoppingCardRepository) : ViewModel() {

    val updateResult = MediatorLiveData<Basket>()
    val cardResult = MediatorLiveData<Basket>()
    val removeResult = MediatorLiveData<MessageResponse>()
    val brandDetailResult = MediatorLiveData<Brand>()

    val cartResult = MediatorLiveData<ArrayList<ProductClothesDetail>>()
    val detailResult = MediatorLiveData<ProductClothesDetail>()

    val error = MutableLiveData<Pair<String, Int?>>()
    val isLoading = MutableLiveData<Boolean>()

    fun getBasket() {
//        viewModelScope.launch {
//            cardResult.addSource(repo.getAllCard()) {
//                when (it) {
//                    Result.Loading -> {
//                    }
//                    is Result.Error -> {
//                    }
//                    is Result.Success -> {
//                        CoreApplication.instance.saveBasket(it.data ?: return@addSource)
//                        cardResult.value = it.data
//                    }
//                }
//            }
//        }
    }

    fun getAllProductsInCart(ids: List<Int>) {
        viewModelScope.launch {
            detailResult.addSource(repo.getAllProductsInCart(ids)) {
                when (it) {
                    Result.Loading -> {
                    }
                    is Result.Error -> {
                        error.value = Pair(it.message, it.code)
                    }
                    is Result.Success -> {
                        cartResult.value = it.data?.data
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

    fun getBrandDetail(id: Int?) {
        viewModelScope.launch {
            brandDetailResult.addSource(repo.getShopDetail(id)) {
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
                        brandDetailResult.value = it.data
                    }
                }
            }
        }
    }

}