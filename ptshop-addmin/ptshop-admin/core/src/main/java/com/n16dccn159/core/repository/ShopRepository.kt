package com.n16dccn159.core.repository

import androidx.lifecycle.LiveData
import com.n16dccn159.core.model.Category
import com.n16dccn159.core.vo.Result

interface ShopRepository {
    suspend fun getMainCategories(genderID: Int): LiveData<Result<ArrayList<Category>>>
}