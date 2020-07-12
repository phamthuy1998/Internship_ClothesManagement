package com.sg.snapshop.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sg.core.model.Product
import com.sg.core.repository.WishListRepository
import com.sg.core.util.ObjectHandler
import com.sg.core.vo.Result
import kotlinx.coroutines.launch

class WishListViewModel(val repo: WishListRepository): ViewModel(){
    val wishListResult = MediatorLiveData<ArrayList<Product>>()
    val removeResult = MediatorLiveData<String>()
    val addResult = MediatorLiveData<String>()
    val error = MutableLiveData<Pair<String, Int?>>()
    val networkState = MutableLiveData<Boolean>()

    fun getWishList(){
        viewModelScope.launch{
            wishListResult.addSource(repo.getWishList()){
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
                       ObjectHandler.addAllToWishListLocal(it.data?.map { it.id } as ArrayList<Int>)
                        wishListResult.value = it.data
                    }
                }
            }
        }
    }

    fun removeFromWishList(id: Int?){
        viewModelScope.launch {
            removeResult.addSource(repo.removeFromWishList(id)){
                when(it){
                    is Result.Loading -> {

                    }
                    is Result.Error -> {
                        error.value = Pair(it.message, it.code)
                    }
                    is Result.Success -> {
                        ObjectHandler.removeFromWishListLocal(id ?: return@addSource)
                        removeResult.value = it.data?.result
                        val arr = ArrayList<Product>(wishListResult.value?.toMutableList() ?: return@addSource)
                        arr.removeIf { prod -> prod.id == id }
                        wishListResult.value  = arr
                    }
                }
            }
        }
    }

    fun addToWishList(id: Int?){
        viewModelScope.launch {
            addResult.addSource(repo.addToWishList(id)){
                when(it){
                    is Result.Loading -> {}
                    is Result.Error -> {
                        error.value = Pair(it.message, it.code)
                    }
                    is Result.Success -> {
                        ObjectHandler.addToWishListLocal(id ?: return@addSource)
                        addResult.value = it.data?.result
                    }
                }
            }
        }
    }
}