package com.ptithcm.core.model

data class InvoiceProductDetail(
    var detail: String?,
    var id: Int?,
    var quantity: Int?,
    var thumnail: String?,
    var title: String?,
    var unitPrice: Double?
) {
    fun getTotalPrice() = (unitPrice ?: 0.0) * (quantity?.toDouble() ?: 0.0)
}