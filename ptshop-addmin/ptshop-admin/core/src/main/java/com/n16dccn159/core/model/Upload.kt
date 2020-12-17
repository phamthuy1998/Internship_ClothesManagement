package com.n16dccn159.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Upload(
    var id: Int? = 0,
    var  url: String? = ""
): Parcelable