package com.n16dccn159.core.repository.impl

import androidx.lifecycle.LiveData
import com.n16dccn159.core.api.ApiClothesService
import com.n16dccn159.core.data.remote.NetworkBoundResource
import com.n16dccn159.core.model.Provider
import com.n16dccn159.core.repository.ProvidersRepository
import com.n16dccn159.core.vo.Result
import retrofit2.Response

class ProvidersRepositoryImpl(val apiClothes: ApiClothesService) : ProvidersRepository {

    override suspend fun getProviders(): LiveData<Result<ArrayList<Provider>>> {
        return object : NetworkBoundResource<ArrayList<Provider>, ArrayList<Provider>>() {
            override fun processResponse(response: ArrayList<Provider>) = response
            override suspend fun createCall(): Response<ArrayList<Provider>> =
                apiClothes.getProviders()
        }.build().asLiveData()
    }

    override suspend fun getDetailProvider(providerId: Int?): LiveData<Result<Provider>> {
        return object : NetworkBoundResource<Provider, Provider>() {
            override fun processResponse(response: Provider) = response
            override suspend fun createCall(): Response<Provider> =
                apiClothes.getDetailProvider(providerId)
        }.build().asLiveData()
    }
}