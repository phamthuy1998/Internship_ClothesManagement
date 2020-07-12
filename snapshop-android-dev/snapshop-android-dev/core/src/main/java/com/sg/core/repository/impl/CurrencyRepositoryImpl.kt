package com.sg.core.repository.impl

import androidx.lifecycle.LiveData
import com.sg.core.api.ApiService
import com.sg.core.data.remote.NetworkBoundResource
import com.sg.core.model.SupportedCurrency
import com.sg.core.repository.CurrencyRepository
import com.sg.core.vo.Result
import retrofit2.Response

class CurrencyRepositoryImpl(val api: ApiService): CurrencyRepository {

    override suspend fun getAllSupportedCurrency(): LiveData<Result<ArrayList<SupportedCurrency>>> {
        return object : NetworkBoundResource<ArrayList<SupportedCurrency>, ArrayList<SupportedCurrency>>(){
            override fun processResponse(response: ArrayList<SupportedCurrency>) = response
            override suspend fun createCall(): Response<ArrayList<SupportedCurrency>> = api.getSupportedCurrencies()

        }.build().asLiveData()
    }
}