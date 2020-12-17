package com.n16dccn159.admin.ext

fun Long.convertMillieToHMmSs(): String{
    val seconds = this / 1000
    val second = seconds % 60
    val minute = seconds / 60 % 60
    val hour = seconds / (60 * 60) % 24
    return if (hour > 0) {
        String.format("%02d:%02d:%02d", hour, minute, second)
    } else {
        String.format("%02d:%02d", minute, second)
    }
}

fun Double.round(decimals: Int = 2): Double = "%.${decimals}f".format(this).toDouble()

fun Float.round(decimals: Int = 2): Float = "%.${decimals}f".format(this).toFloat()