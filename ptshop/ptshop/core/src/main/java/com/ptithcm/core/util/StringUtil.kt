package com.ptithcm.core.util

import java.util.*
import java.util.regex.Pattern

fun String.capitalize(): String {
    val capBuffer = StringBuffer()
    val capMatcher = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(this)
    while (capMatcher.find()) {
        capMatcher.appendReplacement(
            capBuffer,
            capMatcher.group(1).toUpperCase(Locale.getDefault()) + capMatcher.group(2).toLowerCase(
                Locale.getDefault()
            )
        )
    }
    return capMatcher.appendTail(capBuffer).toString()
}