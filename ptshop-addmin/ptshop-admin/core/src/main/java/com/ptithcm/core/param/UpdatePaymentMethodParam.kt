package com.ptithcm.core.param

data class UpdatePaymentMethodParam(
    var set_default: Boolean = false,
    var address_country: String? = "",
    var address_state: String? = "",
    var address_line1: String? = "",
    var address_line2: String? = "",
    var address_zip: String? = "",
    var address_city: String? = ""
)