package com.sg.core.repository.impl

import androidx.lifecycle.LiveData
import com.sg.core.api.ApiService
import com.sg.core.data.remote.NetworkBoundResource
import com.sg.core.data.remote.NetworkX
import com.sg.core.model.Brand
import com.sg.core.model.Stories
import com.sg.core.repository.BrandsRepository
import com.sg.core.vo.ListResponse
import com.sg.core.vo.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class BrandsRepositoryImpl(private val api : ApiService) : BrandsRepository {


    override suspend fun getBrandsX(page: Int?): LiveData<Result<ListResponse<Brand>>> = withContext(Dispatchers.IO) {

        return@withContext object : NetworkX<ListResponse<Brand>, ListResponse<Brand>>() {
            override fun processResponse(response: ListResponse<Brand>) = response
            override suspend fun createCall(): Response<ListResponse<Brand>> = api.getBrands(page)
        }.build().asLiveData()
    }
    override suspend fun getBrands(page : Int?): LiveData<Result<ListResponse<Brand>>> {

        return object : NetworkBoundResource<ListResponse<Brand>, ListResponse<Brand>>() {
            override fun processResponse(response: ListResponse<Brand>) = response

            override suspend fun createCall(): Response<ListResponse<Brand>> = api.getBrands(page)
        }.build().asLiveData()
    }

    override suspend fun getStories(page : Int?): LiveData<Result<ListResponse<Stories>>> {
        return object : NetworkBoundResource<ListResponse<Stories>, ListResponse<Stories>>() {
            override fun processResponse(response: ListResponse<Stories>) = response
            override suspend fun createCall(): Response<ListResponse<Stories>> = api.getStories(page)
        }.build().asLiveData()
    }

    override suspend fun getStoriesX(page: Int?): LiveData<Result<ListResponse<Stories>>> = withContext(Dispatchers.IO) {
        return@withContext object : NetworkX<ListResponse<Stories>, ListResponse<Stories>>() {
            override fun processResponse(response: ListResponse<Stories>) = response
            override suspend fun createCall(): Response<ListResponse<Stories>> = api.getStories(page)
        }.build().asLiveData()
    }
}