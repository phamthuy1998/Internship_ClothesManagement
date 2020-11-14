package com.ptithcm.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class SupportedCurrency (
    var code: String? = "",
    var name: Currency? = Currency.GBP
): Parcelable{
    fun getLocale() = name?.toLocale()

    fun checkType(): SupportedCurrency{
        if (code?.isEmpty() == true){
            name = when(Locale.getDefault()){
                Locale.UK -> Currency.GBP
                Locale.FRANCE -> Currency.EUR
                Locale.US -> Currency.USD
                else -> Currency.GBP
            }
            code = name?.toCode()
        }
        return this
    }
}
