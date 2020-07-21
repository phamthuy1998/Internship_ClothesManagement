package com.ptithcm.ptshop.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ptithcm.core.model.Brand
import com.ptithcm.core.param.BrandParam
import com.ptithcm.core.repository.DesignerRepository
import com.ptithcm.core.vo.Result
import kotlinx.coroutines.launch

class DesignerViewModel(private val repo: DesignerRepository): ViewModel() {

    val error = MutableLiveData<Pair<String, Int?>>()
    val networkState = MutableLiveData<Boolean>()

    val brandResult = MediatorLiveData<ArrayList<Brand>>()

    fun getPopularBrands(param: BrandParam){
        viewModelScope.launch {
            brandResult.addSource(repo.getPoupularBrands(param)){
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
                        brandResult.value = it.data?.results
                    }
                }
            }
        }
    }
}