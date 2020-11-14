package com.ptithcm.core.repository

import androidx.lifecycle.LiveData
import com.ptithcm.core.model.Provider
import com.ptithcm.core.vo.Result

interface ProvidersRepository {

    suspend fun getProviders(): LiveData<Result<ArrayList<Provider>>>

    suspend fun getDetailProvider(providerId: Int?): LiveData<Result<Provider>>
}