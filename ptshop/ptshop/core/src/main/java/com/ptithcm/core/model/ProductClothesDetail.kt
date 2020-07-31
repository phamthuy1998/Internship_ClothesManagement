package com.ptithcm.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductClothesDetail(
    var active: Int?,
    var addDate: String?,
    var categoryID: Int?,
    var colors: List<Color>?,
    var detail: String?,
    var id: Int?,
    var images: List<String>?,
    var isLike: Int?,
    var isNew: Int?,
    var price: Double?,
    var provider: Provider?,
    var providerId: Int?,
    var rating: Int?,
    var sizes: List<Size>?,
    var sizesColors: List<SizesColor>?,
    var sold: Int?,
    var thumnail: String?,
    var title: String?,
    var typePromotion: PromotionType?,
    var valuePromotion: Double?,
    var quantityInCart: SizesColor?
) : Parcelable