package com.sg.core.model

import android.os.Parcelable
import com.sg.core.util.capitalize
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Filter(
    var text: String? = "",
    var value: Int? = 0,
    var categories: ArrayList<Categories>? = arrayListOf(),
    var order: Int? = 0
) : Parcelable {

    fun copyMainCategory(): MainCategories {
        return MainCategories(
            text = text?.capitalize(),
            order = order,
            value = value,
            isExpand = false,
            childCategories = arrayListOf(),
            isChoose = false
        )
    }
}