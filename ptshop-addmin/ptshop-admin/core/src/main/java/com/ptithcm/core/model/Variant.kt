package com.ptithcm.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Variant(
    var base_compare_at_price: String? = "",
    var base_compare_at_price_after_tax: String? = "",
    var base_price: String? = "",
    var base_price_after_tax: String? = "",
    var compare_at_price: String? = "",
    var compare_at_price_after_tax: String? = "",
    var id: Long? = 0,
    var image: Image? = Image(),
    var inventory_quantity: Int? = 0,
    var is_active: Boolean = false,
    var options: ArrayList<Option>? = arrayListOf(),
    var position: Int? = 0,
    var price: String? = "",
    var price_after_tax: String? = "",
    var price_rate: String? = "",
    var requires_shipping: Boolean = false,
    var title: String? = "",
    var unique_id: String? = "",
    val product: Product?//for update basket address
): Parcelable {
    fun quantity() = inventory_quantity.toString()

    fun checkIfWrongPrice(): Variant{
        if (compare_at_price_after_tax != null
            && (compare_at_price_after_tax?.toDouble() ?: 0.0) < (price_after_tax?.toDouble() ?: 0.0)
        ){
            compare_at_price_after_tax = null
        }
        return this
    }
}