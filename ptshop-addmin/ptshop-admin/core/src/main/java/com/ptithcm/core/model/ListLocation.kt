package com.ptithcm.core.model

data class ListLocation(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<Location>
)