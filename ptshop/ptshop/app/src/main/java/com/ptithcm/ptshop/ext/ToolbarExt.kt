package com.ptithcm.ptshop.ext

import android.graphics.Color
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import com.ptithcm.core.util.ObjectHandler
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.view.MainActivity

fun initToolBar(
    toolbar: Toolbar?, hasBack: Boolean = true, hasBackRight: Boolean = true,
    hasLeft: Boolean = true, hasRight: Boolean = true, hasTextLeft: Boolean = false,
    hasTextRight: Boolean = false, hasCloseButton: Boolean = false, hasCount: Boolean = true,
    isProductPage: Boolean = false
) {
    resetToolbar(toolbar)
    toolbar?.findViewById<AppCompatImageButton>(R.id.ivBack)?.apply {
        if (hasBack) this.visible() else this.gone()
    }
    toolbar?.findViewById<AppCompatTextView>(R.id.tvBackRight)?.apply {
        if (hasBackRight) this.visible() else this.gone()
    }
    toolbar?.findViewById<AppCompatImageButton>(R.id.ivLeft)?.apply {
        if (hasLeft) this.visible() else this.gone()
    }
    toolbar?.findViewById<AppCompatImageButton>(R.id.ivRight)?.apply {
        if ((hasRight) or isProductPage) this.visible() else this.gone()
    }
    toolbar?.findViewById<AppCompatTextView>(R.id.tvCancelToolbar)?.apply {
        if (hasTextLeft) this.visible() else this.gone()
    }
    toolbar?.findViewById<AppCompatTextView>(R.id.tvRight)?.apply {
        if (hasTextRight) this.visible() else this.gone()
    }
    toolbar?.findViewById<AppCompatImageButton>(R.id.ivClose)?.apply {
        if (hasCloseButton) this.visible() else this.gone()
    }
    toolbar?.findViewById<AppCompatTextView>(R.id.tvCount)?.apply {
        when {
            // rest for account is user
            !hasCount || hasCount && !hasRight -> gone()
            hasCount -> visible()
        }
    }
    toolbar?.findViewById<AppCompatTextView>(R.id.tvTitleToolbar)?.apply {
        setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
    }
}

fun AppCompatActivity.setupToolbar(
    toolbar: Toolbar?, title: String = getString(R.string.app_name), isBackPress: Boolean = true,
    messageQueue: ((view: View) -> Unit)? = null
) {
    toolbar?.apply {
        visible()
        transparentStatusBar(false)
        toolbar.findViewById<AppCompatTextView>(R.id.tvTitleToolbar)?.apply {
            setOnClickListener {
                messageQueue?.invoke(it)
            }
        }
        toolbar.findViewById<AppCompatImageButton>(R.id.ivBack)?.apply {
            setOnClickListener {
                if (isBackPress) {
                    (this@setupToolbar as? MainActivity)?.isShowLoading(false)
                    (this@setupToolbar as? MainActivity)?.isShowErrorNetwork(false)
                    this@setupToolbar.onBackPressed()
                } else messageQueue?.invoke(it)
            }
        }
        toolbar.findViewById<AppCompatImageButton>(R.id.ivClose)?.apply {
            setOnClickListener {
                if (isBackPress) this@setupToolbar.onBackPressed() else messageQueue?.invoke(
                    it
                )
            }
        }
        toolbar.findViewById<AppCompatImageView>(R.id.ivLogo)?.apply {
            if (title == getString(R.string.app_name)) {
                visibility = View.VISIBLE
                toolbar.findViewById<AppCompatTextView>(R.id.tvTitleToolbar)?.gone()
            } else {
                visibility = View.GONE
                toolbar.findViewById<AppCompatTextView>(R.id.tvTitleToolbar)?.apply {
                    visible()
                    text = title
                }
            }
        }
        toolbar.findViewById<AppCompatImageButton>(R.id.ivLeft)?.apply {
            setOnClickListener {
                messageQueue?.invoke(it)
            }
        }
        toolbar.findViewById<AppCompatImageButton>(R.id.ivRight)?.apply {
            setOnClickListener {
                messageQueue?.invoke(it)
            }
        }
        toolbar.findViewById<AppCompatTextView>(R.id.tvRight)?.apply {
            setOnClickListener {
                messageQueue?.invoke(it)
            }
        }
        val countBags = ObjectHandler.getNumberItem()
        toolbar.findViewById<AppCompatTextView>(R.id.tvCount)?.apply {
            when {
                countBags > 0 -> text = countBags.toString()
                else -> this.gone()
            }
        }
    }
}

private fun resetToolbar(toolbar: Toolbar?) {
    toolbar?.apply {
        visible()
        isSelected = false
        toolbar.findViewById<AppCompatImageButton>(R.id.ivRight)?.apply {
            setImageResource(R.drawable.ic_shopping_bag)
        }
        toolbar.findViewById<AppCompatImageButton>(R.id.ivBack)?.apply {
            setImageResource(R.drawable.ic_back)
        }
        toolbar.findViewById<AppCompatImageView>(R.id.ivLogo)?.apply {
            gone()
        }
        toolbar.findViewById<AppCompatTextView>(R.id.tvClear)?.apply {
            gone()
        }
        toolbar.findViewById<AppCompatTextView>(R.id.tvCount)?.apply {
            gone()
        }
    }
}

fun AppCompatActivity.transparentStatusBar(isFull: Boolean, isBlack: Boolean = false) {
    when (isFull) {
        true -> {
            window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }
        else -> {
            if (isBlack) {
                blackStatusBar()
            } else {
                window.statusBarColor = Color.WHITE
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
            }
        }
    }
}

fun AppCompatActivity.blackStatusBar() {
    window.statusBarColor = resources.getColor(R.color.black, null)
    window.decorView.systemUiVisibility =
        window.decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
}