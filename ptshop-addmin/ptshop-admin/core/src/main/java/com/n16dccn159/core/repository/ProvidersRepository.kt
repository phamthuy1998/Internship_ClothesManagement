package com.n16dccn159.core.repository

import androidx.lifecycle.LiveData
import com.n16dccn159.core.model.Provider
import com.n16dccn159.core.vo.Result

interface ProvidersRepository {

    suspend fun getProviders(): LiveData<Result<ArrayList<Provider>>>

    suspend fun getDetailProvider(providerId: Int?): LiveData<Result<Provider>>
}