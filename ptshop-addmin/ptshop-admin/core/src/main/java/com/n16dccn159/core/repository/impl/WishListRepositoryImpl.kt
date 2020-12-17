package com.n16dccn159.core.repository.impl

import androidx.lifecycle.LiveData
import com.n16dccn159.core.api.ApiClothesService
import com.n16dccn159.core.data.remote.NetworkBoundResource
import com.n16dccn159.core.model.ProductClothes
import com.n16dccn159.core.model.wish.ObjectResponse
import com.n16dccn159.core.repository.WishListRepository
import com.n16dccn159.core.vo.Result
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