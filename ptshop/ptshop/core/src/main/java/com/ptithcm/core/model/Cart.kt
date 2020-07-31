package com.ptithcm.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cart(
    val first_name: String,
    val id: Int,
    val last_name: String,
    var products: ArrayList<ProductClothesDetail> = arrayListOf(),
    val shipping_address_country: String,
    val shipping_address_county_area: String,
    val shipping_address_line_1: String,
    val shipping_address_line_2: String,
    val shipping_address_postcode_zip: String,
    val shipping_address_town_city: String,
    val shipping_telephone: String
): Parcelable{

    fun saveAddressUser(user: User?): User? {
        return user?.copy(
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