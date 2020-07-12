package com.sg.snapshop.util

import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatSpinner
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.sg.snapshop.R
import com.sg.snapshop.ext.roundPrice
import java.util.*

object BindingAdapterText {

    @JvmStatic
    @BindingAdapter(value = ["curPage", "pageSize"], requireAll = true)
    fun setTextPager(textView : AppCompatTextView, curPage: String?, pageSize: String?) {
        val strString = textView.context.getString(R.string.text_count_pager, curPage, pageSize)
        textView.text = HtmlCompat.fromHtml(strString, HtmlCompat.FROM_HTML_MODE_LEGACY)
    }

    @JvmStatic
    @BindingAdapter(value = ["textCount"], requireAll = true)
    fun setTextCount(textView : AppCompatTextView, count: Int?) {
        if (count == null || count <= 0){
            textView.text = textView.context.getString(R.string.no_item_found)
        }else {
            textView.text = textView.context.getString(R.string.count_item_found, count)
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["textPrice", "locale"], requireAll = true)
    fun setTextPrice(textView : AppCompatTextView, price: String?, locale : Locale?) {
        if (price.isNullOrEmpty()) {
            textView.text = textView.context.getString(R.string.no_price)
        } else {
            textView.text = price.roundPrice(locale ?: Locale.UK)
        }
    }

    @JvmStatic
    @BindingAdapter("android:layout_marginStart")
    fun setMarginStart(view: AppCompatSpinner,space: Float){
        val layoutParam = view.layoutParams as? ViewGroup.MarginLayoutParams
        layoutParam?.marginStart = space.toInt()
    }

    @JvmStatic
    @BindingAdapter("capText", requireAll = false)
    fun setTextCap(textView: AppCompatTextView, text: String){
        textView.text = text.toLowerCase().split(" ").joinToString(" ") { it.capitalize() }
    }
}