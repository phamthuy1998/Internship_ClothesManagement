package com.ptithcm.core.param

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UploadProductParam(
    var title: String? = null,
    var products: List<Int>? = null,
    var description: String? = null,
    var uploads: ArrayList<ProductPhotoUrl>? = null,
    var tags: List<Int>? = null
    ) : Parcelable

@Parcelize
data class ProductPhotoUrl(
    var url: String? = null
) : Parcelable