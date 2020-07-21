package com.ptithcm.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    var billing_address_country: String? = "",
    var billing_address_county_area: String? = "",
    var billing_address_line_1: String? = "",
    var billing_address_line_2: String? = "",
    var billing_address_postcode_zip: String? = "",
    var billing_address_town_city: String? = "",
    var brand: Brand? = null,
    var cover: String? = "",
    var email: String? = "",
    var first_name: String? = "",
    var id: Int? = 0,
    var last_name: String? = "",
    var phone: String? = "",
    var photo: String? = "",
    var shipping_address_country: String? = "",
    var shipping_address_county_area: String? = "",
    var shipping_address_line_1: String? = "",
    var shipping_address_line_2: String? = "",
    var shipping_address_postcode_zip: String? = "",
    var shipping_address_town_city: String? = "",
    var shipping_telephone: String? = ""
) : Parcelable{

    fun copyCreditCard() : CreditCard{
        return CreditCard(address_city = billing_address_town_city,
            address_country = billing_address_country,
            address_line1 = billing_address_line_1,
            address_line2 = billing_address_line_2,
            address_state = billing_address_county_area,
            address_zip = billing_address_postcode_zip)
    }

    fun copyBillingAddress(
        billing_address_country: String?,
        billing_address_county_area: String?,
        billing_address_line_1: String?,
        billing_address_line_2: String?,
        billing_address_postcode_zip: String?,
        billing_address_town_city: String?
    ): User {
        return User(
            billing_address_country = billing_address_country,
            billing_address_county_area = billing_address_county_area,
            billing_address_line_1 = billing_address_line_1,
            billing_address_line_2 = billing_address_line_2,
            billing_address_postcode_zip = billing_address_postcode_zip,
            billing_address_town_city = billing_address_town_city,
            brand = brand,
            cover = cover,
            email = email,
            first_name = first_name,
            id = id,
            last_name = last_name,
            phone = phone,
            photo = photo,
            shipping_address_country = shipping_address_country,
            shipping_address_county_area = shipping_address_county_area,
            shipping_address_line_1 = shipping_address_line_1,
            shipping_address_line_2 = shipping_address_line_2,
            shipping_address_postcode_zip = shipping_address_postcode_zip,
            shipping_address_town_city = shipping_address_town_city,
            shipping_telephone = shipping_telephone

        )
    }
}