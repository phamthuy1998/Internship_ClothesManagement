package com.n16dccn159.admin.util

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.Window
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.n16dccn159.admin.R
import com.n16dccn159.admin.constant.POPUP_MODEL


open class BottomPopupDialog : BottomSheetDialogFragment() {

    private var popup: PopUp? = null
    private var binding: ViewDataBinding? = null

    companion object {
        fun newInstance(popup: PopUp?): BottomPopupDialog =
            BottomPopupDialog().apply {
                arguments = Bundle().apply {
                    putParcelable(POPUP_MODEL, popup)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        popup = arguments?.getParcelable(POPUP_MODEL)

        binding = DataBindingUtil.inflate(
            inflater,
            popup?.popupId ?: R.layout.layout_loading,
            container,
            false
        )

        if (binding == null) {
            return layoutInflater.inflate(popup?.popupId ?: R.layout.layout_loading, container, false)
        }

        return binding?.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (popup == null)
            return
        view.findViewById<ProgressBar>(R.id.progressBar)?.visibility = View.VISIBLE
        popup?.messageQueue?.invoke(binding)
        view.findViewById<ProgressBar>(R.id.progressBar)?.visibility = View.GONE
    }

    override fun onStart() {
        super.onStart()
        dialog?.setCancelable(popup?.isCancelable == true)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.setLayout(MATCH_PARENT, MATCH_PARENT)
    }

    override fun show(manager: FragmentManager, tag: String?) {
        val ft = manager.beginTransaction()
        ft.add(this, tag)
        ft.commitAllowingStateLoss()
    }
}