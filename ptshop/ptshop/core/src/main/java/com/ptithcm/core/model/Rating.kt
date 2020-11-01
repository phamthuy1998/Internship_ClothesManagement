package com.ptithcm.core.model


data class Rating(
    var subComments: ArrayList<SubRating>? = null,
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
){
    fun getItemSubRatingWithPosition(position: Int): SubRating? {
        return subComments?.getOrNull(position)
    }
}

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
){
    fun dateEditFormat() = dateEdit
    fun dateRatingFormat() = dateEdit

}