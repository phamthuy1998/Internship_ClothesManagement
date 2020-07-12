package com.sg.snapshop.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sg.core.model.Brand
import com.sg.core.param.BrandParam
import com.sg.core.repository.DesignerRepository
import com.sg.core.vo.Result
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