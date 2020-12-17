package com.n16dccn159.admin.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.n16dccn159.core.param.UploadProductParam
import com.n16dccn159.core.repository.UploadRepository
import com.n16dccn159.core.vo.Result
import kotlinx.coroutines.launch

class UploadViewModel(private val repo: UploadRepository): ViewModel() {

    val uploadLiveData = MediatorLiveData<Any>()
    val error = MutableLiveData<Pair<String, Int?>>()
    val networkState = MutableLiveData<Boolean>()

    fun uploadProduct(param: UploadProductParam){
        viewModelScope.launch {
            uploadLiveData.addSource(repo.uploadProduct(param)) {
                when (it) {
                    Result.Loading -> {
                        networkState.value = true
                    }
                    is Result.Error -> {
                        error.value = Pair(it.message, it.code)
                    }
                    is Result.Success -> {
                        uploadLiveData.value = it.data
                    }
                }
            }
        }
    }

}