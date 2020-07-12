package com.sg.core.repository.impl

import androidx.lifecycle.LiveData
import com.sg.core.api.ApiService
import com.sg.core.data.remote.NetworkBoundResource
import com.sg.core.model.Tag
import com.sg.core.repository.ShareDataRepository
import com.sg.core.vo.ListResponse
import com.sg.core.vo.Result
import retrofit2.Response

class ShareDataRepositoryImpl(val api: ApiService): ShareDataRepository {
    override suspend fun getAllTags(): LiveData<Result<ListResponse<Tag>>> {
        return object : NetworkBoundResource<ListResponse<Tag>, ListResponse<Tag>>(){
            override fun processResponse(response: ListResponse<Tag>) = response
            override suspend fun createCall(): Response<ListResponse<Tag>> = api.getTags()
        }.build().asLiveData()
    }
}