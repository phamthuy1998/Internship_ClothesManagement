package com.ptithcm.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchParams(
    var accountId: Int?,
    var idTypeSearch: Int?,
    var keySearch: String?,
    var pageNumber: Int?,
    var pageSize: Int?,
    var typeFilter: Int?,
    var typeSearch: Int?,
    var typeSearchFilter: Int?
) : Parcelable