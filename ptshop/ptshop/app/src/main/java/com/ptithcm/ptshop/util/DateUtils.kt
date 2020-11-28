package com.ptithcm.ptshop.util

import android.content.Context
import android.util.Log
import com.ptithcm.ptshop.R
import org.joda.time.DateTime
import org.joda.time.Days
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


    private const val PATTERN_DATE = "E MMM dd"
    private const val PATTERN_MESSAGE_DATE = "dd/MM/yyyy"


    /**
     * Gets timestamp in millis and converts it to HH:mm (e.g. 16:44).
     */
    fun formatTime(timeInMillis: Long): String {
        val dateFormat = SimpleDateFormat("hh:mma", Locale.getDefault())
        return dateFormat.format(timeInMillis)
    }

    fun formatTimeWithMarker(timeInMillis: Long): String {
        val dateFormat = SimpleDateFormat("h:mm a", Locale.getDefault())
        return dateFormat.format(timeInMillis)
    }

    fun getHourOfDay(timeInMillis: Long): Int {
        val dateFormat = SimpleDateFormat("H", Locale.getDefault())
        return Integer.valueOf(dateFormat.format(timeInMillis))
    }

    fun getMinute(timeInMillis: Long): Int {
        val dateFormat = SimpleDateFormat("m", Locale.getDefault())
        return Integer.valueOf(dateFormat.format(timeInMillis))
    }

    /**
     * If the given time is of a different date, display the date.
     * If it is of the same date, display the time.
     * @param timeInMillis  The time to convert, in milliseconds.
     * @return  The time or date.
     */
    fun formatDateTime(context: Context, timeInMillis: Long): String {
        Log.d("TIME_MESSAGE", (System.currentTimeMillis() - timeInMillis).toString())
        val timeMinutesAgo = (System.currentTimeMillis() - timeInMillis) / 1000 / 60
        val timeHoursAgo = timeMinutesAgo / 60
        val daysAgo = timeHoursAgo / 24
        return when {
            timeMinutesAgo < 1 -> context.getString(R.string.small_min)
            timeMinutesAgo in 2..59 -> context.getString(R.string.minutes_ago, timeMinutesAgo)
            timeMinutesAgo >= 60 && daysAgo < 1 -> {
                if (timeHoursAgo < 2) {
                    context.getString(R.string.one_hour_ago)
                } else {
                    context.getString(R.string.hours_ago, timeHoursAgo)
                }
            }
            timeHoursAgo > 24 -> {
                if (daysAgo == 1L && (timeHoursAgo % 24 == 0L)) {
                    context.getString(R.string.yesterday)
                } else {
                    formatDate(timeInMillis, PATTERN_MESSAGE_DATE)
                }
            }
            else -> ""
        }
    }

    /**
     * Formats timestamp to 'date month' format (e.g. 'February 3').
     */
    fun formatDate(timeInMillis: Long, outPattern: String = PATTERN_DATE): String {
        val dateFormat = SimpleDateFormat(outPattern, Locale.getDefault())
        return dateFormat.format(timeInMillis)
    }

    /**
     * Returns whether the given date is today, based on the user's current locale.
     */
    fun isToday(timeInMillis: Long): Boolean {
        val dateFormat = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
        val date = dateFormat.format(timeInMillis)
        return date == dateFormat.format(System.currentTimeMillis())
    }

    /**
     * Checks if two dates are of the same day.
     * @param millisFirst   The time in milliseconds of the first date.
     * @param millisSecond  The time in milliseconds of the second date.
     * @return  Whether {@param millisFirst} and {@param millisSecond} are off the same day.
     */
    fun hasSameDate(millisFirst: Long, millisSecond: Long): Boolean {
        val dateFormat = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
        return dateFormat.format(millisFirst) == dateFormat.format(millisSecond)
    }

    fun formatLastSeenDate(timeInMillis: Long): String {
        val dateFormat = SimpleDateFormat("dd MMM HH:mm", Locale.getDefault())
        return dateFormat.format(timeInMillis)
    }

    fun compareDayBetweenTimeStamp(
        futureMillis: Long,
        currentMillis: Long = System.currentTimeMillis()
    ): Long {
        val futureDay = DateTime(futureMillis)
        val currentDay = DateTime(currentMillis)
        return Days.daysBetween(currentDay.toLocalDate(), futureDay.toLocalDate()).days.toLong()
    }

}