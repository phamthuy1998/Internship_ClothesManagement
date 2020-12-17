package com.n16dccn159.admin.view.sizeguide

import android.util.SparseArray

object WomenSizeGuide {

    val arrCountries = arrayListOf(
        "US", "UK", "ITALY", "FRANCE", "DENMARK",
        "RUSSIA", "GERMANY", "AUSTRALIA", "JAPAN", "JEANS"
    )
    val arrGlove = arrayListOf("LADIES", "SIZE\"")
    val arrCountriesShoe = arrayListOf("US", "UK", "ITALY/EUROPE", "FRANCE")
    val arrCountriesHat = arrayListOf("US", "UK", "EUROPE")
    var sizesHat = arrayListOf("XS", "XS", "S", "S", "M", "M", "L", "L", "XL", "XL")
    var sizes = arrayListOf(
        "XXS", "XXS - XS", "XS - S", "S - M", "M - L", "L - XL",
        "XL - XXL", "XXL - XXXL", "XXXL"
    )
    var beltSizes = arrayListOf(
        "XXS", "XXS - XS", "XS - S", "S - M", "M - L",
        "L - XL", "XL - XXL", "XXL - XXXL", "XXXXL"
    )
    var sizeDetailsGlove = SparseArray<ArrayList<String>>().apply {
        append(0, arrayListOf<String>("XS", "S", "M", "L", "XL"))
        append(1, arrayListOf<String>("6", "6.5", "7/7.5", "8", "8.5/9"))
    }
    var sizeDetailsShoe = SparseArray<ArrayList<String>>().apply {
        append(
            0,
            arrayListOf<String>(
                "4", "4.5", "5", "5.5", "6", "6.5", "7", "7.5", "8", "8.5", "9",
                "9.5", "10", "10.5", "11", "11.5", "12"
            )
        )
        append(
            1,
            arrayListOf<String>(
                "1", "1.5", "2", "2.5", "3", "3.5", "4", "4.5", "5", "5.5",
                "6", "6.5", "7", "7.5", "8", "8.5", "9"
            )
        )
        append(
            2,
            arrayListOf(
                "34", "34.5", "35", "35.5", "36", "36.5", "37", "37.5", "38", "38.5",
                "39", "39.5", "40", "40.5", "41", "41.5", "42"
            )
        )
        append(
            3,
            arrayListOf(
                "35", "35.5", "36", "36.5", "37", "37.5", "38", "38.5", "39", "39.5",
                "40", "40.5", "41", "41.5", "42", "42.5", "43"
            )
        )
    }
    var sizeDetailsRing = SparseArray<ArrayList<String>>().apply {
        append(
            0,
            arrayListOf<String>(
                "4", "4¼", "4½", "4¾", "5", "5¼", "5½", "5¾", "6", "6¼", "6½", "6¾", "7", "7¼",
                "7½", "7¾", "8", "8¼", "8½", "8¾", "9"
            )
        )
        append(
            1,
            arrayListOf(
                "H½", "|", "|½", "J", "J½", "K", "K½", "L", "L½", "M", "M½", "N", "N½", "O", "O½",
                "P", "P½", "Q", "Q½", "R", "R½"
            )
        )
        append(
            2,
            arrayListOf(
                "8", "8/9", "9", "10", "10/11", "11", "12", "12/13", "13", "14", "14/15", "15",
                "15/16", "16", "17", "17/18", "18", "18/19", "19", "19/20", "20"
            )
        )
        append(
            3,
            arrayListOf(
                "46", "47", "48", "49", "49.5", "50", "50.5", "51", "52", "53", "53.5", "54",
                "54.5", "55", "55.5", "56", "57", "58", "58.5", "59", "60"
            )
        )
    }
    var sizeDetailsHat = SparseArray<ArrayList<String>>().apply {
        append(0, arrayListOf("6½", "6⅝", "6¾", "6⅞", "7", "7⅛", "7¼", "7⅜", "7½", "7⅝"))
        append(1, arrayListOf("6⅜", "6½", "6⅝", "6¾", "6⅞", "7", "7⅛", "7¼", "7⅜", "7½"))
        append(2, arrayListOf("52", "53", "54", "55", "56", "57", "58", "59", "60", "61"))
    }
    var sizeDetailsBelt = SparseArray<ArrayList<String>>().apply {
        append(0, arrayListOf("00", "0", "2 - 4", "4 - 6", "8", "10", "12", "14", "16"))
        append(1, arrayListOf("4", "6", "8", "10", "12", "14", "16", "18", "20"))
        append(2, arrayListOf("36", "38", "40", "42", "44", "46", "48", "50", "52"))
        append(3, arrayListOf("65", "70", "75", "80", "85", "90", "95", "100", "105"))
    }

    var sizeDetails = SparseArray<ArrayList<String>>().apply {
        append(0, arrayListOf("00", "0", "2 - 4", "4 - 6", "8", "10", "12", "14", "16"))
        append(1, arrayListOf("4", "6", "8", "10", "12", "14", "16", "18", "20"))
        append(2, arrayListOf("36", "38", "40", "42", "44", "46", "48", "50", "52"))
        append(3, arrayListOf("32", "34", "36", "38", "40", "42", "44", "46", "48"))
        append(4, arrayListOf("30", "32", "34", "36", "38", "40", "42", "44", "46"))
        append(5, arrayListOf("38", "40", "42", "44", "46", "48", "50", "52", "54"))
        append(6, arrayListOf("30", "32", "34", "36", "38", "40", "42", "44", "46"))
        append(7, arrayListOf("4", "6", "8", "10", "12", "14", "16", "18", "20"))
        append(8, arrayListOf("3", "5", "7", "9", "11", "13", "15", "17", "19"))
        append(
            9,
            arrayListOf(
                "23", "24 - 25", "26 - 27", "27 - 28", "29 - 30", "31 - 32",
                "32 - 33", "", ""
            )
        )
    }
}