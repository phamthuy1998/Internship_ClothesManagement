package com.ptithcm.core.param

data class ChangePasswordParam(
    val current_password:String?,
    val new_password:String?
)