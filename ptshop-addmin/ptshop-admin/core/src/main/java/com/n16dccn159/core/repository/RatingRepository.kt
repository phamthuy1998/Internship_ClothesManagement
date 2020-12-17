package com.n16dccn159.core.repository

import androidx.lifecycle.LiveData
import com.n16dccn159.core.model.Rating
import com.n16dccn159.core.model.RatingProduct
import com.n16dccn159.core.model.wish.ObjectResponse
import com.n16dccn159.core.vo.ListResponse
import com.n16dccn159.core.vo.Result

interface RatingRepository {
    suspend fun getRatings(productID: Int): LiveData<Result<ListResponse<Rating>>>
    suspend fun addRating(rating: Rating): LiveData<Result<ObjectResponse<Int>>>
    suspend fun updateRating(rating: Rating): LiveData<Result<ObjectResponse<Int>>>
    suspend fun delRating(ratingID: Int): LiveData<Result<ObjectResponse<Int>>>
    suspend fun checkRating(productId: Int, accID: Int, colorId: Int, sizeId: Int ,orderId: Int?): LiveData<Result<ObjectResponse<Int>>>
    suspend fun getRatingProduct(productID: Int): LiveData<Result<ObjectResponse<RatingProduct>>>
    suspend fun getRatingDetail(ratingID: Int): LiveData<Result<ObjectResponse<Rating>>>
}