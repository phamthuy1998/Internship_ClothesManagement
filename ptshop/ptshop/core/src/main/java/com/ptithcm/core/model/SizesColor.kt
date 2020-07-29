package com.ptithcm.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SizesColor(
    var colorID: Int?,
    var colorName: String?,
    var productID: Int?,
    var quantity: Int?,
    var sizeId: Int?,
    var sizeName: String?
) : Parcelable