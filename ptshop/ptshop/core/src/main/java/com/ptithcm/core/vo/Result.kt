package com.ptithcm.core.vo

sealed class Result<out R> {

    data class Success<out T>(val data: T? = null, val message: String? = "") : Result<T>()

    data class Error(val message: String, val code: Int?) : Result<Nothing>()
    object Loading : Result<Nothing>()
    object LoadingMore : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[ErrorCode = $code -- message = $message]"
            Loading -> "Loading"
            LoadingMore -> "Loading more"
        }
    }
}

