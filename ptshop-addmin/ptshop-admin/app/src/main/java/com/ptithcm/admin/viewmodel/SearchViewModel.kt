package com.ptithcm.admin.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.ptithcm.core.param.RefineParam
import com.ptithcm.core.repository.SearchRepository
import com.ptithcm.core.vo.ItemViewModel
import com.ptithcm.core.vo.Result
import kotlinx.coroutines.launch

class SearchViewModel (private val repository: SearchRepository): ViewModel() {

    val searchProductLiveData = MediatorLiveData<PagedList<ItemViewModel>>()
    val refineProductLiveData = MediatorLiveData<PagedList<ItemViewModel>>()
    val productLoadStatusX = MutableLiveData<Result<ItemViewModel>>()

    fun getPagingSearchProduct(key:String?,sports:String?=null,styles:String?=null){
        viewModelScope.launch {
            val request = repository.getPagingSearchProduct(key,sports,styles)
            searchProductLiveData.addSource(request.result) {
                searchProductLiveData.value = it
            }
            searchProductLiveData.addSource(request.status) {
                productLoadStatusX.value = it
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
            }
        }
    }
}