package com.ptithcm.core.model


import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable
import com.ptithcm.core.vo.ItemViewModel

data class ProductClothes(
    override var id: Int ,
    var title: String? = null,
    var price: Double? = null,
    var thumnail: String? = null,
    var isLike: Int? = null,
    var promotion: Double? = null
) : ItemViewModel