package com.ptithcm.core.model

import com.ptithcm.core.vo.ItemViewModel

data class Invoice(
    override var id: Int,
    var address: String?,
    var buyDate: String?,
    var deliveryDate: Any?,
    var name: String?,
    var phone: String?,
    var price: Double?,
    var statusInvoice: String?,
    var statusOrderId: Int?,
    var updateDate: Any?
) : ItemViewModel