package com.ptithcm.ptshop.util

import android.graphics.Paint
import android.text.Html
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatSpinner
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.ptithcm.core.model.PromotionType
import com.ptithcm.core.util.PriceFormat
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.ext.roundPrice
import java.util.*

object BindingAdapterText {

    @JvmStatic
    @BindingAdapter(value = ["curPage", "pageSize"], requireAll = true)
    fun setTextPager(textView: AppCompatTextView, curPage: String?, pageSize: String?) {
        val strString = textView.context.getString(R.string.text_count_pager, curPage, pageSize)
        textView.text = HtmlCompat.fromHtml(strString, HtmlCompat.FROM_HTML_MODE_LEGACY)
    }

    @JvmStatic
    @BindingAdapter(value = ["textCount"], requireAll = true)
    fun setTextCount(textView: AppCompatTextView, count: Int?) {
        if (count == null || count <= 0) {
            textView.text = textView.context.getString(R.string.no_item_found)
        } else {
            textView.text = textView.context.getString(R.string.count_item_found, count)
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["textPrice", "locale"], requireAll = true)
    fun setTextPrice(textView: AppCompatTextView, price: String?, locale: Locale?) {
        if (price.isNullOrEmpty()) {
            textView.text = textView.context.getString(R.string.no_price)
        } else {
            textView.text = price.roundPrice(locale ?: Locale.UK)
        }
    }

    @JvmStatic
    @BindingAdapter("price", "promo", "promoType", requireAll = false)
    fun setTextPrice(
        textView: AppCompatTextView,
        price: Double?,
        promo: Double?,
        promoType: PromotionType?
    ) {
        if (price == null) {
            textView.text = textView.context.getString(R.string.no_price)
        } else {
            textView.text =
                when (promoType) {
                    PromotionType.ABSOLUTE -> textView.context.getString(
                        R.string.price_format,
                        PriceFormat.currencyFormat(price - (promo ?: 0.0))
                    )
                    PromotionType.PERCENT -> textView.context.getString(
                        R.string.price_format,
                        PriceFormat.currencyFormat(price * (1 - (promo ?: 0.0)))
                    )
                    else -> textView.context.getString(
                        R.string.price_format,
                        PriceFormat.currencyFormat(price)
                    )
                }
        }
    }

    @JvmStatic
    @BindingAdapter("android:layout_marginStart")
    fun setMarginStart(view: AppCompatSpinner, space: Float) {
        val layoutParam = view.layoutParams as? ViewGroup.MarginLayoutParams
        layoutParam?.marginStart = space.toInt()
    }

    @JvmStatic
    @BindingAdapter("capText", requireAll = false)
    fun setTextCap(textView: AppCompatTextView, text: String) {
        textView.text = text.toLowerCase().split(" ").joinToString(" ") { it.capitalize() }
    }

    @JvmStatic
    @BindingAdapter("setTextHtml")
    fun setTextHtml(view: TextView, text: String?) {
        text ?: return

        if (text.isNullOrEmpty())
            return

        view.text = HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_COMPACT)
    }

    @JvmStatic
    @BindingAdapter("setPaintFlagsStrike")
    fun setPaintFlagsStrike(view: TextView, boolean: Boolean) {
        if (boolean)
            view.paintFlags = (Paint.STRIKE_THRU_TEXT_FLAG)
    }
}