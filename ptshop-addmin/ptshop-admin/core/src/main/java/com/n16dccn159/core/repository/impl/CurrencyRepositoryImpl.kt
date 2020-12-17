package com.n16dccn159.core.repository.impl

import androidx.lifecycle.LiveData
import com.n16dccn159.core.api.ApiService
import com.n16dccn159.core.data.remote.NetworkBoundResource
import com.n16dccn159.core.model.SupportedCurrency
import com.n16dccn159.core.repository.CurrencyRepository
import com.n16dccn159.core.vo.Result
import retrofit2.Response

class CurrencyRepositoryImpl(val api: ApiService): CurrencyRepository {

    override suspend fun getAllSupportedCurrency(): LiveData<Result<ArrayList<SupportedCurrency>>> {
        return object : NetworkBoundResource<ArrayList<SupportedCurrency>, ArrayList<SupportedCurrency>>(){
            override fun processResponse(response: ArrayList<SupportedCurrency>) = response
            override suspend fun createCall(): Response<ArrayList<SupportedCurrency>> = api.getSupportedCurrencies()

        }.build().asLiveData()
    }
}