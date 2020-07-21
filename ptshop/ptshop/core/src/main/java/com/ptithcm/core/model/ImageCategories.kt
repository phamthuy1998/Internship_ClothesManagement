package com.ptithcm.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImageCategories(var gender : Int? = 0,
                           var icon_image_url : String? = "",
                           var banner_image_url : String? = "") : Parcelable