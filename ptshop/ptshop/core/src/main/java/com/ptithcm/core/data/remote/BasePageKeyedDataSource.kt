package com.ptithcm.core.data.remote

import android.util.Log
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.google.gson.Gson
import com.ptithcm.core.CoreApplication
import com.ptithcm.core.util.AppEvent
import com.ptithcm.core.vo.ApiResponse
import com.ptithcm.core.vo.ListResponse
import com.ptithcm.core.vo.Result
import kotlinx.coroutines.*
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

abstract class BasePageKeyedDataSource<I, O>(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    val status: MutableLiveData<Result<O>>
) : PageKeyedDataSource<Int, O>(), CoroutineScope {

    private val job = Job()
    val TAG = BasePageKeyedDataSource::class.java.name

    override val coroutineContext: CoroutineContext
        get() = dispatcher + job

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, O>
    ) {
        if (CoreApplication.instance.isNetworkConnected()) {
            runBlocking {
                fetchFromNetwork(initialCallback = callback)
            }
        } else {
            AppEvent.onNoInternet()
            setValue(Result.Error("", 404))
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, O>) {
        if (CoreApplication.instance.isNetworkConnected()) {
            launch {
                fetchFromNetwork(params.key, callback = callback, isInit = false)
            }
        } else {
            AppEvent.onNoInternet()
            setValue(Result.Error("", 404))
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, O>
    ) {
    }

    private suspend fun fetchFromNetwork(
        page: Int = 1,
        initialCallback: LoadInitialCallback<Int, O>? = null,
        callback: LoadCallback<Int, O>? = null,
        isInit: Boolean = true
    ) {

        Log.i(TAG, "Fetch paging from network")
        if (page == 1)
            setValue(Result.Loading)
        else
            setValue(Result.LoadingMore)

        try {
            val apiResponse = createCall(page)

            Log.i(TAG, "Data fetched from network")

            if (apiResponse.isSuccessful) {
                val body = apiResponse.body()
                when (apiResponse.code()) {
                    200, 201 -> {
                        body?.let {
                            initialCallback?.onResult(handleResponse(it, true), null, 2)
                            callback?.onResult(handleResponse(it, false), page + 1)
                            setValue(Result.Success())
                        }
                    }
                    else -> {
                        // handle show network error when call init
                        setValue(
                            Result.Error(apiResponse.message(), apiResponse.code())
                        )
                    }
                }
            } else {
                val response =
                    Gson().fromJson(apiResponse.errorBody()?.string(), ApiResponse::class.java)
                val errorMsg = response?.detail ?: ""

                // handle show network error when call init
                setValue(Result.Error(errorMsg, apiResponse.code()))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @MainThread
    private fun setValue(newValue: Result<O>) {
            status.postValue(newValue)
    }

    @MainThread
    protected abstract suspend fun createCall(page: Int): Response<ListResponse<I>>

    @WorkerThread
    protected abstract suspend fun handleResponse(
        items: ListResponse<I>,
        firstLoad: Boolean = false
    ): List<O>
}