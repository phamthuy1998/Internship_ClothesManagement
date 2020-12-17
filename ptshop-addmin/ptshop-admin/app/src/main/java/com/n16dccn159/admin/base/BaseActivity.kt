package com.n16dccn159.admin.base

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.EditText
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.n16dccn159.admin.R
import com.n16dccn159.admin.databinding.LayoutNoInternetBinding
import com.n16dccn159.admin.ext.hideKeyboard
import com.n16dccn159.admin.ext.isShowLoading
import com.n16dccn159.admin.util.BottomPopupDialog
import com.n16dccn159.admin.util.PopUp
import com.n16dccn159.admin.util.PopupDialog
import com.n16dccn159.admin.view.MainActivity
import com.n16dccn159.core.util.AppEvent
import com.n16dccn159.core.util.NetworkListener
import java.util.*

abstract class
BaseActivity<ViewBinding : ViewDataBinding> : AppCompatActivity(), NetworkListener {

    lateinit var viewBinding: ViewBinding

    private var listOfPopupDialogFragment: ArrayList<DialogFragment?>? = arrayListOf()

    @get:LayoutRes
    abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("AnnaActivity", this.javaClass.simpleName)
        AppEvent.registerNetworkListener(this)
        viewBinding = DataBindingUtil.setContentView(this, layoutId)
        viewBinding.lifecycleOwner = this
        bindView()
        bindViewModel()
    }

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    hideKeyboard()
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }

    open fun bindView() {}

    open fun bindViewModel() {}

    override fun onDestroy() {
        super.onDestroy()
        AppEvent.unRegisterNetworkListener(this)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        (this as? MainActivity)?.isShowLoading(false)
    }

    fun showPopup(popup: PopUp?) {
        closePopup()
        val popupDialogFragment =
            if (popup?.isBottomSheet == false) PopupDialog.newInstance(popup) else BottomPopupDialog.newInstance(
                popup
            )
        popupDialogFragment.show(supportFragmentManager, PopupDialog().tag)
        listOfPopupDialogFragment?.add(popupDialogFragment)
    }

    fun closePopup() {
        for (item in listOfPopupDialogFragment ?: arrayListOf()) {
            item?.dismissAllowingStateLoss()
        }
        listOfPopupDialogFragment?.clear()
    }

    override fun onNoInternet() {
        showPopup(PopUp(R.layout.layout_no_internet, messageQueue = this::popUpErrorEvent))
    }

    private fun popUpErrorEvent(popupBinding: ViewDataBinding?) {
        (popupBinding as? LayoutNoInternetBinding)?.apply {
            btnCancel.setOnClickListener {
                closePopup()
            }
        }
    }
}