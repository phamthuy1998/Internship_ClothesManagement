package com.ptithcm.core.repository

import androidx.lifecycle.LiveData
import com.ptithcm.core.model.Product
import com.ptithcm.core.model.Stories
import com.ptithcm.core.model.Tag
import com.ptithcm.core.param.StoryParam
import com.ptithcm.core.vo.ListResponse
import com.ptithcm.core.vo.Listing
import com.ptithcm.core.vo.Result

interface HomeRepository {

    suspend fun getAllStories(param: StoryParam): Listing<Stories>

    suspend fun getProductDetail(id: Int?): LiveData<Result<Product>>

    suspend fun getProductsDetail(ids: ArrayList<Int>?): LiveData<Result<ListResponse<Product>>>

    suspend fun getAllTags(): LiveData<Result<ListResponse<Tag>>>

    suspend fun deleteStory(id: Int?): LiveData<Result<Void>>
}