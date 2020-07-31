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

    var selectedSize: Size?,
    var selectedColor: Color?,
    var quantityInCart: SizesColor?
) : Parcelable {

    fun getFinalPrice() : Double {
        var finalPrice = price ?: 0.0
        if (valuePromotion != 0.0) {
            if (typePromotion == PromotionType.ABSOLUTE) {
                finalPrice -= (valuePromotion ?: 0.0)
            } else if (typePromotion == PromotionType.PERCENT) {
                finalPrice *= (1 - (valuePromotion ?: 0.0))
            }
        }
        return finalPrice.coerceIn(0.0..Double.MAX_VALUE)
    }

    fun findQuantityOfSizeAndColor(sizeId: Int?, colorId: Int?): SizesColor? = sizesColors?.firstOrNull { it.sizeId == sizeId && it.colorID == colorId }
}