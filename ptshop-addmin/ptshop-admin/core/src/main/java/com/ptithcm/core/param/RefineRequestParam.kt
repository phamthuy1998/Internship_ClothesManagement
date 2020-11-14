package com.ptithcm.core.param

import com.ptithcm.core.model.Gender
import com.ptithcm.core.model.TypeCategories
import com.ptithcm.core.util.INIT_PAGE
import com.ptithcm.core.util.ORDERING_HIGH
import com.ptithcm.core.util.ORDERING_LOW

data class RefineRequestParam(
    var designers: ArrayList<String>? = arrayListOf(),
    var main_category: ArrayList<Int>? = arrayListOf(),
    var category: ArrayList<Int>? = arrayListOf(),
    var filters: ArrayList<Int>? = arrayListOf(),
    var colour: ArrayList<String>? = arrayListOf(),
    var size: ArrayList<String>? = arrayListOf(),
    var gender: ArrayList<Int>? = arrayListOf(),
    var section: Int? = 0,
    var ordering: String? = "",
    var page: Int? = INIT_PAGE
) {

    fun convertParam(refineParam: RefineParam?): RefineRequestParam {
        refineParam?.brands?.forEach {
            when {
                it.brandId == 0 -> designers?.add(":${it.storeId}")
                it.storeId == 0 -> designers?.add("${it.brandId}:")
                else -> designers?.add("${it.brandId}:${it.storeId}")
            }
        }
        refineParam?.colours?.forEach {
            colour?.add("${it.name}")
        }
        refineParam?.gender?.forEach {
            if (it.value != Gender.NONE.value) {
                gender?.add(it.value)
            }
        }
        refineParam?.sizes?.forEach {
            size?.add("${it.value}")
        }
        refineParam?.categories?.forEach {
            when (it.type) {
                TypeCategories.MAIN_CATEGORIES -> main_category?.add(it.id ?: 0)
                TypeCategories.CATEGORIES -> category?.add(it.id ?: 0)
                else -> filters?.add(it.id ?: 0)
            }
        }

        return RefineRequestParam(designers = designers, main_category = main_category,
            category = category, filters = filters, size = size, colour = colour,
            gender = gender, section = getSection(refineParam), ordering = getPrice(refineParam)
        )
    }

    private fun getSection(refineParam: RefineParam?): Int {
        return when {
            refineParam?.newItems == true -> 1
            refineParam?.ourPicks == true -> 2
            else -> 0
        }
    }

    private fun getPrice(refineParam: RefineParam?): String {
        return when {
            refineParam?.priceHigh == true -> ORDERING_HIGH
            refineParam?.priceLow == true -> ORDERING_LOW
            else -> ""
        }
    }

    fun optionsMap(refineParam: RefineParam?): Map<String, String?> {
        val hashMap = hashMapOf<String, String?>()
        val section = getSection(refineParam)
        val ordering = getPrice(refineParam)
        if (section != 0) {
            hashMap["section"] = section.toString()
        }
        if (ordering.isNotEmpty()) {
            hashMap["ordering"] = ordering
        }
        return hashMap
    }

    fun optionsSearchMap(refineParam: RefineParam?): Map<String, String?> {
        val hashMap = hashMapOf<String, String?>()
        if (!refineParam?.key.isNullOrEmpty()){
            hashMap["search"] = refineParam?.key
        }
        if (!refineParam?.sport.isNullOrEmpty()){
            hashMap["sports"] = refineParam?.sport
        }
        if (!refineParam?.style.isNullOrEmpty()){
            hashMap["styles"] = refineParam?.style
        }
        return hashMap
    }
}