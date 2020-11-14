package com.ptithcm.core.model

import android.os.Parcelable
import androidx.databinding.ObservableBoolean
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Tag(
    val created_at: String? = "",
    val id: Int? = 0,
    val name: String? = "",
    val tag_type: Int? = 0,
    var obserIsSelected: ObservableBoolean = ObservableBoolean(false)
): Parcelable{
    @IgnoredOnParcel
    var isCheck = false
}