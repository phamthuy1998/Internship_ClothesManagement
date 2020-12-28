package com.ptithcm.core.model

import android.os.Parcelable
import com.ptithcm.core.vo.ItemViewModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductClothesDetail(
    override var id: Int,
    var addDate: String?,
    var categoryID: Int?,
    var colors: List<Color>?,
    var detail: String?,
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
    var quantityInCart: SizesColor?,

    var hasChanged: Boolean = false,
    var hasChangedPrice: Boolean = false,
    var hasChangedQuantity: Boolean = false
) : ItemViewModel, Parcelable {

    fun getProductDiscountPrice(): Double? =
        if (typePromotion == PromotionType.ABSOLUTE) {
            price?.minus(valuePromotion ?: 0.0)
        } else {
            price?.minus((valuePromotion ?: 0.0).times(price ?: 0.0))
        }

    fun copyProd(newProd: ProductClothesDetail) {
        colors = newProd.colors
        images = newProd.images
        isLike = newProd.isLike
        isNew = newProd.isNew
        price = newProd.price
        provider = newProd.provider
        providerId = newProd.providerId
        rating = newProd.rating
        sizes = newProd.sizes
        sizesColors = newProd.sizesColors
        sold = newProd.sold
        thumnail = newProd.thumnail
        title = newProd.title
        typePromotion = newProd.typePromotion
        valuePromotion = newProd.valuePromotion
    }

    fun getFinalPrice(): Double {
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

    fun getSizeAndColorById(sizeId: Int?, colorId: Int?): SizesColor? =
        sizesColors?.firstOrNull { it.sizeId == sizeId && it.colorID == colorId }

    override fun equals(other: Any?): Boolean {
        if (this === other)
            return true

        return this.hashCode() == other?.hashCode()
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + (colors?.hashCode() ?: 0)
        result = 31 * result + (price?.hashCode() ?: 0)
        result = 31 * result + (sizes?.hashCode() ?: 0)
        result = 31 * result + (sizesColors?.hashCode() ?: 0)
        result = 31 * result + (sold ?: 0)
        result = 31 * result + (typePromotion?.hashCode() ?: 0)
        result = 31 * result + (valuePromotion?.hashCode() ?: 0)
        return result
    }
}