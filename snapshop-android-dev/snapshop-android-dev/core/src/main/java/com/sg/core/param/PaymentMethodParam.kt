package com.sg.core.param

data class PaymentMethodParam(
    var card_token: String,
    var set_default: Boolean
)