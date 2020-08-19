package com.ptithcm.ptshop.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.ptithcm.core.model.SearchParams
import com.ptithcm.core.repository.CarouselDetailRepository
import com.ptithcm.core.vo.ItemViewModel
import com.ptithcm.core.vo.Result
import kotlinx.coroutines.launch

class CarouselDetailViewModel(private val repository: CarouselDetailRepository) : ViewModel() {

    val networkState = MutableLiveData<Boolean>()
    val productLoadStatusX = MutableLiveData<Result<ItemViewModel>>()

    val productsCategoriesLiveData = MediatorLiveData<PagedList<ItemViewModel>>()
    fun getPagingProductsCategories(
        categoryID: Int,
        pageSize: Int,
        pageNumber: Int,
        accountId: Int
    ) {
        viewModelScope.launch {
            val request =
                repository.getPagingProductsCarousel(categoryID, pageSize, pageNumber, accountId)
            productsCategoriesLiveData.addSource(request.result) {
                productsCategoriesLiveData.value = it
            }
            productsCategoriesLiveData.addSource(request.status) {
                productLoadStatusX.value = it
                when (it) {
                    is Result.Loading -> {
                        networkState.value = true
                    }
                    is Result.Error -> {
                        networkState.value = false
                    }
                    is Result.Success -> {
                        networkState.value = false
                    }
                }
            }
        }
    }

    val productsProviderLiveData = MediatorLiveData<PagedList<ItemViewModel>>()
    fun getPagingProductsProvider(
        providerId: Int,
        pageSize: Int,
        pageNumber: Int,
        accountId: Int
    ) {
        viewModelScope.launch {
            val request =
                repository.getPagingProductsProvider(providerId, pageSize, pageNumber, accountId)
            productsProviderLiveData.addSource(request.result) {
                productsProviderLiveData.value = it
            }
            productsProviderLiveData.addSource(request.status) {
                productLoadStatusX.value = it
                when (it) {
                    is Result.Loading -> {
                        networkState.value = true
                    }
                    is Result.Error -> {
                        networkState.value = false
                    }
                    is Result.Success -> {
                        networkState.value = false
                    }
                }
            }
        }
    }

    val refineProductLiveData = MediatorLiveData<PagedList<ItemViewModel>>()
    val networkStateRefine = MutableLiveData<Boolean>()
    fun getPagingRefineProduct(searchParams: SearchParams?) {
        viewModelScope.launch {
            val request = repository.getPagingRefineProduct(searchParams)
            refineProductLiveData.addSource(request.result) {
                refineProductLiveData.value = it
            }
            refineProductLiveData.addSource(request.status) {
                productLoadStatusX.value = it
                when (it) {
                    is Result.Loading -> {
                        networkStateRefine.value = true
                    }
                    is Result.Error -> {
                        networkStateRefine.value = false
                    }
                    is Result.Success -> {
                        networkStateRefine.value = false
                    }
                }
            }
        }
    }

}