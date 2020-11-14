package com.ptithcm.core.model

import android.os.Parcelable
import com.ptithcm.core.param.CategoriesRefine
import com.ptithcm.core.util.capitalize
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MainCategories(
    var text: String? = "",
    var order: Int? = 0,
    var size_type: Int? = 0,
    var value: Int? = 0,
    var genders: ArrayList<Int>? = arrayListOf(),
    var popular: Boolean = false,
    var isExpand: Boolean = false,
    var isChoose: Boolean = false,
    var childCategories: ArrayList<MainCategories>? = arrayListOf()
) : Parcelable {

    fun initFeature() {
        isExpand = false
        isChoose = false
        text = text?.capitalize()
    }

    fun copyItemAll(): MainCategories {
        return copy(
            text = "View All ${text?.capitalize()}",
            isExpand = false,
            childCategories = arrayListOf(),
            order = 0,
            isChoose = false
        )
    }

    fun copyMainCategoryRefine(position: Triple<Int, Int, Int>): CategoriesRefine {
        return CategoriesRefine(
            id = value,
            name = text,
            position = position,
            type = when {
                position.second == 0 -> TypeCategories.MAIN_CATEGORIES
                position.third == 0 -> TypeCategories.CATEGORIES
                else -> TypeCategories.FILTER
            }
        )
    }

    fun copyCategoryRefine(index: Int, type: TypeCategories): CategoriesRefine {
        return CategoriesRefine(
            id = value,
            name = text,
            type = type,
            position = Triple(index, -1, -1)
        )
    }
}