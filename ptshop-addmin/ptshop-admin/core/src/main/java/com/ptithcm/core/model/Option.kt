package com.ptithcm.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Option(
    var id: Long? = 0,
    var name: String? = "",
    var position: Int? = 0,
    var product_id: Long? = 0,
    var values: ArrayList<String>? = arrayListOf(),
    var value : String? = "",
    var option_id : Long? = 0
): Parcelable