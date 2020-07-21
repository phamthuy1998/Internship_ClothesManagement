package com.ptithcm.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class Gender(val value : Int) : Parcelable {
    WOMEN(2),
    MEN(1),
    KIDS(3),
    UNISEX(0),
    NONE(-1)
}