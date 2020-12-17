package com.n16dccn159.core.repository

import androidx.lifecycle.LiveData
import com.n16dccn159.core.model.Tag
import com.n16dccn159.core.vo.ListResponse
import com.n16dccn159.core.vo.Result

interface ShareDataRepository {

    suspend fun getAllTags(): LiveData<Result<ListResponse<Tag>>>
}