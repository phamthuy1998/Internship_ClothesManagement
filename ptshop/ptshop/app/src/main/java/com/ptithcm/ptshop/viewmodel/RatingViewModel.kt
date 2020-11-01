package com.ptithcm.ptshop.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ptithcm.core.model.Rating
import com.ptithcm.core.model.wish.ObjectResponse
import com.ptithcm.core.repository.RatingRepository
import com.ptithcm.core.vo.Result
import kotlinx.coroutines.launch

class RatingViewModel(private val repository: RatingRepository) : ViewModel() {

    val error = MutableLiveData<Pair<String, Int?>>()
    val networkState = MutableLiveData<Boolean>()

    val ratingList = MediatorLiveData<ArrayList<Rating>>()
    val addRatingResult = MediatorLiveData<ObjectResponse<Int>>()
    val updateRatingResult = MediatorLiveData<ObjectResponse<Int>>()
    val delRatingResult = MediatorLiveData<ObjectResponse<Int>>()
    val ratingAverage = MediatorLiveData<ObjectResponse<Float>>()

    fun getRatings(productID: Int) {
        viewModelScope.launch {
            ratingList.addSource(repository.getRatings(productID)) {
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
                        ratingList.value = it.data?.results
                    }
                }
            }
        }
    }

    fun addRating(rating: Rating) {
        viewModelScope.launch {
            addRatingResult.addSource(repository.addRating(rating)) {
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
                        addRatingResult.value = it.data
                    }
                }
            }
        }
    }

    fun updateRating(rating: Rating) {
        viewModelScope.launch {
            updateRatingResult.addSource(repository.updateRating(rating)) {
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
                        updateRatingResult.value = it.data
                    }
                }
            }
        }
    }

    fun delRating(ratingID: Int) {
        viewModelScope.launch {
            delRatingResult.addSource(repository.delRating(ratingID)) {
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
                        delRatingResult.value = it.data
                    }
                }
            }
        }
    }
    fun getRatingProduct(productId: Int) {
        viewModelScope.launch {
            ratingAverage.addSource(repository.getRatingProduct(productId)) {
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
                        ratingAverage.value = it.data
                    }
                }
            }
        }
    }
}