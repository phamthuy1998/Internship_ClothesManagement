package com.n16dccn159.core.model

data class Checkout (
    val brands: Map<String, CheckoutBrand>?,
    val discount: Discount?
)