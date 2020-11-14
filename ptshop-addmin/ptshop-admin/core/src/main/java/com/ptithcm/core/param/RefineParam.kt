package com.ptithcm.core.param

import android.os.Parcelable
import com.ptithcm.core.model.Gender
import com.ptithcm.core.model.MainCategories
import com.ptithcm.core.model.TypeCategories
import com.ptithcm.core.util.capitalize
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RefineParam(
    var categories: ArrayList<CategoriesRefine>? = arrayListOf(),
    var brands: ArrayList<BrandsRefine>? = arrayListOf(),
    var colours: ArrayList<ColourRefine>? = arrayListOf(),
    var sizes: ArrayList<SizeRefine>? = arrayListOf(),
    var gender: ArrayList<Gender>? = arrayListOf(),
    var newItems: Boolean = false,
    var ourPicks: Boolean = false,
    var priceHigh: Boolean = false,
    var priceLow: Boolean = false,
    var key: String? = "",
    var sport: String? = "",
    var style: String? = "",
    var storiesRefine: StoriesRefine? = StoriesRefine()
) : Parcelable {

    fun clearData(isProduct: Boolean, categories: ArrayList<CategoriesRefine>?) {
        this.categories = if (isProduct) arrayListOf() else categories
        this.brands = arrayListOf()
        this.colours = arrayListOf()
        this.sizes = arrayListOf()
        this.gender = arrayListOf()
        this.newItems = false
        this.ourPicks = false
        this.priceHigh = false
        this.priceLow = false
        this.key = ""
        this.sport = ""
        this.style = ""
    }
}

@Parcelize
data class CategoriesRefine(
    var id: Int? = 0,
    var name: String? = "",
    var position: Triple<Int, Int, Int> = Triple(-1, -1, -1),
    var type: TypeCategories? = TypeCategories.MAIN_CATEGORIES
) : Parcelable {

    fun copyMainCategory(): MainCategories {
        return MainCategories(
            value = id,
            text = "View All ${name?.capitalize()}",
            isExpand = false,
            childCategories = arrayListOf(),
            order = 0,
            isChoose = false
        )
    }
}

@Parcelize
data class BrandsRefine(
    var brandId: Int? = 0,
    var storeId: Int? = 0,
    var name: String? = ""
) : Parcelable

@Parcelize
data class ColourRefine(
    var code: String? = "",
    var name: String? = ""
) : Parcelable

@Parcelize
data class SizeRefine(
    var value: String? = "",
    var name: String? = ""
) : Parcelable

@Parcelize
data class StoriesRefine(
    var isStories: Boolean = false,
    var storiesId: Int? = 0
) : Parcelable