package com.sg.core.param
import android.os.Parcelable

import kotlinx.android.parcel.Parcelize

import androidx.annotation.Keep

import com.google.gson.annotations.SerializedName



@Parcelize
data class ChangePassParam(
    var userid: Int? = 0,
    var oldpass: String? = "",
    var newpassword: String? = ""
) : Parcelable