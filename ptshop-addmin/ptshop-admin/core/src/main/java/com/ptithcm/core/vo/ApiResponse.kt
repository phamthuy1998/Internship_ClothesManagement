package com.ptithcm.core.vo

import com.google.gson.annotations.SerializedName
import com.ptithcm.vo.MetaData

open class ApiResponse<T>(
    val code: String? = null,
    val detail: String? = null,
    val status: Boolean? = false,
    @SerializedName(value = "data",alternate = ["results"])
    val data: T? = null,
    val metadata: MetaData? = null
)

