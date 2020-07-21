package com.ptithcm.core.repository

import androidx.lifecycle.LiveData
import com.ptithcm.core.model.Brand
import com.ptithcm.core.model.Stories
import com.ptithcm.core.util.INIT_PAGE
import com.ptithcm.core.vo.ListResponse
import com.ptithcm.core.vo.Result

interface BrandsRepository {

    suspend fun getBrands(page : Int? = INIT_PAGE): LiveData<Result<ListResponse<Brand>>>

    suspend fun getBrandsX(page : Int? = INIT_PAGE): LiveData<Result<ListResponse<Brand>>>

    suspend fun getStoriesX(page : Int? = INIT_PAGE): LiveData<Result<ListResponse<Stories>>>

    suspend fun getStories(page : Int? = INIT_PAGE): LiveData<Result<ListResponse<Stories>>>

}