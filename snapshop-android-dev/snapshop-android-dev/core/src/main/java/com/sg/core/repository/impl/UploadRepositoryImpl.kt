package com.sg.core.repository.impl

import androidx.lifecycle.LiveData
import com.sg.core.api.ApiService
import com.sg.core.data.remote.NetworkBoundResource
import com.sg.core.model.wish.WishResponse
import com.sg.core.param.UploadProductParam
import com.sg.core.param.WishListParam
import com.sg.core.repository.UploadRepository
import com.sg.core.vo.ApiResponse
import com.sg.core.vo.Result
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