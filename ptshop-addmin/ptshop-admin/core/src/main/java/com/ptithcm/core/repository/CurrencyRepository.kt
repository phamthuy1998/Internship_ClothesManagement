package com.ptithcm.core.repository

import androidx.lifecycle.LiveData
import com.ptithcm.core.model.SupportedCurrency
import com.ptithcm.core.vo.Result

interface CurrencyRepository {
    suspend fun getAllSupportedCurrency(): LiveData<Result<ArrayList<SupportedCurrency>>>
}