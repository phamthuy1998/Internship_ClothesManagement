package com.ptithcm.core.repository

import androidx.lifecycle.LiveData
import com.ptithcm.core.model.Rating
import com.ptithcm.core.model.RatingProduct
import com.ptithcm.core.model.wish.ObjectResponse
import com.ptithcm.core.vo.ListResponse
import com.ptithcm.core.vo.Result

interface RatingRepository {
    suspend fun getRating(ratingID: Int): LiveData<Result<ObjectResponse<Rating>>>
    suspend fun getRatings(productID: Int): LiveData<Result<ListResponse<Rating>>>
    suspend fun addRating(rating: Rating): LiveData<Result<ObjectResponse<Int>>>
    suspend fun updateRating(rating: Rating): LiveData<Result<ObjectResponse<Int>>>
    suspend fun delRating(ratingID: Int): LiveData<Result<ObjectResponse<Int>>>
    suspend fun checkRating(productId: Int, accID: Int, colorId: Int, sizeId: Int ,orderId: Int?): LiveData<Result<ObjectResponse<Int>>>
    suspend fun getRatingProduct(productID: Int): LiveData<Result<ObjectResponse<RatingProduct>>>
    suspend fun getRatingDetail(ratingID: Int): LiveData<Result<ObjectResponse<Rating>>>
}