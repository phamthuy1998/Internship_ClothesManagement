package com.ptithcm.core.repository.impl

import androidx.lifecycle.LiveData
import com.ptithcm.core.api.ApiService
import com.ptithcm.core.data.remote.NetworkBoundResource
import com.ptithcm.core.model.Tag
import com.ptithcm.core.repository.ShareDataRepository
import com.ptithcm.core.vo.ListResponse
import com.ptithcm.core.vo.Result
import retrofit2.Response

class ShareDataRepositoryImpl(val api: ApiService): ShareDataRepository {
    override suspend fun getAllTags(): LiveData<Result<ListResponse<Tag>>> {
        return object : NetworkBoundResource<ListResponse<Tag>, ListResponse<Tag>>(){
            override fun processResponse(response: ListResponse<Tag>) = response
            override suspend fun createCall(): Response<ListResponse<Tag>> = api.getTags()
        }.build().asLiveData()
    }
}