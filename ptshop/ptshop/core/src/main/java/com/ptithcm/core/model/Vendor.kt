package com.ptithcm.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Vendor(
    var order: Int? = 0,
    var text: String? = "",
    var value: String? = ""
) : Parcelable