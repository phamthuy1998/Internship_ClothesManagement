package com.n16dccn159.core.param

import com.n16dccn159.core.model.SizesColor

data class RequestCheckoutParam(
    var accountID: Int?,
    var address: String?,
    var name: String?,
    var note: String?,
    var phone: String?,
    var products: List<SizesColor?>?,
    var tokenCard: String?
)