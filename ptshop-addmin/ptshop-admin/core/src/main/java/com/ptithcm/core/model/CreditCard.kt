package com.ptithcm.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CreditCard(
    var address_city: String? = "",
    var address_country: String? = "",
    var address_line1: String? = "",
    var address_state: String? = "",
    var brand: String? = "",
    var country: String? = "",
    var exp_month: Int? = 0,
    var exp_year: Int? = 0,
    var id: String? = "",
    var last4: String? = "",
    var name: String? = "",
    var tokenCard: String? = null
): Parcelable {

    fun mapCreditCard(address: ShoppingAddress?) {
        address ?: return
        address_city = address.province
        address_country = "vietnam"
        address_state = address.district
        address_line1 = address.street
    }
}