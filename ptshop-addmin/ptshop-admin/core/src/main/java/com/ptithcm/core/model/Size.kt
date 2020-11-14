package com.ptithcm.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Size(
    var order: Int? = 0,
    var value: String? = "",
    var text: String? = "",
    var size_type: Int? = 0,
    var isChoose: Boolean = false,

    var description: String?,
    var id: Int?,
    var sizeName: String?
) : Parcelable