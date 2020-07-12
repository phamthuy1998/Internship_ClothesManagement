package com.sg.snapshop.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sg.core.CoreApplication
import com.sg.core.model.Basket
import com.sg.core.model.Brand
import com.sg.core.model.Product
import com.sg.core.param.AddProductParam
import com.sg.core.param.ProductVariantParam
import com.sg.core.repository.ShoppingCardRepository
import com.sg.core.util.ObjectHandler
import com.sg.core.vo.MessageResponse
import com.sg.core.vo.Result
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ShoppingViewModel(val repo: ShoppingCardRepository): ViewModel() {

    val updateResult = MediatorLiveData<Basket>()
    val cardResult = MediatorLiveData<Basket>()
    val removeResult = MediatorLiveData<MessageResponse>()
    val detailResult = MediatorLiveData<Product>()
    val brandDetailResult = MediatorLiveData<Brand>()

    val error = MutableLiveData<Pair<String, Int?>>()
    val isLoading = MutableLiveData<Boolean>()

    fun updateBasket(param: AddProductParam){
        viewModelScope.launch {
            updateResult.addSource(repo.updateBasket(param)){
                when(it){
                    Result.Loading -> {
                        isLoading.value = true
                    }
                    is Result.Error -> {
                        isLoading.value = false
                        error.value = Pair(it.message, it.code)
                    }
                    is Result.Success -> {
                        CoreApplication.instance.saveBasket(it.data ?: return@addSource)
                        isLoading.value = false
                        updateResult.value = it.data
                    }
                }
            }
        }
    }

    fun getBasket(){
        viewModelScope.launch {
            cardResult.addSource(repo.getAllCard()){
                when(it){
                    Result.Loading -> {}
                    is Result.Error -> {}
                    is Result.Success ->{
                        CoreApplication.instance.saveBasket(it.data ?: return@addSource)
                        cardResult.value = it.data
                    }
                }
            }
        }
    }

    fun removeFromBasket(id: Long){
        viewModelScope.launch {
            removeResult.addSource(repo.removeFromBasket(id)){
                when(it){
                    Result.Loading -> {}
                    is Result.Error -> {
                        error.value = Pair(it.message, it.code)
                    }
                    is Result.Success -> {
                        ObjectHandler.removeProdFromBasket(id)
                        removeResult.value = it.data
                    }
                }
            }
        }
    }

    fun getProdDetail(id: Int){
        viewModelScope.launch {
            detailResult.addSource(repo.getProductDetail(id)){
                when(it){
                    Result.Loading -> {}
                    is Result.Error ->{
                        error.value = Pair(it.message, it.code)
                    }
                    is Result.Success -> {
                        detailResult.value = it.data
                    }
                }
            }
        }
    }

    fun getBrandDetail(id: Int?){
        viewModelScope.launch {
            brandDetailResult.addSource(repo.getShopDetail(id)){
                when(it){
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
                }            }
        }
    }

    fun updateBasketFromLocal(){
        runBlocking {
            launch {
                //just call this when first time login, after that no need
                if (CoreApplication.instance.basket != null){
                    return@launch
                }
                val listProdVariant = ObjectHandler.notLoginBasket
                val listProductParam = listProdVariant.map {
                    ProductVariantParam(
                        quantity = it.quantity,
                        product_variant = it.product_variant.id ?: 0
                    )
                }
                val addProductParam = AddProductParam(listProductParam as ArrayList<ProductVariantParam>)
                viewModelScope.launch {
                    updateResult.addSource(repo.updateBasket(addProductParam)){
                        when(it){
                            Result.Loading -> {
                                isLoading.value = true
                            }
                            is Result.Error -> {
                                isLoading.value = false
                                error.value = Pair(it.message, it.code)
                            }
                            is Result.Success -> {
                                CoreApplication.instance.clearLocalBasket(it.data)
                                isLoading.value = false
                                updateResult.value = it.data
                            }
                        }
                    }
                }
            }
        }
    }
}