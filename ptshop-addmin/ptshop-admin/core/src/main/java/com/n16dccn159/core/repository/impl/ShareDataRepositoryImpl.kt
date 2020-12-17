package com.n16dccn159.core.repository.impl

import androidx.lifecycle.LiveData
import com.n16dccn159.core.api.ApiService
import com.n16dccn159.core.data.remote.NetworkBoundResource
import com.n16dccn159.core.model.Tag
import com.n16dccn159.core.repository.ShareDataRepository
import com.n16dccn159.core.vo.ListResponse
import com.n16dccn159.core.vo.Result
import retrofit2.Response

class ShareDataRepositoryImpl(val api: ApiService): ShareDataRepository {
    override suspend fun getAllTags(): LiveData<Result<ListResponse<Tag>>> {
        return object : NetworkBoundResource<ListResponse<Tag>, ListResponse<Tag>>(){
            override fun processResponse(response: ListResponse<Tag>) = response
            override suspend fun createCall(): Response<ListResponse<Tag>> = api.getTags()
        }.build().asLiveData()
    }
}