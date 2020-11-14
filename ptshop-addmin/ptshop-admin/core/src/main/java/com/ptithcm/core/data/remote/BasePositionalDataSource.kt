package com.ptithcm.core.data.remote

import android.util.Log
import androidx.paging.PositionalDataSource


abstract class BasePositionalDataSource<T>(val list: List<T>) : PositionalDataSource<T>() {
    val TAG = BasePositionalDataSource::class.java.name

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<T>) {
        try {
            Log.d("Jayzz", "load more , start ${params.startPosition} -- end=  ${params.loadSize + params.startPosition}")
            val data = list.subList(params.startPosition, params.loadSize + params.startPosition)
            callback.onResult(data)
        } catch (e: Exception) {
            Log.d(TAG, e.toString())
        }
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<T>) {
        val totalCount = list.size
        val position = computeInitialLoadPosition(params, totalCount)
        val loadSize = computeInitialLoadSize(params, position, totalCount)
        val data = list.subList(position, position + loadSize)
        callback.onResult(data, position, totalCount)
    }

}