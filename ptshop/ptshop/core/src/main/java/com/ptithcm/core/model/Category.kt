package com.ptithcm.core.model


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Category(
    var id: Int? = null,
    var name: String? = "",
    var detail: String? = null,
    var genderID: Int? = null,
    var imageUrl: String? = null,
    var isSection: Boolean = false,
    var countSection: Int? = 0
) : Parcelable