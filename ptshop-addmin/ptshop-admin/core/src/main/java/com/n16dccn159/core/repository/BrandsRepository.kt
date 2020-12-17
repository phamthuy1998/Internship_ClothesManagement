package com.n16dccn159.core.repository

import androidx.lifecycle.LiveData
import com.n16dccn159.core.model.Brand
import com.n16dccn159.core.model.Stories
import com.n16dccn159.core.util.INIT_PAGE
import com.n16dccn159.core.vo.ListResponse
import com.n16dccn159.core.vo.Result

interface BrandsRepository {

    suspend fun getBrands(page : Int? = INIT_PAGE): LiveData<Result<ListResponse<Brand>>>

    suspend fun getBrandsX(page : Int? = INIT_PAGE): LiveData<Result<ListResponse<Brand>>>

    suspend fun getStoriesX(page : Int? = INIT_PAGE): LiveData<Result<ListResponse<Stories>>>

    suspend fun getStories(page : Int? = INIT_PAGE): LiveData<Result<ListResponse<Stories>>>

}