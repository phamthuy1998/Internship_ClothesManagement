package com.sg.snapshop.view.sizeguide

import android.util.SparseArray

object KidSizeGuide {

    val arrY = arrayListOf("APPROX. AGE", "UK", "ITALY/EUROPE")
    var arrX = SparseArray<ArrayList<String>>().apply {
        append(
            0,
            arrayListOf<String>(
                "3-6 m", "6-9 m", "9-12 m", "12-18 m", "18-24 m", "2 yr", "3 yr", "4 yr", "5 yr",
                "6 yr", "7 yr", "8 yr", "9 yr", "10 yr", "11 yr", "12 yr", "13 yr", "14 yr"
            )
        )
        append(
            1, arrayListOf<String>(
                "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "1", "2",
                "2.5", "3", "4", "5"
            )
        )
        append(
            2, arrayListOf<String>(
                "17.5", "18.5", "20", "21", "22.5", "24", "26.5", "27", "28", "29", "30", "32",
                "33", "34", "35", "36", "37", "38"
            )
        )
    }
}