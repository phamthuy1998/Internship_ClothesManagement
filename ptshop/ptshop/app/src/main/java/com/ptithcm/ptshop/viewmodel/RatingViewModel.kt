package com.ptithcm.ptshop.viewmodel

import android.net.Uri
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.UploadTask
import com.google.firebase.storage.ktx.storage
import com.ptithcm.core.model.Rating
import com.ptithcm.core.model.RatingProduct
import com.ptithcm.core.model.wish.ObjectResponse
import com.ptithcm.core.repository.RatingRepository
import com.ptithcm.core.vo.Result
import kotlinx.coroutines.launch
import java.util.*

const val RATING = "rating"

class RatingViewModel(private val repository: RatingRepository) : ViewModel() {

    val error = MutableLiveData<Pair<String, Int?>>()
    val networkState = MutableLiveData<Boolean>()

    val ratingList = MediatorLiveData<ArrayList<Rating>>()
    val addRatingResult = MediatorLiveData<ObjectResponse<Int>>()
    val updateRatingResult = MediatorLiveData<ObjectResponse<Int>>()
    val checkRating = MediatorLiveData<ObjectResponse<Int>>()
    val delRatingResult = MediatorLiveData<ObjectResponse<Int>>()
    val ratingAverage = MediatorLiveData<ObjectResponse<RatingProduct>>()
    val ratingDetail = MediatorLiveData<ObjectResponse<Rating>>()
    val ratingResponse= MediatorLiveData<Rating>()

    var urlImage1 = MutableLiveData<String>()
    var urlImage2 = MutableLiveData<String>()
    var urlVideo = MutableLiveData<String>()

    fun getRating(ratingID: Int){
        viewModelScope.launch {
            ratingResponse.addSource(repository.getRating(ratingID)) {
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
                        ratingResponse.value = it.data?.data
                    }
                }
            }
        }

    }
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

    fun uploadFirebase(uri: Uri?, pos: Int, urlDefault: String?) {
        networkState.value = true
        if (uri == null) {
            when (pos) {
                1 -> urlImage1.value =urlDefault?: null
                2 -> urlImage2.value = urlDefault?: null
                else -> urlVideo.value = urlDefault?: null
            }
            return
        }
        viewModelScope.launch {
            var url: String? = null
            val ref =
                Firebase.storage.reference.child(RATING).child(UUID.randomUUID().toString())
            val uploadTask = ref.putFile(uri)

            uploadTask.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {
                        error.value = Pair(it.message ?: "", 0)
                    }
                }
                return@Continuation ref.downloadUrl
            }).addOnCompleteListener { task ->
                url = if (task.isSuccessful) task.result.toString() else null
                when (pos) {
                    1 -> urlImage1.value = url
                    2 -> urlImage2.value = url
                    else -> urlVideo.value = url
                }
                networkState.value = false
            }.addOnFailureListener { err ->
                error.value = Pair(err.message ?: "", 0)
                networkState.value = false
            }
        }
    }

    fun addRating(rating: Rating?) {
        if (rating == null) {
            error.value = Pair("Lỗi add rating", 0)
            return
        }
        viewModelScope.launch {
            rating.apply {
                imageUrl1 = urlImage1.value
                imageUrl2 = urlImage2.value
                videoUrl = urlVideo.value
            }
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

    fun updateRating(rating: Rating?) {
        if (rating == null) {
            error.value = Pair("Lỗi update rating", 0)
            return
        }
        viewModelScope.launch {
            rating.apply {
                imageUrl1 = urlImage1.value
                imageUrl2 = urlImage2.value
                videoUrl = urlVideo.value
            }
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

    fun checkRating(productId: Int, accID: Int, colorId: Int, sizeId: Int, orderId: Int) {
        viewModelScope.launch {
            checkRating.addSource(repository.checkRating(productId, accID, colorId, sizeId, orderId)) {
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
                        checkRating.value = it.data
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

    fun getRatingDetail(ratingId: Int) {
        viewModelScope.launch {
            ratingDetail.addSource(repository.getRatingDetail(ratingId)) {
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
                        ratingDetail.value = it.data
                    }
                }
            }
        }
    }
}