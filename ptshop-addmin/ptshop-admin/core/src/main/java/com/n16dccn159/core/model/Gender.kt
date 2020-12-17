package com.n16dccn159.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class Gender(val value : Int) : Parcelable {
    WOMEN(0),
    MEN(1),
    KIDS(2),
    UNISEX(2),
    NONE(-1)
}