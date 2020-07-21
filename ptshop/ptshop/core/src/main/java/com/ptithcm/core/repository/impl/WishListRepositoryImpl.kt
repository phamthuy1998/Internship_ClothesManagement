package com.ptithcm.core.repository.impl

import androidx.lifecycle.LiveData
import com.ptithcm.core.api.ApiService
import com.ptithcm.core.data.remote.NetworkBoundResource
import com.ptithcm.core.model.Product
import com.ptithcm.core.model.wish.WishResponse
import com.ptithcm.core.param.WishListParam
import com.ptithcm.core.repository.WishListRepository
import com.ptithcm.core.vo.Result
import retrofit2.Response

class WishListRepositoryImpl(val api: ApiService): WishListRepository {

    override suspend fun getWishList(): LiveData<Result<ArrayList<Product>>> {
        return object : NetworkBoundResource<ArrayList<Product>, ArrayList<Product>>(){
            override suspend fun createCall(): Response<ArrayList<Product>> = api.getAllWishList()

            override fun processResponse(response: ArrayList<Product>): ArrayList<Product>? {
                return response.map {
                    it.apply {
                        variants?.sortBy { variant -> variant.position }
                        checkVariantWrongPrice()
                        isAddProduct = true
                    }
                    it
                } as ArrayList<Product>
            }
        }.build().asLiveData()
    }

    override suspend fun addToWishList(id: Int?): LiveData<Result<WishResponse>> {
        return object : NetworkBoundResource<WishResponse, WishResponse>(){
            override fun processResponse(response: WishResponse) = response
            override suspend fun createCall(): Response<WishResponse> = api.addToWishList(WishListParam(id))
        }.build().asLiveData()
    }

    override suspend fun removeFromWishList(id: Int?): LiveData<Result<WishResponse>> {
        return object : NetworkBoundResource<WishResponse, WishResponse>(){
            override fun processResponse(response: WishResponse) = response
            override suspend fun createCall(): Response<WishResponse> = api.removeFromWishList(id)
        }.build().asLiveData()
    }
}