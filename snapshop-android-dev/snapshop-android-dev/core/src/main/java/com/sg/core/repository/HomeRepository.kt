package com.sg.core.repository

import androidx.lifecycle.LiveData
import com.sg.core.model.Product
import com.sg.core.model.Stories
import com.sg.core.model.Tag
import com.sg.core.param.StoryParam
import com.sg.core.vo.ListResponse
import com.sg.core.vo.Listing
import com.sg.core.vo.Result

interface HomeRepository {

    suspend fun getAllStories(param: StoryParam): Listing<Stories>

    suspend fun getProductDetail(id: Int?): LiveData<Result<Product>>

    suspend fun getProductsDetail(ids: ArrayList<Int>?): LiveData<Result<ListResponse<Product>>>

    suspend fun getAllTags(): LiveData<Result<ListResponse<Tag>>>

    suspend fun deleteStory(id: Int?): LiveData<Result<Void>>
}