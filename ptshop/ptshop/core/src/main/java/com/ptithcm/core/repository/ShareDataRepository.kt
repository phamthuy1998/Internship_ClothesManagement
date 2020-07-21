package com.ptithcm.core.repository

import androidx.lifecycle.LiveData
import com.ptithcm.core.model.Tag
import com.ptithcm.core.vo.ListResponse
import com.ptithcm.core.vo.Result

interface ShareDataRepository {

    suspend fun getAllTags(): LiveData<Result<ListResponse<Tag>>>
}