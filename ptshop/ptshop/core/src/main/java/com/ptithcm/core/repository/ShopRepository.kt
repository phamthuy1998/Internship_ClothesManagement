package com.ptithcm.core.repository

import androidx.lifecycle.LiveData
import com.ptithcm.core.model.Carousel
import com.ptithcm.core.model.Category
import com.ptithcm.core.vo.ListResponse
import com.ptithcm.core.vo.Result

interface ShopRepository {

    suspend fun getCarousels(): LiveData<Result<ListResponse<Carousel>>>

    suspend fun getMainCategories(genderID: Int): LiveData<Result<ArrayList<Category>>>
}