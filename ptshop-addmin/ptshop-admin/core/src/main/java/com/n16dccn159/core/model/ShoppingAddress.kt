package com.n16dccn159.core.model

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.n16dccn159.core.CoreApplication
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ShoppingAddress(
    @Bindable
    var id: Int? = null,
    @Bindable
    var accountId: Int? = CoreApplication.instance.account?.id,
    @Bindable
    var district: String? = "",
    @Bindable
    var name: String? = "",
    @Bindable
    var phone: String? = "",
    @Bindable
    var province: String? = "",
    @Bindable
    var street: String? = "",
    @Bindable
    var wards: String? = "",
    var isDefault: Int? = 0
) : BaseObservable(), Parcelable {
    fun getFullAddress(): String {
        val fullAddress = StringBuilder()
        if (!street.isNullOrEmpty())
            fullAddress.append(street)
        if (!wards.isNullOrEmpty())
            fullAddress.append(", $wards")
        if (!district.isNullOrEmpty())
            fullAddress.append(", $district")
        if (!province.isNullOrEmpty())
            fullAddress.append(", $province")
        return fullAddress.toString()
    }

    fun isEmpty(): Boolean {
        return district.isNullOrEmpty()
                || name.isNullOrEmpty()
                || phone.isNullOrEmpty()
                || province.isNullOrEmpty()
                || street.isNullOrEmpty()
                || wards.isNullOrEmpty()
    }
}