package com.ptithcm.admin.viewmodel

import androidx.lifecycle.*
import androidx.lifecycle.Transformations.switchMap
import com.ptithcm.core.model.Gender
import com.ptithcm.core.model.MainCategories
import com.ptithcm.core.model.ProductsFilter
import com.ptithcm.core.param.CategoriesRefine
import com.ptithcm.core.repository.ProductFilterRepository
import com.ptithcm.core.vo.Result
import com.ptithcm.admin.ext.getArrayItemCategories
import com.ptithcm.admin.ext.getCategoriesRefine
import com.ptithcm.admin.ext.getMainCategories
import kotlinx.coroutines.launch

class ProductFilterViewModel(private val repository: ProductFilterRepository) : ViewModel() {

    val productFilterLiveData = MediatorLiveData<ProductsFilter>()
    private val request = MutableLiveData<Gender?>()
    private val requestMainCategories = MutableLiveData<Pair<CategoriesRefine, Gender>>()
    private val requestCategories = MutableLiveData<Pair<CategoriesRefine, Gender>>()

    val allCategoriesLiveData: LiveData<ArrayList<MainCategories>> = switchMap(request) {
        switchLiveData(productFilterLiveData, it ?: Gender.WOMEN)
    }
    val allCategoriesRefineLiveData: LiveData<ArrayList<MainCategories>> = switchMap(request) {
        switchLiveData(productFilterLiveData, null)
    }
    val categoriesMainRefineLiveData: LiveData<ArrayList<MainCategories>> =
        switchMap(requestMainCategories) {
            switchCategoriesMainLiveData(productFilterLiveData, it.first, it.second)
        }
    val categoriesRefineLiveData: LiveData<ArrayList<MainCategories>> =
        switchMap(requestCategories) {
            switchCategoriesLiveData(productFilterLiveData, it.first, it.second)
        }
    val error = MutableLiveData<String>()

    fun getProductFilter() {
        viewModelScope.launch {
            productFilterLiveData.addSource(repository.getProductFilter()) {
                when (it) {
                    is Result.Loading -> {
                    }
                    is Result.Error -> {
                        error.value = it.message
                    }
                    is Result.Success -> {
                        productFilterLiveData.value = it.data
                    }
                }
            }
        }
    }

    fun requestAllCategories(type: Gender?) {
        request.value = type
    }

    fun requestMainCategories(categoriesRefine: CategoriesRefine, gender: Gender) {
        requestMainCategories.value =  Pair(categoriesRefine, gender)
    }

    fun requestCategories(categoriesRefine: CategoriesRefine, gender: Gender) {
        requestCategories.value = Pair(categoriesRefine, gender)
    }

    private fun switchLiveData(
        liveData: LiveData<ProductsFilter>, type: Gender?
    ): LiveData<ArrayList<MainCategories>> {
        return switchMap(liveData) {
            val results = getMainCategories(type, liveData.value)
            val data = MutableLiveData<ArrayList<MainCategories>>()
            data.value = results
            return@switchMap data
        }
    }

    private fun switchCategoriesMainLiveData(
        liveData: LiveData<ProductsFilter>,
        param: CategoriesRefine,
        gender: Gender
    ): LiveData<ArrayList<MainCategories>> {
        return switchMap(liveData) {
            val mainCategories = getMainCategories(gender, liveData.value) ?: arrayListOf()
            val results = mainCategories.getArrayItemCategories(param)
            val data = MutableLiveData<ArrayList<MainCategories>>()
            data.value = results
            return@switchMap data
        }
    }

    private fun switchCategoriesLiveData(
        liveData: LiveData<ProductsFilter>,
        param: CategoriesRefine,
        gender: Gender
    ): LiveData<ArrayList<MainCategories>> {
        return switchMap(liveData) {
            val results = getCategoriesRefine(liveData.value, param, gender)
            val data = MutableLiveData<ArrayList<MainCategories>>()
            data.value = results
            return@switchMap data
        }
    }

}