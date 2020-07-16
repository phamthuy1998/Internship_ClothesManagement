package com.sg.core.model


import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class Category(
    var id: Int? = null,
    var name: String? = null,
    var detail: String? = null,
    var genderID: Int? = null,
    var imageUrl: Any? = null
) : Parcelable