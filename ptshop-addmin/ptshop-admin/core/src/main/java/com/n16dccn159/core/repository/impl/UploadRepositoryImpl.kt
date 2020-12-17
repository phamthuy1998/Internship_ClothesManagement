package com.n16dccn159.core.repository.impl

import androidx.lifecycle.LiveData
import com.n16dccn159.core.api.ApiService
import com.n16dccn159.core.data.remote.NetworkBoundResource
import com.n16dccn159.core.model.wish.WishResponse
import com.n16dccn159.core.param.UploadProductParam
import com.n16dccn159.core.param.WishListParam
import com.n16dccn159.core.repository.UploadRepository
import com.n16dccn159.core.vo.ApiResponse
import com.n16dccn159.core.vo.Result
import retrofit2.Response


class UploadRepositoryImpl(val api: ApiService): UploadRepository {
    override suspend fun addToWishList(id: Int?): LiveData<Result<WishResponse>> {
        return object : NetworkBoundResource<WishResponse, WishResponse>(){
            override fun processResponse(response: WishResponse) = response
            override suspend fun createCall(): Response<WishResponse> = api.addToWishList(
                WishListParam(id)
            )
        }.build().asLiveData()
    }

    override suspend fun uploadProduct(param: UploadProductParam): LiveData<Result<String>> {
        return object : NetworkBoundResource<ApiResponse<String>, String>(){
            override fun processResponse(response: ApiResponse<String>) = response.detail
            override suspend fun createCall() = api.uploadProduct(param)
        }.build().asLiveData()
    }
}