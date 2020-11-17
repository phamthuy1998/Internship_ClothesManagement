package com.ptithcm.admin.util

import android.content.Context

object DisplayUtil {

    fun getScreenWidth(context: Context): Int {
        return context.resources.displayMetrics.widthPixels
    }

    fun getScreenHeight(context: Context): Int {
        return context.resources.displayMetrics.heightPixels
    }

    fun dpToPixel(context: Context, dp: Int): Int {
        val density = context.resources.displayMetrics.density

        return (dp * density + 0.5f).toInt()
    }


}