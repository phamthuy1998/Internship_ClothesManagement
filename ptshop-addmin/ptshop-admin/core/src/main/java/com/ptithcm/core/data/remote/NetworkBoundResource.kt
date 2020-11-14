package com.ptithcm.core.data.remote

import android.util.Log
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.ptithcm.core.CoreApplication
import com.ptithcm.core.util.AppEvent
import com.ptithcm.core.vo.ApiResponse
import com.ptithcm.core.vo.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response


abstract class NetworkBoundResource<RequestType ,ResultType>
constructor(private val dispatcher: CoroutineDispatcher = Dispatchers.IO) {

    val TAG = NetworkBoundResource::class.java.name

    private val result = MutableLiveData<Result<ResultType>>()

    fun build(): NetworkBoundResource<RequestType , ResultType> {

        result.value = Result.Loading

        CoroutineScope(dispatcher).launch {

            val dbResult = loadFromDb()

            if (shouldFetch(dbResult)) {
                try {
                    // Show popup when no internet
                    if (CoreApplication.instance.isNetworkConnected()) {
                        fetchFromNetwork()
                    } else {
                        AppEvent.onNoInternet()
                        setValue(Result.Error("", 404))
                    }
                } catch (e: java.lang.Exception) {
                    Log.e(TAG, "An error happened: $e")
                    setValue(Result.Error(e.message ?: "", 404))
                }
            } else {
                Log.d(TAG, "Return data from local database")
                if (dbResult != null)
                    setValue(Result.Success(dbResult))
            }

        }
        return this
    }

    @MainThread
    private fun setValue(newValue: Result<ResultType>) {
        if (result.value != newValue) {
            result.postValue(newValue)
        }
    }

    private suspend fun fetchFromNetwork() {
        Log.i(TAG, "Fetch data from network")

        val apiResponse = createCall()

        Log.i(TAG, "Data fetched from network")

        if (apiResponse.isSuccessful) {
            val body = apiResponse.body()
            when (apiResponse.code()) {
                // 204 for delete brand story
                200, 201 , 204 -> {
                    body?.let {
                        val message = ""
                        if (it == null) {
                            setValue(Result.Success(it, message))
                        } else {
                            saveCallResult(processResponse(it))
                            val result = loadFromDb() ?: processResponse(it)
                            setValue(Result.Success(result))
                        }

                    }
                    if (body == null) {
                        setValue(Result.Success(null, apiResponse.message()))
                    }
                }
                else -> {
                    setValue(Result.Error(apiResponse.message(), apiResponse.code()))
                }
            }
        } else {

            val response =
                Gson().fromJson(apiResponse.errorBody()?.string(), ApiResponse::class.java)
            var errorMsg = response?.detail ?: ""
            if (apiResponse.code() == 409 && errorMsg == "") errorMsg =
                "This email is already registered"
            setValue(Result.Error(errorMsg, apiResponse.code()))
        }
    }

    fun asLiveData(): LiveData<Result<ResultType>> = result

    @WorkerThread
    protected abstract fun processResponse(response: RequestType): ResultType?

    @WorkerThread
    protected open suspend fun saveCallResult(item: ResultType?) {
    }

    @MainThread
    protected open fun shouldFetch(data: ResultType?): Boolean = true

    @MainThread
    protected open suspend fun loadFromDb(): ResultType? = null

    @MainThread
    protected abstract suspend fun createCall(): Response<RequestType>
}
