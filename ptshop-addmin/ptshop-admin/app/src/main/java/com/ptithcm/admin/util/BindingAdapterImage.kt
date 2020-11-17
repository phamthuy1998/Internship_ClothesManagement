package com.ptithcm.admin.util

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.text.format.DateUtils
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.ptithcm.admin.R
import com.ptithcm.admin.ext.gone
import com.ptithcm.admin.ext.visible
import com.ptithcm.admin.view.rating.adapter.RatingAdapter.Companion.DATE_INPUT_FORMAT
import java.io.File
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object BindingAdapterImage {

    @JvmStatic
    @BindingAdapter(value = ["imageUrl", "placeHolder"], requireAll = false)
    fun setImageUrl(imageView: AppCompatImageView, url: String?, placeHolder: Drawable?) {
        if (url == null || url.isEmpty()) {
            imageView.gone()
        } else {
            imageView.visible()
            if (placeHolder == null)
                Glide
                    .with(imageView.context)
                    .load(File(url))
                    .thumbnail(Glide.with(imageView.context).load(url))
                    .placeholder(
                        ContextCompat.getDrawable(
                            imageView.context,
                            R.drawable.ic_place_holder
                        )
                    )
                    .into(imageView)
            else Glide
                .with(imageView.context)
                .load(File(url))
                .thumbnail(Glide.with(imageView.context).load(url))
                .placeholder(
                    ContextCompat.getDrawable(
                        imageView.context,
                        R.drawable.ic_place_holder
                    )
                )
                .into(imageView)

        }
    }

    @JvmStatic
    @BindingAdapter(value = ["dateTime", "dateTimeEdit"], requireAll = false)
    fun dateTime(textView: AppCompatTextView, dateTime: String?, dateTimeEdit: String?) {

        // set date time
        val now = Calendar.getInstance().time
        val sdfDue = SimpleDateFormat(DATE_INPUT_FORMAT, Locale.ENGLISH)
        sdfDue.timeZone = TimeZone.getTimeZone("UTC")
        var due: Date? = null
        try {
            due = sdfDue.parse(dateTimeEdit ?: dateTime ?: "")
            if (due.time > now.time) {
                textView.text = if (dateTimeEdit != null)
                    textView.context.getString(R.string.timeEdited)
                else textView.context.getString(R.string.time)
            } else {
                textView.text = if (dateTimeEdit != null)
                    textView.context.getString(
                        R.string.timeEditedValue,
                        DateUtils.getRelativeTimeSpanString(
                            due.time,
                            now.time,
                            DateUtils.SECOND_IN_MILLIS
                        )
                    )
                else DateUtils.getRelativeTimeSpanString(
                    due.time,
                    now.time,
                    DateUtils.SECOND_IN_MILLIS
                )
            }
        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["imageThumbURLVideo"], requireAll = false)
    fun imageThumbURLVideo(imageView: AppCompatImageView, urlVideo: String?) {
        if (urlVideo == null || urlVideo.isEmpty()) {
            imageView.gone()
        } else {
                Glide
                    .with(imageView.context)
                    .load(File(urlVideo))
                    .thumbnail(Glide.with(imageView.context).load(urlVideo))
                    .placeholder(
                        ContextCompat.getDrawable(
                            imageView.context,
                            R.drawable.ic_place_holder
                        )
                    )
                    .into(imageView)
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["selected"], requireAll = false)
    fun setImageSelected(imageView: AppCompatImageView, selected: Boolean) {
        imageView.isSelected = selected
    }

    @JvmStatic
    @BindingAdapter(value = ["strColorTint"], requireAll = true)
    fun setBackgroundTint(button: AppCompatButton, strColorTint: String?) {
        val drawable = button.background as? GradientDrawable
        drawable?.setColor(Color.parseColor(strColorTint))
        button.background = drawable
    }

    @JvmStatic
    @BindingAdapter("setVisible")
    fun setVisible(view: View, isVisible: Boolean?) {
        view.isVisible = isVisible ?: false
    }

    @JvmStatic
    @BindingAdapter("setInvisible")
    fun setInvisible(view: View, isVisibleInvisible: Boolean?) {
        view.isInvisible = isVisibleInvisible ?: false
    }

}