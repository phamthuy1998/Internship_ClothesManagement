package com.ptithcm.core.vo

class ListResponse<T>(
    val count: Int?,
    val next: String?,
    val previous: String?,
    val results: ArrayList<T>
)