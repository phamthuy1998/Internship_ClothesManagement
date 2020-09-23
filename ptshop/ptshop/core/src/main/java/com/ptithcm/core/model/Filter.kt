package com.ptithcm.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Filter(
    var sortBy: Int? = null,
    var categories: List<Int>? = listOf(),
    var providers: List<Int>? = listOf()
) : Parcelable {
    fun clearData() {
        sortBy = null
        categories = listOf()
        providers = listOf()
    }
}