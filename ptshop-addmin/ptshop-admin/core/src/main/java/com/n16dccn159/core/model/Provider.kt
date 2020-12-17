package com.n16dccn159.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Provider(
    var id: Int?,
    var brandName: String?,
    var infomation: String?,
    var imageUrl: String?
) : Parcelable