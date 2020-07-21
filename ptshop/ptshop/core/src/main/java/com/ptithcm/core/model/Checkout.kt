package com.ptithcm.core.model

data class Checkout (
    val brands: Map<String, CheckoutBrand>?,
    val discount: Discount?
)