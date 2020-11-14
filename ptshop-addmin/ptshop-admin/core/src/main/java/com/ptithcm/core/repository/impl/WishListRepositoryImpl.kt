package com.ptithcm.core.repository.impl

import androidx.lifecycle.LiveData
import com.ptithcm.core.api.ApiClothesService
import com.ptithcm.core.data.remote.NetworkBoundResource
import com.ptithcm.core.model.ProductClothes
import com.ptithcm.core.model.wish.ObjectResponse
import com.ptithcm.core.repository.WishListRepository
import com.ptithcm.core.vo.Result
import retrofit2.Response

class WishListRepositoryImpl(val apiClothes: ApiClothesService) : WishListRepository {

    override suspend fun getWishList(): LiveData<Result<ObjectResponse<ArrayList<ProductClothes>>>> {
        return object :
            NetworkBoundResource<ObjectResponse<ArrayList<ProductClothes>>, ObjectResponse<ArrayList<ProductClothes>>>() {
            override suspend fun createCall(): Response<ObjectResponse<ArrayList<ProductClothes>>> =
                apiClothes.getAllWishList()

            override fun processResponse(response: ObjectResponse<ArrayList<ProductClothes>>): ObjectResponse<ArrayList<ProductClothes>>? {
                return response
            }
        }.build().asLiveData()
    }

    override suspend fun addAndRemoveToWishList(id: Int?): LiveData<Result<ObjectResponse<Int>>> {
        return object : NetworkBoundResource<ObjectResponse<Int>, ObjectResponse<Int>>() {
            override fun processResponse(response: ObjectResponse<Int>) = response
            override suspend fun createCall(): Response<ObjectResponse<Int>> =
                apiClothes.addAndRemoveToWishList(id)
        }.build().asLiveData()
    }
}