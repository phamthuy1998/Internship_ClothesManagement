package com.ptithcm.core.repository.impl

import androidx.lifecycle.LiveData
import com.ptithcm.core.api.ApiClothesService
import com.ptithcm.core.data.remote.NetworkBoundResource
import com.ptithcm.core.model.Category
import com.ptithcm.core.model.ShopInfo
import com.ptithcm.core.model.wish.ObjectResponse
import com.ptithcm.core.repository.ShopRepository
import com.ptithcm.core.vo.Result
import retrofit2.Response

class ShopRepositoryImpl(val apiClothes: ApiClothesService) : ShopRepository {
    override suspend fun getMainCategories(genderID: Int): LiveData<Result<ArrayList<Category>>> {
        return object : NetworkBoundResource<ArrayList<Category>, ArrayList<Category>>() {
            override fun processResponse(response: ArrayList<Category>) = response
            override suspend fun createCall(): Response<ArrayList<Category>> =
                apiClothes.getMainCategories(genderID)
        }.build().asLiveData()
    }

    override suspend fun getShopInfo(): LiveData<Result<ObjectResponse<ShopInfo>>> {
        return object : NetworkBoundResource<ObjectResponse<ShopInfo>,ObjectResponse<ShopInfo>>() {
            override fun processResponse(response: ObjectResponse<ShopInfo>) = response
            override suspend fun createCall(): Response<ObjectResponse<ShopInfo>> =
                apiClothes.getShopInfo()
        }.build().asLiveData()
    }
}