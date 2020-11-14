package com.ptithcm.core.model

import android.os.Parcelable
import androidx.room.PrimaryKey
import com.ptithcm.core.param.CategoriesParam
import com.ptithcm.core.util.capitalize
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Categories(
    @PrimaryKey
    var id: Int? = 0,
    var name: String? = "",
    var popular: Boolean = false,
    var images: ArrayList<ImageCategories>? = arrayListOf(),
    var text: String? = "",
    var order: Int? = 0,
    var value: Int? = 0,
    var genders: ArrayList<Int>? = arrayListOf(),
    var gender: Int? = 0,
    var main_categories: ArrayList<Int>? = arrayListOf(),
    var isSection: Boolean = false,
    val countSection: Int? = 0
) : Parcelable {

    fun copyMainCategory(): MainCategories {
        return MainCategories(
            text = text?.capitalize(),
            order = order,
            genders = genders,
            value = value,
            popular = popular,
            isExpand = false,
            isChoose = false
        )
    }

    fun copyCategorySearch(id_main_category: Int): Categories {
        return this.copy(main_categories = arrayListOf(id_main_category))
    }


    fun copyCategoryParam(type: Gender): CategoriesParam {
        return CategoriesParam(
            main_category_id = id ?: 0,
            name = name,
            gender = type.value,
            typeCategories = TypeCategories.MAIN_CATEGORIES,
            position = Triple(0, -1, -1)
        )
    }
}