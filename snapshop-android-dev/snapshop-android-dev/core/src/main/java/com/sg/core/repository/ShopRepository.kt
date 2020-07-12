package com.sg.core.repository

import androidx.lifecycle.LiveData
import com.sg.core.model.Carousel
import com.sg.core.model.Categories
import com.sg.core.vo.ListResponse
import com.sg.core.vo.Result

interface ShopRepository {

    suspend fun getCarousels(): LiveData<Result<ListResponse<Carousel>>>

    suspend fun getMainCategories(): LiveData<Result<ListResponse<Categories>>>
}