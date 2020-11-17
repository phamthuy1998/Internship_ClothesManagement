package com.ptithcm.admin.ext

import androidx.appcompat.widget.AppCompatButton
import com.ptithcm.core.model.Gender
import com.ptithcm.core.model.TypeCarousel
import com.ptithcm.core.model.Variant
import com.ptithcm.admin.R
import com.ptithcm.admin.constant.COLOR_OPTION
import com.ptithcm.admin.constant.KEY_EMPTY
import com.ptithcm.admin.constant.SIZE_OPTION

const val FEMALE = "Female"
const val MALE = "Male"
const val KID = "Kid"
const val UNISEX = "Unisex"

fun switchGender(gender: Int?): Gender {
    return when (gender) {
        Gender.UNISEX.value -> Gender.UNISEX
        Gender.MEN.value -> Gender.MEN
        Gender.WOMEN.value -> Gender.WOMEN
        Gender.KIDS.value -> Gender.KIDS
        else -> Gender.NONE
    }
}

fun switchTypeCarousel(type : String?) : TypeCarousel{
    return when (type) {
        TypeCarousel.BRAND.value -> TypeCarousel.BRAND
        TypeCarousel.STORE.value -> TypeCarousel.STORE
        else -> TypeCarousel.PRODUCT_LIST
    }
}

fun getValueGender(gender : Gender?) : String{
    return when(gender){
        Gender.WOMEN -> FEMALE
        Gender.MEN -> MALE
        Gender.KIDS -> KID
        Gender.UNISEX -> UNISEX
        else -> KEY_EMPTY
    }
}

fun findVariant(value: String, type: String, variants: ArrayList<Variant>?): Variant? {
    return variants?.find {
        it.options?.find { option ->
            option.name == type
        }?.value == value
    }
}

fun findVariant(value: Pair<String?,String?>,variants: ArrayList<Variant>?): Variant? {
    var conditionMeets=0
    if (variants != null) {
        for(variant in variants){
            if(value.first.isNullOrEmpty() || value.second.isNullOrEmpty()) conditionMeets++
            for(option in variant.options?: arrayListOf()){
                if(option.name== SIZE_OPTION){
                    if(option.value==value.first) conditionMeets++
                }
                if(option.name== COLOR_OPTION){
                    if(option.value==value.second) conditionMeets++
                }
            }
            if(conditionMeets==2) return variant
            else conditionMeets=0
        }
        return null
    }
    return null
}

fun toggleDrawable(rootView: AppCompatButton, isViewVisible: Boolean) {
    rootView.setCompoundDrawablesWithIntrinsicBounds(
        0,
        0,
        if (isViewVisible)
            R.drawable.ic_arrow_up_black
        else
            R.drawable.ic_arrow_down_grey,
        0
    )
}