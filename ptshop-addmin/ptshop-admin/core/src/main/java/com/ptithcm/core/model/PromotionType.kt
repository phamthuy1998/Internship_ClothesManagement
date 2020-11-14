package com.ptithcm.core.model


import com.google.gson.annotations.SerializedName

enum class PromotionType {
    @SerializedName("percent")
    PERCENT,

    @SerializedName("absolute")
    ABSOLUTE;
}