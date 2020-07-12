package com.sg.core.repository.impl

import androidx.lifecycle.LiveData
import com.sg.core.api.ApiService
import com.sg.core.data.remote.NetworkBoundResource
import com.sg.core.model.Carousel
import com.sg.core.model.Categories
import com.sg.core.repository.ShopRepository
import com.sg.core.vo.ListResponse
import com.sg.core.vo.Result
import retrofit2.Response

class ShopRepositoryImpl(val api: ApiService) : ShopRepository {

    override suspend fun getCarousels(): LiveData<Result<ListResponse<Carousel>>> {
        return object : NetworkBoundResource<ListResponse<Carousel>, ListResponse<Carousel>>() {
            override fun processResponse(response: ListResponse<Carousel>) = response

            override suspend fun createCall(): Response<ListResponse<Carousel>> =
                api.getCarousels()
        }.build().asLiveData()
    }

    override suspend fun getMainCategories(): LiveData<Result<ListResponse<Categories>>> {
        return object : NetworkBoundResource<ListResponse<Categories>, ListResponse<Categories>>() {
            override fun processResponse(response: ListResponse<Categories>) = response
            override suspend fun createCall(): Response<ListResponse<Categories>> =
                api.getMainCategories()
        }.build().asLiveData()
    }

}