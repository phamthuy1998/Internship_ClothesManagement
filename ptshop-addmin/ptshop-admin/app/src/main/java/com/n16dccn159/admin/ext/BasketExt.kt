package com.n16dccn159.admin.ext

import com.n16dccn159.core.model.Basket
import com.n16dccn159.core.model.CheckoutBrand
import com.n16dccn159.core.model.Discount

fun Basket.toCheckout(): Pair<MutableList<CheckoutBrand>?, Discount?>{
    val basket = this
    var checkoutBrands: MutableList<CheckoutBrand>? = null
    basket.apply {
        val listProd = product_variants.groupBy { it.product_variant.product?.brand?.brand_name }
        checkoutBrands = listProd.map {
            val brand = it.value.first().product_variant.product?.brand
            CheckoutBrand(
                product_variants = it.value,
                brand = brand
            )
        }.toMutableList()
    }
    return Pair(checkoutBrands, null)
}

fun Basket.toProductId() = this.product_variants.map {
    it.product_variant.product?.id
}
