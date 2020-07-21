package com.ptithcm.core.repository.impl

import androidx.lifecycle.LiveData
import com.ptithcm.core.api.ApiService
import com.ptithcm.core.data.remote.NetworkBoundResource
import com.ptithcm.core.model.wish.WishResponse
import com.ptithcm.core.param.UploadProductParam
import com.ptithcm.core.param.WishListParam
import com.ptithcm.core.repository.UploadRepository
import com.ptithcm.core.vo.ApiResponse
import com.ptithcm.core.vo.Result
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