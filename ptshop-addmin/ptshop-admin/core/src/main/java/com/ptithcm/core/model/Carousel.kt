package com.ptithcm.core.model

import android.os.Parcelable
import com.ptithcm.core.param.ProductsParam
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Carousel(var id : Int? = 0,
                    var name : String? = "",
                    var description : String? = "",
                    var image_url : String? = "",
                    var type : String? = "",
                    var brand_id : Int? = 0,
                    var brand_name : String? = "",
                    var categories : ArrayList<Int>? = arrayListOf(),
                    var main_categories : ArrayList<Int>? = arrayListOf(),
                    var order : Int? = 0,
                    var gender : Int? = 0,
                    var storeId : Int? = 0,
                    var stores: ArrayList<Stories>? = arrayListOf(),
                    var isDesigner : Boolean = false) : Parcelable {

    fun copyProductParam() : ProductsParam {
        return ProductsParam(main_categories, gender = gender, categories = categories)
    }
}