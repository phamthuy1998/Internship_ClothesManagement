package com.sg.core.repository

import androidx.lifecycle.LiveData
import com.sg.core.model.SupportedCurrency
import com.sg.core.vo.Result

interface CurrencyRepository {
    suspend fun getAllSupportedCurrency(): LiveData<Result<ArrayList<SupportedCurrency>>>
}