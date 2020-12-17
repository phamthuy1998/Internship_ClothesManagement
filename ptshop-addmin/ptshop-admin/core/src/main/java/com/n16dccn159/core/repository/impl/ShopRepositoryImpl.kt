package com.n16dccn159.core.repository.impl

import androidx.lifecycle.LiveData
import com.n16dccn159.core.api.ApiClothesService
import com.n16dccn159.core.data.remote.NetworkBoundResource
import com.n16dccn159.core.model.Category
import com.n16dccn159.core.repository.ShopRepository
import com.n16dccn159.core.vo.Result
import retrofit2.Response

class ShopRepositoryImpl(val apiClothes: ApiClothesService) : ShopRepository {
    override suspend fun getMainCategories(genderID: Int): LiveData<Result<ArrayList<Category>>> {
        return object : NetworkBoundResource<ArrayList<Category>, ArrayList<Category>>() {
            override fun processResponse(response: ArrayList<Category>) = response
            override suspend fun createCall(): Response<ArrayList<Category>> =
                apiClothes.getMainCategories(genderID)
        }.build().asLiveData()
    }
}