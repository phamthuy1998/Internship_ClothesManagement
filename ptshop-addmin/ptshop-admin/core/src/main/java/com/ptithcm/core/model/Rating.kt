package com.ptithcm.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Rating(
    var subComments: ArrayList<SubRating>? = null,
    var ratingID: Int? = null,
    var accountID: Int? = null,
    var orderId: Int? = null,
    var colorId: Int? = null,
    var sizeId: Int? = null,
    var username: String? = null,
    var rating: Int? = null,
    var comment: String? = null,
    var dateRating: String? = null,
    var dateEdit: String? = null,
    var productID: Int? = null,
    var imageUrl1: String? = null,
    var imageUrl2: String? = null,
    var imageUrl3: String? = null,
    var videoUrl: String? = null,
    var parentId: Int? = null
):Parcelable {
    fun getItemSubRatingWithPosition(position: Int): SubRating? {
        return subComments?.getOrNull(position)
    }

    fun videoIsNullOrEmpty() :Boolean{
        return videoUrl.isNullOrEmpty()
    }
}
@Parcelize
data class SubRating(
    var ratingID: Int? = null,
    var accountID: Int? = null,
    var username: String? = null,
    var rating: Int? = null,
    var comment: String? = null,
    var dateRating: String? = null,
    var dateEdit: String? = null,
    var productID: Int? = null,
    var imageUrl1: String? = null,
    var imageUrl2: String? = null,
    var imageUrl3: String? = null,
    var videoUrl: String? = null,
    var parentId: Int? = null
) :Parcelable{
    fun dateEditFormat() = dateEdit
    fun dateRatingFormat() = dateEdit

}