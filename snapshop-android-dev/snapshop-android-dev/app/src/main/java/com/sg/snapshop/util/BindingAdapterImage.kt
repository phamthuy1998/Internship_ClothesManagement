package com.sg.snapshop.util

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapterImage {

    @JvmStatic
    @BindingAdapter(value = ["imageUrl", "placeHolder"], requireAll = false)
    fun setImageUrl(imageView: AppCompatImageView, url: String?, placeHolder: Drawable?) {
        if (url == null || url.isEmpty()) {
            imageView.setImageDrawable(placeHolder)
        } else {
            Glide
                .with(imageView.context)
                .load(url)
                .thumbnail(Glide.with(imageView.context).load(url))
                .placeholder(placeHolder)
                .into(imageView)
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["strColorTint"], requireAll = true)
    fun setBackgroundTint(button : AppCompatButton, strColorTint: String?) {
        val drawable = button.background as? GradientDrawable
        drawable?.setColor(Color.parseColor(strColorTint))
        button.background = drawable
    }

}