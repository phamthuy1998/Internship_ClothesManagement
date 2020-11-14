package com.ptithcm.core.data.remote

import androidx.paging.DataSource


abstract class BaseSource<T>(val data: List<T> ): DataSource.Factory<Int, T>() {

    override fun create(): DataSource<Int, T> {
        return object :BasePositionalDataSource<T>(data) {}
    }
}