package com.ptithcm.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cart(
    var products: ArrayList<ProductClothesDetail> = arrayListOf(),
    var shippingAddress: ShoppingAddress? = null,
    var creditCard: CreditCard? = null
) : Parcelable {
    fun copyCreditCard(): CreditCard {
        return CreditCard().apply { mapCreditCard(shippingAddress) }
    }
}