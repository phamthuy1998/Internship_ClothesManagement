package com.ptithcm.ptshop.ext

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