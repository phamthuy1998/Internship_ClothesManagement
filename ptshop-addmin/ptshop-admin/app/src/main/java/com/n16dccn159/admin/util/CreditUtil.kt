package com.n16dccn159.admin.util

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.style.AbsoluteSizeSpan
import android.text.style.StyleSpan
import androidx.core.content.res.ResourcesCompat
import com.n16dccn159.admin.R
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

object CreditUtil {

    private const val PATTEN_DAY = "MM/yyyy"

    fun getExpiryDate(): ArrayList<String> {
        val startDate: Calendar = Calendar.getInstance()
        val endDate: Calendar = Calendar.getInstance()
        startDate.clear()
        endDate.clear()
        startDate.set(
            Calendar.getInstance().get(Calendar.YEAR),
            Calendar.getInstance().get(Calendar.MONTH),
            1
        )
        endDate.set(Calendar.getInstance().get(Calendar.YEAR) + 5, 12, 1)
        val format = SimpleDateFormat(PATTEN_DAY, Locale.getDefault())
        val list = ArrayList<String>()
        while (startDate < endDate) {
            list.add(format.format(startDate.getTime()))
            startDate.add(Calendar.MONTH, 1)
        }
        return list
    }

    fun formatDeletePopUpTitle(context: Context): SpannableString {
        val firstWord = "Delete Credit Card\n\n"
        val secondWord = "Are you sure you want to delete this credit card?"
        val spannable = SpannableString(firstWord + secondWord)
        val tf = ResourcesCompat.getFont(context, R.font.sanfrancisco_bold)
        val tf1 = ResourcesCompat.getFont(context, R.font.sanfrancisco)
        spannable.setSpan(
            tf?.style?.let {
                StyleSpan(it)
            }, 0, firstWord.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannable.setSpan(
            tf1?.style?.let {
                StyleSpan(it)
                AbsoluteSizeSpan(22)
            },
            firstWord.length,
            firstWord.length + secondWord.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return spannable
    }
}