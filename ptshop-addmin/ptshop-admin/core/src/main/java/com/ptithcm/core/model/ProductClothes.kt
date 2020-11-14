package com.ptithcm.core.model


import android.os.Parcelable
import com.ptithcm.core.vo.ItemViewModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductClothes(
    override var id: Int,
    var title: String? = null,
    var price: Double? = null,
    var thumnail: String? = null,
    var isLike: Int? = null,
    var promotion: Double? = null,
    var typePromotion: PromotionType? = null
) : ItemViewModel, Parcelable {
    fun getIsFavorite() = isLike == 1
}