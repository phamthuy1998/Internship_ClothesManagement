package com.n16dccn159.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Vendor(
    var order: Int? = 0,
    var text: String? = "",
    var value: String? = ""
) : Parcelable