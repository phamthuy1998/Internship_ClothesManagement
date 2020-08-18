package com.ptithcm.ptshop.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ptithcm.core.model.ProductClothes
import com.ptithcm.core.model.wish.ObjectResponse
import com.ptithcm.core.repository.WishListRepository
import com.ptithcm.core.vo.Result
import kotlinx.coroutines.launch

class WishListViewModel(val repo: WishListRepository): ViewModel() {
    val wishListResult = MediatorLiveData<ArrayList<ProductClothes>>()
    val addAndRemoveResult = MediatorLiveData<ObjectResponse<Int>>()
    val error = MutableLiveData<Pair<String, Int?>>()
    val networkState = MutableLiveData<Boolean>()

    fun getWishList() {
        viewModelScope.launch {
            wishListResult.addSource(repo.getWishList()) {
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
                        wishListResult.value = it.data?.data
                    }
                }
            }
        }
    }

    fun addAndRemoveToWishList(id: Int?) {
        viewModelScope.launch {
            addAndRemoveResult.addSource(repo.addAndRemoveToWishList(id)) {
                when (it) {
                    is Result.Loading -> {
                    }
                    is Result.Error -> {
                        error.value = Pair(it.message, it.code)
                    }
                    is Result.Success -> {
                        if (it.data?.data != 1) {
                            val arr = ArrayList<ProductClothes>(
                                wishListResult.value?.toMutableList() ?: return@addSource
                            )
                            arr.removeIf { prod -> prod.id == id }
                            wishListResult.value = arr
                        }
                        addAndRemoveResult.value = it.data
                    }
                }
            }
        }
    }
}