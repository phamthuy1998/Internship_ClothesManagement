package com.ptithcm.core.param
import android.os.Parcelable

import kotlinx.android.parcel.Parcelize


@Parcelize
data class ChangePassParam(
    var userid: Int? = 0,
    var oldpass: String? = "",
    var newpassword: String? = ""
) : Parcelable