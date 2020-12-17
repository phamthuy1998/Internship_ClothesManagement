package com.n16dccn159.core.vo

import com.google.gson.annotations.SerializedName
import com.n16dccn159.vo.MetaData

open class ApiResponse<T>(
    val code: String? = null,
    val detail: String? = null,
    val status: Boolean? = false,
    @SerializedName(value = "data",alternate = ["results"])
    val data: T? = null,
    val metadata: MetaData? = null
)

