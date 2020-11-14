package com.ptithcm.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AppliedDiscount(
    val value: Int? = null,
    val title: String? = null,
    val amount: Double? = null
): Parcelable