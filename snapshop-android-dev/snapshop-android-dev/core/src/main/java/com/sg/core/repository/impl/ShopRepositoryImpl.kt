package com.sg.core.repository.impl

import androidx.lifecycle.LiveData
import com.sg.core.api.ApiClothesService
import com.sg.core.api.ApiService
import com.sg.core.data.remote.NetworkBoundResource
import com.sg.core.model.Carousel
import com.sg.core.model.Category
import com.sg.core.repository.ShopRepository
import com.sg.core.vo.ListResponse
import com.sg.core.vo.Result
import retrofit2.Response

class ShopRepositoryImpl(val api: ApiService, val apiClothes: ApiClothesService) : ShopRepository {

    override suspend fun getCarousels(): LiveData<Result<ListResponse<Carousel>>> {
        return object : NetworkBoundResource<ListResponse<Carousel>, ListResponse<Carousel>>() {
            override fun processResponse(response: ListResponse<Carousel>) = response

            override suspend fun createCall(): Response<ListResponse<Carousel>> =
                api.getCarousels()
        }.build().asLiveData()
    }

    override suspend fun getMainCategories(genderID: Int): LiveData<Result<ArrayList<Category>>> {
        return object : NetworkBoundResource<ArrayList<Category>, ArrayList<Category>>() {
            override fun processResponse(response: ArrayList<Category>) = response
            override suspend fun createCall(): Response<ArrayList<Category>> =
                apiClothes.getMainCategories(genderID)
        }.build().asLiveData()
    }

}