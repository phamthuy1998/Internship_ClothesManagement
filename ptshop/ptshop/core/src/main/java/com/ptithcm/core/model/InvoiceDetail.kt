package com.ptithcm.core.model

data class InvoiceDetail(
    var address: String?,
    var buyDate: String?,
    var deliveryDate: Any?,
    var id: Int?,
    var name: String?,
    var note: String?,
    var phone: String?,
    var price: Double?,
    var products: List<InvoiceProductDetail>?,
    var statusInvoice: String?,
    var statusOrderId: Int?,
    var updateDate: Any?
) {
    fun getTotalPrice(): Double? {
        return products?.fold(0.0, { sum, product ->
            sum + (product.unitPrice ?: 0.0) * (product.quantity?.toDouble() ?: 0.0)
        })
    }
}