package com.ptithcm.core.model

data class Account(
    var id: Int? = 0,
    var name: String? = "",
    var email: String? = "",
    var phone: String? = "",
    var roleId: Int? = 0,
    var password: String? = "",
    var username: String? = "",
    var imageUrl: String? = "",
    var active: Int? = 0
)