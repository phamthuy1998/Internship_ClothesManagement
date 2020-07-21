package com.ptithcm.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Colour(var order : Int? = 0,
                  var value : String? = "",
                  var variants : ArrayList<Colour>? = arrayListOf(),
                  var code : String? = "",
                  var text : String? = "",
                  var isChoose : Boolean = false) : Parcelable