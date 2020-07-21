package com.ptithcm.core.param

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductsParam(
    var mainCategories: ArrayList<Int>? = arrayListOf(),
    var categories: ArrayList<Int>? = arrayListOf(),
    var gender: Int? = 0
) : Parcelable