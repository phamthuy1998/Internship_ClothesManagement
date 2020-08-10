package com.ptithcm.core.param

import com.ptithcm.core.model.SizesColor

data class RequestCheckoutParam(
    var accountID: Int?,
    var address: String?,
    var name: String?,
    var note: String?,
    var phone: String?,
    var products: List<SizesColor?>?
)