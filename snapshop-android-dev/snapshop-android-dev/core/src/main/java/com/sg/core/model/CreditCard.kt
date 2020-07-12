package com.sg.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CreditCard(
    var address_city: String?,
    var address_country: String?,
    var address_line1: String?,
    var address_line1_check: String?="",
    var address_line2: String?,
    var address_state: String?,
    var address_zip: String?,
    var address_zip_check: String?="",
    var brand: String?="",
    var country: String?="",
    var default_card: Boolean?=false,
    var exp_month: Int?=0,
    var exp_year: Int?=0,
    var id: String?="",
    var last4: String?="",
    var name: String?=""
): Parcelable {

     fun copyAddress() : Address {
         return Address(address_country, address_state, address_line1, address_line2, address_zip, address_city)
     }

    fun mapCreditCard (address : Address) {
        address_city = address.address_town_city
        address_country = address.address_country
        address_state = address.address_county_area
        address_zip = address.address_postcode_zip
        address_line1 = address.address_line_1
        address_line2 = address.address_line_2
    }
}