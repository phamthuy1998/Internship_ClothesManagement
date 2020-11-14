package com.ptithcm.core.util

import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.ParseException
import java.util.*
import kotlin.math.ln
import kotlin.math.pow
import kotlin.math.roundToInt

object PriceFormat {
    private var numberFormat: NumberFormat? = null

    @JvmStatic
    fun priceFormat(paramFloat: Double): String {
        var paramFloat = paramFloat
        paramFloat = (paramFloat * 10).roundToInt() / 10.0
        return numberFormat?.format(paramFloat) ?: ""
    }

    @Throws(ParseException::class)
    fun parsePrice(paramString: String): Number {
        return numberFormat?.parse(paramString.replace(".", ""))!!
    }

    fun decimalFormat(paramFloat: Double): String {
        val format = DecimalFormat("#,###.##")
        format.isDecimalSeparatorAlwaysShown = false
        return format.format(paramFloat)
    }

    @JvmStatic
    fun currencyFormat(paramFloat: Double): String {
        val format = DecimalFormat("#,###.####")
        format.isDecimalSeparatorAlwaysShown = false
        return format.format(paramFloat)
    }

    @JvmStatic
    fun decimalFormat(paramFloat: Double, pattern: String?): String {
        val format = DecimalFormat(pattern)
        format.isDecimalSeparatorAlwaysShown = false
        return format.format(paramFloat)
    }

    fun priceFormatWithSuffix(count: Double): String? {
        if (count < 1000) return "" + count
        val exp = (ln(count) / ln(1000.0)).toInt()
        return String.format(
            "%.1f %c",
            count / 1000.0.pow(exp.toDouble()),
            "kMGTPE"[exp - 1]
        )
    }

    fun roundToursPrice(price: Double): Double {
        return ((price / 1100).toInt() * 1000).toDouble()
    }

    const val KHONG = "không"
    const val MOT = "một"
    const val HAI = "hai"
    const val BA = "ba"
    const val BON = "bốn"
    const val NAM = "năm"
    const val SAU = "sáu"
    const val BAY = "bảy"
    const val TAM = "tám"
    const val CHIN = "chín"
    const val LAM = "lăm"
    const val LE = "lẻ"
    const val MUOI = "mươi"
    const val MUOIF = "mười"
    const val MOTS = "mốt"
    const val TRAM = "trăm"
    const val NGHIN = "nghìn"
    const val TRIEU = "triệu"
    const val TY = "tỷ"
    val number = arrayOf(
        KHONG, MOT, HAI, BA,
        BON, NAM, SAU, BAY, TAM, CHIN
    )

    /**
     * Hàm chính đọc số
     * @param a
     * @return
     */
    fun readNum(a: String): ArrayList<String> {
        val kq = ArrayList<String>()
        //Cắt chuổi string chử số ra thành các chuổi nhỏ 3 chử số
        val List_Num = splitNumber(a, 3)
        while (List_Num.size != 0) { //Xét 3 số đầu tiên của chuổi (số đầu tiên của List_Num)
            when (List_Num.size % 3) {
                1 -> kq.addAll(read_3num(List_Num[0]))
                2 -> {
                    val nghin = read_3num(List_Num[0])
                    if (!nghin.isEmpty()) {
                        kq.addAll(nghin)
                        kq.add(NGHIN)
                    }
                }
                0 -> {
                    val trieu = read_3num(List_Num[0])
                    if (!trieu.isEmpty()) {
                        kq.addAll(trieu)
                        kq.add(TRIEU)
                    }
                }
            }
            //Xét nếu 3 số đó thuộc hàng tỷ
            if (List_Num.size == List_Num.size / 3 * 3 + 1 && List_Num.size != 1) kq.add(TY)
            //Xóa 3 số đầu tiên để tiếp tục 3 số kế
            List_Num.removeAt(0)
        }
        return kq
    }

    //Đọc 3 số
    fun read_3num(a: String): ArrayList<String> {
        val kq = ArrayList<String>()
        var num = -1
        try {
            num = a.toInt()
        } catch (ex: Exception) {
        }
        if (num == 0) return kq
        var hang_tram = -1
        try {
            hang_tram = a.substring(0, 1).toInt()
        } catch (ex: Exception) {
        }
        var hang_chuc = -1
        try {
            hang_chuc = a.substring(1, 2).toInt()
        } catch (ex: Exception) {
        }
        var hang_dv = -1
        try {
            hang_dv = a.substring(2, 3).toInt()
        } catch (ex: Exception) {
        }
        //xét hàng trăm
        if (hang_tram != -1) {
            kq.add(number[hang_tram])
            kq.add(TRAM)
        }
        when (hang_chuc) {
            -1 -> {
            }
            1 -> kq.add(MUOIF)
            0 -> if (hang_dv != 0) kq.add(LE)
            else -> {
                kq.add(number[hang_chuc])
                kq.add(MUOI)
            }
        }
        when (hang_dv) {
            -1 -> {
            }
            1 -> if (hang_chuc != 0 && hang_chuc != 1 && hang_chuc != -1) kq.add(MOTS) else kq.add(
                number[hang_dv]
            )
            5 -> if (hang_chuc != 0 && hang_chuc != -1) kq.add(LAM) else kq.add(number[hang_dv])
            0 -> if (kq.isEmpty()) kq.add(number[hang_dv])
            else -> kq.add(number[hang_dv])
        }
        return kq
    }

    fun splitNumber(str: String, chunkSize: Int): ArrayList<String> {
        var str = str
        val du = str.length % chunkSize
        //Nếu độ dài chuỗi không phải bội số của chunkSize thì thêm # vào trước cho đủ.
        if (du != 0) for (i in 0 until chunkSize - du) str = "#$str"
        return splitStringEvery(str, chunkSize)
    }

    /**
     * Hàm cắt chuỗi ra thành chuỗi nhỏ
     * @param s
     * @param interval
     * @return
     */
    fun splitStringEvery(s: String, interval: Int): ArrayList<String> {
        val arrList = ArrayList<String>()
        val arrayLength = Math.ceil(s.length / interval.toDouble()).toInt()
        val result = arrayOfNulls<String>(arrayLength)
        var j = 0
        val lastIndex = result.size - 1
        for (i in 0 until lastIndex) {
            result[i] = s.substring(j, j + interval)
            j += interval
        }
        result[lastIndex] = s.substring(j)
        /*
          Có thể dùng hàm sau để cắt nhưng hiệu suất sẽ thấp hơn cách trên
         result = s.split("(?<=\\G.{" + interval + "})");
         */arrList.addAll(Arrays.asList<String>(*result))
        return arrList
    }

    @JvmStatic
    fun convertNumberToWords(s: String): String {
        var result = ""
        val kq = readNum(s)
        for (i in kq.indices) {
            result += kq[i] + " "
        }
        result += "đồng"
        return result.substring(0, 1).toUpperCase() + result.substring(1)
    }

    init {
        numberFormat = NumberFormat.getInstance(Locale.GERMANY)
        (numberFormat as NumberFormat).maximumFractionDigits = 0
        (numberFormat as NumberFormat).minimumFractionDigits = 0
        (numberFormat as NumberFormat).maximumIntegerDigits = 15
    }
}