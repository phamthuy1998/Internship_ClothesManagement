package com.ptithcm.admin.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import com.ptithcm.admin.R
import com.ptithcm.admin.databinding.LayoutLoadingButtonBinding
import java.util.Locale

class LoadingButton(context: Context, attrs: AttributeSet? = null) : FrameLayout(context, attrs) {

    private var binding: LayoutLoadingButtonBinding

    var text: String? = ""
        set(value) {
            if (field == value) return
            field = value?.toUpperCase(Locale.ROOT)
            binding.text = field
        }

    var isLoading: Boolean = false
        set(value) {
            if (field == value) return
            field = value
            binding.isLoading = value
            binding.container.alpha = if (isOpacity && value) 0.5f else 1f
            binding.text = if (loadingText?.isNotEmpty() == true && value) loadingText else text
        }

    var isShowTextBehind: Boolean = false
        set(value) {
            field = value
            binding.isShowTextBehind = value
        }

    var btnOutOfStock: Boolean = false
        set(value) {
            if (field == value) return
            field = value
            binding.isOutOfStock = field
        }

    private var isOpacity: Boolean = false

    private var loadingText: String? = ""

    init {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.layout_loading_button,
            this,
            true
        )
        val ta = context.obtainStyledAttributes(attrs, R.styleable.LoadingButton)
        try {
            text = ta.getString(R.styleable.LoadingButton_text) ?: ""
            isLoading = ta.getBoolean(R.styleable.LoadingButton_isLoading, false)
            isShowTextBehind = ta.getBoolean(R.styleable.LoadingButton_isShowTextBehind, false)
            isOpacity = ta.getBoolean(R.styleable.LoadingButton_isOpacity, false)
            loadingText = ta.getString(R.styleable.LoadingButton_loadingText) ?: ""
            btnOutOfStock = ta.getBoolean(R.styleable.LoadingButton_outOfStock, false)
        } finally {
            ta.recycle()
        }
    }


}