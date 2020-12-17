package com.n16dccn159.core.repository

import androidx.lifecycle.LiveData
import com.n16dccn159.core.model.Product
import com.n16dccn159.core.model.Stories
import com.n16dccn159.core.model.Tag
import com.n16dccn159.core.param.StoryParam
import com.n16dccn159.core.vo.ListResponse
import com.n16dccn159.core.vo.Listing
import com.n16dccn159.core.vo.Result

interface HomeRepository {

    suspend fun getAllStories(param: StoryParam): Listing<Stories>

    suspend fun getProductDetail(id: Int?): LiveData<Result<Product>>

    suspend fun getProductsDetail(ids: ArrayList<Int>?): LiveData<Result<ListResponse<Product>>>

    suspend fun getAllTags(): LiveData<Result<ListResponse<Tag>>>

    suspend fun deleteStory(id: Int?): LiveData<Result<Void>>
}