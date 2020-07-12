package com.sg.snapshop.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.sg.core.model.Brand
import com.sg.core.model.Gender
import com.sg.core.model.Stories
import com.sg.core.model.TypeCarousel
import com.sg.core.param.RefineParam
import com.sg.core.repository.CarouselDetailRepository
import com.sg.core.vo.ItemViewModel
import com.sg.core.vo.Result
import kotlinx.coroutines.launch

class CarouselDetailViewModel(private val repository: CarouselDetailRepository) : ViewModel() {

    val brandLiveData = MediatorLiveData<Triple<Brand, Gender, TypeCarousel>>()
    val storiesLiveData = MediatorLiveData<Triple<Brand, Gender, TypeCarousel>>()

    val vendorBrandLiveData = MediatorLiveData<Brand>()
    val storeBrandLiveData = MediatorLiveData<Brand>()

    val storesCarouselLiveData = MediatorLiveData<PagedList<Stories>>()
    val brandsProductDetailLiveData = MediatorLiveData<PagedList<ItemViewModel>>()
    val refineProductLiveData = MediatorLiveData<PagedList<ItemViewModel>>()
    val productsCategoriesLiveData = MediatorLiveData<PagedList<ItemViewModel>>()

    val networkState = MutableLiveData<Boolean>()
    val networkStateRefine = MutableLiveData<Boolean>()

    val error = MutableLiveData<Pair<String, Int?>>()
    val errorStorePaging = MutableLiveData<Pair<String, Int?>>()

    val productLoadStatusX = MutableLiveData<Result<ItemViewModel>>()

    fun getVendorBrand(brandId: Int?) {
        viewModelScope.launch {
            vendorBrandLiveData.addSource(repository.getVendorBrand(brandId)) {
                when (it) {
                    is Result.Loading -> {
                        networkState.value = true
                    }
                    is Result.Error -> {
                        networkState.value = false
                        error.value = Pair(it.message, it.code)
                    }
                    is Result.Success -> {
                        networkState.value = false
                        vendorBrandLiveData.value = it.data
                    }
                }
            }
        }
    }

    fun getStoreBrand(storeId: Int?) {
        viewModelScope.launch {
            storeBrandLiveData.addSource(repository.getStoreBrand(storeId)) {
                when (it) {
                    is Result.Loading -> {
                        networkState.value = true
                    }
                    is Result.Error -> {
                        networkState.value = false
                        error.value = Pair(it.message, it.code)
                    }
                    is Result.Success -> {
                        networkState.value = false
                        storeBrandLiveData.value = it.data
                    }
                }
            }
        }
    }

    fun getPagingBrandsProductDetail(vendorBrandId: List<String?>?, gender: Int?) {
        viewModelScope.launch {
            val mGender = if (gender != -1) gender else null
            val request = repository.getPagingBrandsProduct(vendorBrandId, mGender)
            brandsProductDetailLiveData.addSource(request.result) {
                brandsProductDetailLiveData.value = it
            }

            brandsProductDetailLiveData.addSource(request.status) {
                productLoadStatusX.value = it
            }
        }
    }

    fun getPagingStoresCarousel(brandId: Int?) {
        viewModelScope.launch {
            val request = repository.getPagingStoresCarousel(brandId)

            storesCarouselLiveData.addSource(request.result) {
                storesCarouselLiveData.value = it
            }

            storesCarouselLiveData.addSource(request.status) {
                when (it) {
                    is Result.Error -> {
                        errorStorePaging.value = Pair(it.message, it.code)
                    }
                }
            }
        }
    }

    fun getPagingProductsCategories(
        mainCategories: ArrayList<Int>?, categories: ArrayList<Int>?,
        filters: ArrayList<Int>?, gender: Int?
    ) {
        viewModelScope.launch {
            val request =
                repository.getPagingProductsCarousel(mainCategories, categories, filters, gender)
            productsCategoriesLiveData.addSource(request.result) {
                productsCategoriesLiveData.value = it
            }
            productsCategoriesLiveData.addSource(request.status) {
                productLoadStatusX.value = it
                when (it) {
                    is Result.Loading -> networkStateRefine.value = true
                    is Result.Error -> networkStateRefine.value = false
                    is Result.Success -> networkStateRefine.value = false
                }
            }

        }
    }

    fun getPagingRefineProduct(refineParam: RefineParam?) {
        viewModelScope.launch {
            val request = repository.getPagingRefineProduct(refineParam)
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