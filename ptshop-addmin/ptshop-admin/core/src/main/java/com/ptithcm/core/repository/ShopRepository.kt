package com.ptithcm.core.repository

import androidx.lifecycle.LiveData
import com.ptithcm.core.model.Category
import com.ptithcm.core.vo.Result

interface ShopRepository {
    suspend fun getMainCategories(genderID: Int): LiveData<Result<ArrayList<Category>>>
}