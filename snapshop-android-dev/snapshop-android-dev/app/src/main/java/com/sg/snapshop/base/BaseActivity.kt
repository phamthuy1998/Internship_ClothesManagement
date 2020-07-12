package com.sg.snapshop.base

import android.app.Dialog
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.sg.core.util.AppEvent
import com.sg.core.util.NetworkListener
import com.sg.snapshop.R
import com.sg.snapshop.databinding.LayoutNoInternetBinding
import com.sg.snapshop.ext.isShowLoading
import com.sg.snapshop.util.BottomPopupDialog
import com.sg.snapshop.util.PopUp
import com.sg.snapshop.util.PopupDialog
import com.sg.snapshop.view.MainActivity
import java.util.ArrayList

abstract class BaseActivity<ViewBinding : ViewDataBinding> : AppCompatActivity(), NetworkListener {

    lateinit var viewBinding: ViewBinding

    private var listOfPopupDialogFragment: ArrayList<DialogFragment?>? = arrayListOf()

    @get:LayoutRes
    abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppEvent.registerNetworkListener(this)
        viewBinding = DataBindingUtil.setContentView(this, layoutId)
        viewBinding.lifecycleOwner = this
        bindView()
        bindViewModel()
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

    private fun popUpErrorEvent(popupBinding: ViewDataBinding?){
        (popupBinding as? LayoutNoInternetBinding)?.apply {
            btnOk.setOnClickListener {
                closePopup()
            }
        }
    }
}