package com.ptithcm.core.param

data class UpdateAddressParam(
    var billing_address_country: String?,
    var billing_address_county_area: String?,
    var billing_address_line_1: String?,
    var billing_address_line_2: String?,
    var billing_address_postcode_zip: String?,
    var billing_address_town_city: String?,
    var shipping_address_country: String?,
    var shipping_address_county_area: String?,
    var shipping_address_line_1: String?,
    var shipping_address_line_2: String?,
    var shipping_address_postcode_zip: String?,
    var shipping_address_town_city: String?,
    var shipping_telephone: String?
)