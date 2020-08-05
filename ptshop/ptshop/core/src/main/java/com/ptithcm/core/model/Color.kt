package com.ptithcm.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Color(
    var colorHex: String?,
    var colorName: String?,
    var id: Int?
) : Parcelable {

    override fun equals(other: Any?): Boolean {
        return this.hashCode() == other?.hashCode()
    }

    override fun hashCode(): Int {
        return id ?: 0
    }
}