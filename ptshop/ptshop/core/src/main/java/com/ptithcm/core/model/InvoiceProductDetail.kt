package com.ptithcm.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class InvoiceProductDetail(
    var invoiceId: Int?,
    var sizeId: Int?,
    var colorId: Int?,
    var statusRating: Int?=0,
    var detail: String?,
    var id: Int?,
    var quantity: Int?,
    var thumnail: String?,
    var title: String?,
    var unitPrice: Double?,
    var colorName: String?,
    var colorHex: String?,
    var sizeName: String?
) : Parcelable {
    fun getTotalPrice() = (unitPrice ?: 0.0) * (quantity?.toDouble() ?: 0.0)
}

