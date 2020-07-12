package com.sg.core.repository

import androidx.lifecycle.LiveData
import com.sg.core.model.Tag
import com.sg.core.vo.ListResponse
import com.sg.core.vo.Result

interface ShareDataRepository {

    suspend fun getAllTags(): LiveData<Result<ListResponse<Tag>>>
}