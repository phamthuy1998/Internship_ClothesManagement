package com.ptithcm.core.repository.impl

import androidx.lifecycle.LiveData
import com.ptithcm.core.api.ApiService
import com.ptithcm.core.data.remote.NetworkBoundResource
import com.ptithcm.core.model.SupportedCurrency
import com.ptithcm.core.repository.CurrencyRepository
import com.ptithcm.core.vo.Result
import retrofit2.Response

class CurrencyRepositoryImpl(val api: ApiService): CurrencyRepository {

    override suspend fun getAllSupportedCurrency(): LiveData<Result<ArrayList<SupportedCurrency>>> {
        return object : NetworkBoundResource<ArrayList<SupportedCurrency>, ArrayList<SupportedCurrency>>(){
            override fun processResponse(response: ArrayList<SupportedCurrency>) = response
            override suspend fun createCall(): Response<ArrayList<SupportedCurrency>> = api.getSupportedCurrencies()

        }.build().asLiveData()
    }
}