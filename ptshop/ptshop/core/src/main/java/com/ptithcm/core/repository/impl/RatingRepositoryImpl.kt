package com.ptithcm.core.repository.impl

import androidx.lifecycle.LiveData
import com.ptithcm.core.api.ApiClothesService
import com.ptithcm.core.data.remote.NetworkBoundResource
import com.ptithcm.core.model.Question
import com.ptithcm.core.model.Rating
import com.ptithcm.core.model.wish.ObjectResponse
import com.ptithcm.core.repository.RatingRepository
import com.ptithcm.core.vo.ListResponse
import com.ptithcm.core.vo.Result
import retrofit2.Response

class RatingRepositoryImpl(val apiClothes: ApiClothesService) : RatingRepository{
    override suspend fun getRatings(productID:Int):LiveData<Result<ListResponse<Rating>>>{
        return object :
            NetworkBoundResource<ListResponse<Rating>, ListResponse<Rating>>() {
            override fun processResponse(response: ListResponse<Rating>) = response
            override suspend fun createCall(): Response<ListResponse<Rating>> =
                apiClothes.getRatings(productId = productID)
        }.build().asLiveData()
    }

    override suspend fun addRating(rating: Rating): LiveData<Result<ObjectResponse<Int>>> {
        return object : NetworkBoundResource<ObjectResponse<Int>, ObjectResponse<Int>>() {
            override fun processResponse(response: ObjectResponse<Int>) = response
            override suspend fun createCall(): Response<ObjectResponse<Int>> =
                apiClothes.addRating(rating)
        }.build().asLiveData()
    }

    override suspend fun updateRating(rating: Rating): LiveData<Result<ObjectResponse<Int>>> {
        return object : NetworkBoundResource<ObjectResponse<Int>, ObjectResponse<Int>>() {
            override fun processResponse(response: ObjectResponse<Int>) = response
            override suspend fun createCall(): Response<ObjectResponse<Int>> =
                apiClothes.updateRating(rating)
        }.build().asLiveData()
    }

    override suspend fun delRating(ratingID: Int): LiveData<Result<ObjectResponse<Int>>> {
        return object : NetworkBoundResource<ObjectResponse<Int>, ObjectResponse<Int>>() {
            override fun processResponse(response: ObjectResponse<Int>) = response
            override suspend fun createCall(): Response<ObjectResponse<Int>> =
                apiClothes.delRating(ratingID)
        }.build().asLiveData()
    }

    override suspend fun getRatingProduct(productID: Int): LiveData<Result<ObjectResponse<Float>>> {
        return object : NetworkBoundResource<ObjectResponse<Float>, ObjectResponse<Float>>() {
            override fun processResponse(response: ObjectResponse<Float>) = response
            override suspend fun createCall(): Response<ObjectResponse<Float>> =
                apiClothes.getRatingProduct(productID)
        }.build().asLiveData()
    }

}