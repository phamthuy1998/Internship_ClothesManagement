package com.ptithcm.core.param

import android.os.Parcelable
import com.ptithcm.core.model.TypeCategories
import com.ptithcm.core.util.capitalize
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoriesParam(
    var main_category_id: Int = 0,
    var category_id: Int = 0,
    var filter_id: Int = 0,
    var name: String? = "",
    var gender: Int? = 0,
    var typeCategories : TypeCategories? = TypeCategories.MAIN_CATEGORIES,
    var position : Triple<Int, Int, Int> = Triple(-1, -1, -1)
) : Parcelable{

    fun copyCategoryRefine() : CategoriesRefine{
        val categoriesRefine = CategoriesRefine(name = name, type = typeCategories, position = position)
        when(typeCategories){
            TypeCategories.MAIN_CATEGORIES -> categoriesRefine.id = main_category_id
            TypeCategories.CATEGORIES -> categoriesRefine.id = category_id
            else -> categoriesRefine.id = filter_id
        }
        return categoriesRefine
    }

    fun copyItemAllCategoryRefine(): CategoriesRefine{
        val categoriesRefine = CategoriesRefine(name = "View All ${name?.capitalize()}", type = typeCategories, position = position)
        when (typeCategories) {
            TypeCategories.MAIN_CATEGORIES -> {
                categoriesRefine.id = main_category_id
            }
            TypeCategories.CATEGORIES -> {
                categoriesRefine.id = category_id
            }
            TypeCategories.FILTER -> {
                categoriesRefine.id = filter_id
            }
        }
        return categoriesRefine
    }
}