package com.ptithcm.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Color(
    var colorHex: String?,
    var colorName: String?,
    var id: Int?
) : Parcelable