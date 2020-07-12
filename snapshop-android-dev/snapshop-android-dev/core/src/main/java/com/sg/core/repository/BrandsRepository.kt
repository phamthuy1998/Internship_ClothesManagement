package com.sg.core.repository

import androidx.lifecycle.LiveData
import com.sg.core.model.Brand
import com.sg.core.model.Stories
import com.sg.core.util.INIT_PAGE
import com.sg.core.vo.ListResponse
import com.sg.core.vo.Result

interface BrandsRepository {

    suspend fun getBrands(page : Int? = INIT_PAGE): LiveData<Result<ListResponse<Brand>>>

    suspend fun getBrandsX(page : Int? = INIT_PAGE): LiveData<Result<ListResponse<Brand>>>

    suspend fun getStoriesX(page : Int? = INIT_PAGE): LiveData<Result<ListResponse<Stories>>>

    suspend fun getStories(page : Int? = INIT_PAGE): LiveData<Result<ListResponse<Stories>>>

}