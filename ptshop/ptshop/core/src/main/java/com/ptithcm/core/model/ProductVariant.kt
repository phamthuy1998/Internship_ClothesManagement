package com.ptithcm.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductVariant(
    val product_variant: Variant,
    var quantity: Int,
    val applied_discount: AppliedDiscount? = null
): Parcelable