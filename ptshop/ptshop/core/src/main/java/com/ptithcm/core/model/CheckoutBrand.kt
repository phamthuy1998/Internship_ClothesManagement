package com.ptithcm.core.model

data class CheckoutBrand(
    val applied_discount: AppliedDiscount? = null,
    val base_delivery_price: String? = null,
    val base_products_price: String? = null,
    val base_total_discounts: String? = null,
    val base_total_price: String? = null,
    val base_total_price_before_shipping: String? = null,
    val brand: Brand? = null,
    val checkout_identifier: String? = null,
    val line_items: LineItems? = null,
    val local_products_price: String? = null,
    val local_total_discounts: String? = null,
    val local_total_price: String? = null,
    val local_total_price_before_shipping: String? = null,
    val product_variants: List<ProductVariant>? = null,
    val shipping_rate_id: String? = null,
    val tax_shipping: Boolean? = null,
    val taxes_included: Boolean? = null
){
    var rates: ShippingRate? = null
    var taxCheckout: TaxCheckout? = null
    var selectedShippingId : String? = null
    fun getSelectedRatesPrice() = rates?.rates?.find {
        it.id == selectedShippingId
    }
}