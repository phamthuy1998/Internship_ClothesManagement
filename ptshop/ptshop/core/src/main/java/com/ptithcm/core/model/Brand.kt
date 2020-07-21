package com.ptithcm.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Brand(
    var id: Int? = 0,
    var brand_name: String? = "",
    var name: String? = "",
    var store_id: Int? = 0,
    var commerce_platform: Int? = 0,
    var commerce_enabled: Boolean = false,
    var stripe_enabled: Boolean = false,
    var shopify_shop: String? = "",
    var shopify_charges_agreed: Boolean = false,
    var contact_information_office: String? = "",
    var contact_information_warehouse: String? = "",
    var email_address: String? = "",
    var address_line_1: String? = "",
    var address_line_2: String? = "",
    var address_town_city: String? = "",
    var address_county_area: String? = "",
    var address_country: String? = "",
    var address_postcode_zip: String? = "",
    var return_policy: String? = "",
    var bio: String? = "",
    var terms: String? = "",
    var avatar_image: String? = "",
    var background_image: String? = "",
    var products_import_completed: String? = "",
    var products_import_requested: String? = "",
    var products_import_default_gender: Int? = 0,
    var status: Int? = 0,
    var commission_percentage: Double? = 0.0,
    var currency: String? = "",
    var image_url: String? = "",
    var type : String? = "",
    var stores: ArrayList<Stories>? = arrayListOf(),
    var text: String?="",
    var value:Int?=0
) : Parcelable {

    fun copyCarousel(): Carousel {
        return Carousel(
            brand_id = if (stores.isNullOrEmpty()) 0 else id,
            storeId = if (stores.isNullOrEmpty()) id else stores?.first()?.id,
            type = if (stores.isNullOrEmpty() || stores?.size == 1) TypeCarousel.STORE.value else TypeCarousel.BRAND.value,
            gender = Gender.NONE.value,
            name = name,
            stores = stores,
            isDesigner = true
        )
    }

    fun copyCarouselDesigner(carousel: Carousel?) {
        type = carousel?.type
        name = carousel?.name
        store_id = carousel?.storeId
    }
}