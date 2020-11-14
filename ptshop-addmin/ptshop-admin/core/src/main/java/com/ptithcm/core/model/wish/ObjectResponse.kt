package com.ptithcm.core.model.wish

import com.google.gson.annotations.SerializedName

open class ObjectResponse<T>(
    val code: String? = null,
    @SerializedName(value = "detail", alternate = ["message"])
    val message: String? = null,
    val status: Boolean? = false,
    @SerializedName(value = "data", alternate = ["results"])
    val data: T? = null
)
