package com.ptithcm.ptshop.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    const val DEFAULT_DATE_FORMAT = "dd/MM/yyyy"
    const val DEFAULT_TIME_POLICY_FORMAT = "dd/MM/yyyy HH:mm:ss"
    const val SHORT_DEFAULT_DATE_FORMAT = "dd/MM/yy"
    const val DEFAULT_SERVER_DATE_FORMAT = "dd-MM-yyyy"
    const val DEFAULT_TIME_FORMAT = "HH:mm"
    const val DEFAULT_TIME_FORMAT2 = "HH:mm:ss"
    const val DEFAULT_DAY_FORMAT = "dd-MM"
    const val DEFAULT_DAY_FNE_FORMAT = "dd/MM"
    const val DEFAULT_DURATION_FORMAT = "MMMM, yyyy"
    const val DEFAULT_FULL_MONTH_FORMAT = "MMMM"
    const val CHECKOUT_DATE_FORMAT = "MMM dd yyyy"
    const val NOTIFICATION_DATE_FORMAT = "dd MMM yyyy"
    const val MONTH_YEAR_FORMAT = "MM/yyyy"
    const val DEFAULT_CONVERSATION_FORMAT = "dd-MM-yyyy HH:mm:ss"
    const val DEFAULT_TIME_DATE_FORMAT = "HH:mm:ss, dd-MM-yyyy"
    const val DEFAULT_COMMENT_FORMAT = "yyyy-MM-dd HH:mm:ss"
    const val DEPART_TIME_FORMAT = "HH:mm dd-MM-yyyy"
    const val DEFAULT_ITEM_CHAT_FORMAT = "HH:mm, dd/MM/yyyy"
    const val SERVER_DEPARTURE_TIME_FORMAT = "dd/MM HH:mm"
    const val DEPARTURE_TIME_FORMAT = "dd-MM HH:mm"
    const val FULL_DATE_FORMAT = "EEEE, dd MMM yyyy"
    const val DATE_MONTH_FORMAT = "dd/MM"
    const val DATE_FORMAT_SERVER = "yyyy-MM-dd'T'HH:mm:ss"
    const val SECOND_MILLIS = 1000
    const val MINUTE_MILLIS = 60 * SECOND_MILLIS
    const val HOUR_MILLIS = 60 * MINUTE_MILLIS
    const val DAY_MILLIS = 24 * HOUR_MILLIS

    @JvmStatic
    fun getLongFromString(dateTime: String?, format: String?): Long {
        if (!dateTime.isNullOrEmpty()) {
            val formater = SimpleDateFormat(format, Locale.getDefault())
            try {
                return formater.parse(dateTime).time
            } catch (e: ParseException) {
            }
        }
        return 0L
    }

    @JvmStatic
    fun convertFormat(dateTime: String?, fromFormat: String?, toFormat: String?): String {
        if (!dateTime.isNullOrEmpty()) {
            val fromFormatter = SimpleDateFormat(fromFormat, Locale("vi"))
            val toFormatter = SimpleDateFormat(toFormat, Locale("vi"))
            try {
                val cal = Calendar.getInstance()
                cal.timeInMillis = fromFormatter.parse(dateTime).time
                return toFormatter.format(cal.time)
            } catch (e: ParseException) {
            }
        }
        return ""
    }

    @JvmStatic
    fun getStringFromLong(date: Long, format: String?): String {
        val formatter = SimpleDateFormat(format, Locale("vi"))
        val cal = Calendar.getInstance()
        cal.timeInMillis = date
        return formatter.format(cal.time)
    }

}