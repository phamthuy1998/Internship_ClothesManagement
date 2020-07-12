package com.sg.core.model

data class Checkout (
    val brands: Map<String, CheckoutBrand>?,
    val discount: Discount?
)