package com.sg.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Profile(
    val token: String? = "",
    var user: User? = User()
):Parcelable{

}