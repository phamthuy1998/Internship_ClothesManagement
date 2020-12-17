package com.n16dccn159.core.repository

import androidx.lifecycle.LiveData
import com.n16dccn159.core.model.SupportedCurrency
import com.n16dccn159.core.vo.Result

interface CurrencyRepository {
    suspend fun getAllSupportedCurrency(): LiveData<Result<ArrayList<SupportedCurrency>>>
}