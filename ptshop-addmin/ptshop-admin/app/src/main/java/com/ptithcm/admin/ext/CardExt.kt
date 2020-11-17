package com.ptithcm.admin.ext

import com.ptithcm.core.model.CreditCard
import com.stripe.android.model.Card

fun Card.addInfo(name: String? = null, creditCard: CreditCard?): Card {
    var result = this.toBuilder()
        .name(name)
        .build()
    result = result.toBuilder().addressCity(creditCard?.address_city).build()
    result = result.toBuilder().addressCountry(creditCard?.address_country).build()
    result = result.toBuilder().addressLine1(creditCard?.address_line1).build()
    result = result.toBuilder().addressState(creditCard?.address_state).build()
//        .addressCity(addressCity)
//        .addressZip(zipCode)
//        .addressCountry(addressCountry)
//        .addressLine1(addressLine1)
//        .addressLine2(addressLine2)
//        .addressState(addressState)
//        .addInfo()
    return result
}