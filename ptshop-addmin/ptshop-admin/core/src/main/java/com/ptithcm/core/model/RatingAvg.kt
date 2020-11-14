package com.ptithcm.core.model

data class RatingAvg(
    var totalRating: Float? = 0F,
    var ratingAvg: Float? = 0F,
    var rating5Count: Float? = 0F,
    var rating4Count: Float? = 0F,
    var rating3Count: Float? = 0F,
    var rating2Count: Float? = 0F,
    var rating1Count: Float? = 0F,
    var rating5Percent: Int? = 0,
    var rating4Percent: Int? = 0,
    var rating3Percent: Int? = 0,
    var rating2Percent: Int? = 0,
    var rating1Percent: Int? = 0
)

data class RatingProduct(
    var rating: Double? = null,
    var ratingCount: Double? = null
)