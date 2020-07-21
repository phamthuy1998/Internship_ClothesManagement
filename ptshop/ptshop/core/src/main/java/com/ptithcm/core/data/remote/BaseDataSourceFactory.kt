package com.ptithcm.core.data.remote

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.ptithcm.core.vo.ListResponse
import com.ptithcm.core.vo.Result
import retrofit2.Response

abstract class BaseDataSourceFactory<I, O>(val status: MutableLiveData<Result<O>>) :
    DataSource.Factory<Int, O>() {
    val sourceLiveData = MutableLiveData<BasePageKeyedDataSource<I, O>>()

    override fun create(): DataSource<Int, O> {
        val source = object : BasePageKeyedDataSource<I, O>(status = status) {
            override suspend fun createCall(page: Int): Response<ListResponse<I>> =
                createXCall(page)

            override suspend fun handleResponse(
                items: ListResponse<I>,
                firstLoad: Boolean
            ): List<O> =  handleXResponse(items, firstLoad)
        }

        sourceLiveData.postValue(source)

        return source
    }

    @MainThread
    protected abstract suspend fun createXCall(page: Int): Response<ListResponse<I>>

    @WorkerThread
    protected open suspend fun handleXResponse(
        items: ListResponse<I>,
        firstLoad: Boolean
    ): List<O> {
        val result = arrayListOf<O>()
        result.addAll(items.results as List<O>)
        return result
    }
}