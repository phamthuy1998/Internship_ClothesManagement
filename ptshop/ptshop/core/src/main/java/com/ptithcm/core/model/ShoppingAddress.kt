package com.ptithcm.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ShoppingAddress(
    var id: Int? = null,
    var accountId: Int?,
    var district: String?,
    var name: String?,
    var phone: String?,
    var province: String?,
    var street: String?,
    var wards: String?,
    var isDefault: Int?
) : Parcelable {
    fun getFullAddress(): String {
        val fullAddress = StringBuilder()
        if (!street.isNullOrEmpty())
            fullAddress.append(street)
        if (!wards.isNullOrEmpty())
            fullAddress.append(", Phường $wards")
        if (!district.isNullOrEmpty())
            fullAddress.append(", Quận/Huyện $district")
        if (!province.isNullOrEmpty())
            fullAddress.append(", Tỉnh/Thành Phố $province")
        return fullAddress.toString()
    }
}