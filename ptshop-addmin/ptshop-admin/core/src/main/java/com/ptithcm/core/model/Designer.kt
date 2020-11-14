package com.ptithcm.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Designer(var brandId : Int? = 0,
                    var storeId : Int? = 0,
                    var name : String? = "",
                    var isSection : Boolean = false,
                    var isChoose : Boolean = false) : Parcelable{
    fun copyCarousel() : Carousel{
        return Carousel(brand_id = brandId,
            name = name,
            type = TypeCarousel.STORE.value,
            gender = Gender.NONE.value,
            storeId = storeId,
            isDesigner = true)
    }
}