package com.ptithcm.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image(
    var created_at: String? = "",
    var height: Int? = 0,
    var id: Long? = 0,
    var position: Int? = 0,
    var product_id: Long? = 0,
    var src: String? = "",
    var src_original: String? = "",
    var updated_at: String? = "",
    var variant_ids: List<Long>? = arrayListOf(),
    var width: Int? = 0
): Parcelable
