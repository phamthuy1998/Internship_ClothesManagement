package com.ptithcm.core.model

import android.os.Parcelable
import com.ptithcm.core.param.UpdatePaymentMethodParam
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Address(
    var address_country: String?,
    var address_county_area: String?,
    var address_line_1: String?,
    var address_line_2: String?,
    var address_postcode_zip: String?,
    var address_town_city: String?
) : Parcelable {

    fun copyUpdateMethodPayment(isChecked: Boolean): UpdatePaymentMethodParam {
        return UpdatePaymentMethodParam(
            isChecked, address_country, address_county_area, address_line_1, address_line_2
            , address_postcode_zip, address_town_city
        )
    }
}