package com.ptithcm.core.model

import com.google.gson.annotations.SerializedName
import java.util.*


enum class Currency{
    @SerializedName("UK Pounds")
    GBP{
        override fun toValue(): String = "Uk Pounds (GBP)"
        override fun toLocale(): Locale = Locale.UK
        override fun toCode(): String = "GBP"
    },
    @SerializedName("Euro")
    EUR{
        override fun toValue(): String = "Euro (EUR)"
        override fun toLocale(): Locale = Locale.FRANCE
        override fun toCode(): String = "EUR"
    },
    @SerializedName("US Dollar")
    USD{
        override fun toValue(): String = "US Dollar (USD)"
        override fun toLocale(): Locale = Locale.US
        override fun toCode(): String = "USD"
    }
    ;
    abstract fun toValue(): String
    abstract fun toLocale(): Locale
    abstract fun toCode(): String
}