package com.n16dccn159.admin.util

import java.util.regex.Pattern

fun checkPhonevalid(phone: String): Boolean {
    val expression = "(09|01|02|03|04|05|06|07|08)+([0-9]{7,11})\\b"
    val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(phone)
    return matcher.matches()
}

fun checkValidEmail(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}