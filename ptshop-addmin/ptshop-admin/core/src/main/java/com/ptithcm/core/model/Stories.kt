package com.ptithcm.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Stories (
    var id: Int? = 0,
    var title: String? = "",
    var description: String? = "",
    var created_at: String? = "",
    var brand: Brand? = Brand(),
    var uploads: ArrayList<Upload>? = arrayListOf(),
    var products: ArrayList<Product>? = arrayListOf(),
    var tags: ArrayList<Tag>? = arrayListOf(),
    var brand_name : String? = "",
    var currency : String? = "",
    var avatar_image: String? = "",
    var text: String?="",
    var value:Int?=0
): Parcelable {

}