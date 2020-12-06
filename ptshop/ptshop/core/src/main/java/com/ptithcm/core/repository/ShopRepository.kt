package com.ptithcm.core.repository

import androidx.lifecycle.LiveData
import com.ptithcm.core.model.Category
import com.ptithcm.core.model.ShopInfo
import com.ptithcm.core.model.wish.ObjectResponse
import com.ptithcm.core.vo.Result

interface ShopRepository {
    suspend fun getMainCategories(genderID: Int): LiveData<Result<ArrayList<Category>>>
    suspend fun getShopInfo(): LiveData<Result<ObjectResponse<ShopInfo>>>
}