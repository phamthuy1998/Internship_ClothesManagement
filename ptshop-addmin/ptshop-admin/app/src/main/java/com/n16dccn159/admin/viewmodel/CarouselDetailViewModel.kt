package com.n16dccn159.admin.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.n16dccn159.core.model.SearchParams
import com.n16dccn159.core.param.ProductsOfCategoryRequestParam
import com.n16dccn159.core.param.ProductsOfProviderRequestParam
import com.n16dccn159.core.repository.CarouselDetailRepository
import com.n16dccn159.core.vo.ItemViewModel
import com.n16dccn159.core.vo.Result
import kotlinx.coroutines.launch

class CarouselDetailViewModel(private val repository: CarouselDetailRepository) : ViewModel() {

    val networkState = MutableLiveData<Boolean>()
    val productLoadStatusX = MutableLiveData<Result<ItemViewModel>>()

    val productsCategoriesLiveData = MediatorLiveData<PagedList<ItemViewModel>>()
    fun getPagingProductsCategories(param: ProductsOfCategoryRequestParam?) {
        param ?: return
        viewModelScope.launch {
            val request =
                repository.getPagingProductsCarousel(param)
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
    fun getPagingProductsProvider(param: ProductsOfProviderRequestParam?) {
        param ?: return
        viewModelScope.launch {
            val request =
                repository.getPagingProductsProvider(param)
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
    fun searchPagingProducts(searchParams: SearchParams?) {
        searchParams ?: return
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