package com.ptithcm.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchParams(
    var keySearch: String? = "",
    var accountId: Int?,
    var pageNumber: Int? = 1,
    var pageSize: Int? = 20,
    var filter: Filter? = Filter()
) : Parcelable