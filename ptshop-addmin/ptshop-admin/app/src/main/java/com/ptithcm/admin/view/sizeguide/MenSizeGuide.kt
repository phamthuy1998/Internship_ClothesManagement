package com.ptithcm.admin.view.sizeguide

import android.util.SparseArray

object MenSizeGuide {

    val arrCountriesTrouser = arrayListOf("UK/US Waist", "Italian (IT)", "French (FR)")
    val arrCountriesTShirt =
        arrayListOf("European (EU)", "French (FR)", "Italian (IT)", "UK/US Chest", "UK/US Waist")
    val arrCountriesShirt = arrayListOf("European Collar (EU)", "UK/US Collar", "Italian(IT)")
    val arrCountriesJacket =
        arrayListOf("UK/US Chest", "European (EU)", "French(FR)", "Italian (IT)", "UK/US Waist")
    val arrCountriesShoe = arrayListOf("UK", "European (EU)", "US")

    var sizes = arrayListOf<String>("XS", "S", "M", "L", "XL", "XXL", "3XL", "4XL", "5XL", "6XL")
    var sizesShirt = arrayListOf<String>("XS", "S", "M", "L", "XL", "XXL", "3XL", "4XL")
    var sizeDetailsTrouser = SparseArray<ArrayList<String>>().apply {
        append(0, arrayListOf<String>("28", "30", "32", "34", "36", "38", "40", "42", "44", "46"))
        append(1, arrayListOf<String>("44", "46", "48", "50", "52", "54", "56", "58", "60", "62"))
        append(2, arrayListOf<String>("36", "38", "40", "42", "44", "46", "48", "50", "52", "54"))
    }
    var sizeDetailsTShirt = SparseArray<ArrayList<String>>().apply {
        append(0, arrayListOf<String>("44", "46", "48", "50", "52", "54", "56", "58", "60", "62"))
        append(1, arrayListOf<String>("44", "46", "48", "50", "52", "54", "56", "58", "60", "62"))
        append(2, arrayListOf<String>("44", "46", "48", "50", "52", "54", "56", "58", "60", "62"))
        append(3, arrayListOf<String>("34", "36", "38", "40", "42", "44", "46", "48", "50", "52"))
        append(4, arrayListOf<String>("28", "30", "32", "34", "36", "38", "40", "42", "44", "46"))
    }
    var sizeDetailsShirt = SparseArray<ArrayList<String>>().apply {
        append(0, arrayListOf<String>("37", "38", "39", "41", "42", "43", "44", "45"))
        append(1, arrayListOf<String>("14.5", "15", "15.5", "16", "16.5", "17", "17.5", "18"))
        append(2, arrayListOf<String>("44", "46", "48", "50", "52", "54", "56", "58"))
    }
    var sizeDetailsJacket = SparseArray<ArrayList<String>>().apply {
        append(0, arrayListOf<String>("34", "36", "38", "40", "42", "44", "46", "48"))
        append(1, arrayListOf<String>("44", "46", "48", "50", "52", "54", "56", "58"))
        append(2, arrayListOf<String>("44", "46", "48", "50", "52", "54", "56", "58"))
        append(3, arrayListOf<String>("44", "46", "48", "50", "52", "54", "56", "58"))
        append(4, arrayListOf<String>("28", "30", "32", "34", "36", "38", "40", "42"))
    }

    var sizeDetailsShoe = SparseArray<ArrayList<String>>().apply {
        append(
            0,
            arrayListOf<String>(
                "39", "39.5", "40", "40.5", "41", "41.5", "42", "42.5", "43", "43.5", "44"
            )
        )
        append(
            1,
            arrayListOf<String>("5", "5.5", "6", "6.5", "7", "7.5", "8", "8.5", "9", "9.5", "10")
        )
        append(
            2,
            arrayListOf<String>(
                "39", "39.5", "40", "40.5", "41", "41.5", "42", "42.5", "43", "43.5", "44"
            )
        )
        append(
            3,
            arrayListOf<String>("6", "6.5", "7", "7.5", "8", "8.5", "9", "9.5", "10", "10.5", "11")
        )
    }
}