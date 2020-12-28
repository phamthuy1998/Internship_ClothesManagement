package com.ptithcm.core.repository.impl

import androidx.lifecycle.LiveData
import com.ptithcm.core.api.ApiClothesService
import com.ptithcm.core.data.remote.NetworkBoundResource
import com.ptithcm.core.model.Rating
import com.ptithcm.core.model.RatingProduct
import com.ptithcm.core.model.wish.ObjectResponse
import com.ptithcm.core.repository.RatingRepository
import com.ptithcm.core.vo.ListResponse
import com.ptithcm.core.vo.Result
import retrofit2.Response

class RatingRepositoryImpl(val apiClothes: ApiClothesService) : RatingRepository {
    override suspend fun getRating(ratingID: Int): LiveData<Result<ObjectResponse<Rating>>> {
        return object :
            NetworkBoundResource<ObjectResponse<Rating>, ObjectResponse<Rating>>() {
            override fun processResponse(response: ObjectResponse<Rating>) = response
            override suspend fun createCall(): Response<ObjectResponse<Rating>> =
                apiClothes.getRating(ratingID)
        }.build().asLiveData()
    }

    override suspend fun getRatings(productID: Int): LiveData<Result<ListResponse<Rating>>> {
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

    override suspend fun checkRating(
        productId: Int,
        accID: Int, colorId: Int, sizeId: Int,orderId: Int?
    ): LiveData<Result<ObjectResponse<Int>>> {
        return object : NetworkBoundResource<ObjectResponse<Int>, ObjectResponse<Int>>() {
            override fun processResponse(response: ObjectResponse<Int>) = response
            override suspend fun createCall(): Response<ObjectResponse<Int>> =
                apiClothes.checkRatingProduct(productId, accID, colorId, sizeId,orderId)
        }.build().asLiveData()
    }

    override suspend fun getRatingProduct(productID: Int): LiveData<Result<ObjectResponse<RatingProduct>>> {
        return object :
            NetworkBoundResource<ObjectResponse<RatingProduct>, ObjectResponse<RatingProduct>>() {
            override fun processResponse(response: ObjectResponse<RatingProduct>) = response
            override suspend fun createCall(): Response<ObjectResponse<RatingProduct>> =
                apiClothes.getRatingProduct(productID)
        }.build().asLiveData()
    }

    override suspend fun getRatingDetail(ratingID: Int): LiveData<Result<ObjectResponse<Rating>>> {
        return object :
            NetworkBoundResource<ObjectResponse<Rating>, ObjectResponse<Rating>>() {
            override fun processResponse(response: ObjectResponse<Rating>) = response
            override suspend fun createCall(): Response<ObjectResponse<Rating>> =
                apiClothes.getRatingDetail(ratingID)
        }.build().asLiveData()
    }

}