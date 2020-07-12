package com.sg.core.model

data class Account(
    val id: Int? = 0,
    val name: String? = "",
    val email: String? = "",
    val phone: String? = "",
    val gender: Int? = 0,
    val roleId: Int? = 0,
    val password: String? = "",
    val username: String? = "",
    val imageUrl: String? = "",
    val active: Int? = 0
)