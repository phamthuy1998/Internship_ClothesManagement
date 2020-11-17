package com.ptithcm.admin.view.card

import android.view.View
import com.ptithcm.admin.R
import com.ptithcm.admin.base.BaseFragment
import com.ptithcm.admin.databinding.FragmentConfirmCheckoutBinding
import com.ptithcm.admin.ext.initToolBar
import com.ptithcm.admin.ext.setupToolbar
import com.ptithcm.admin.view.MainActivity

class ConfirmCheckoutFragment: BaseFragment<FragmentConfirmCheckoutBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_confirm_checkout

    override fun bindEvent() {
        viewBinding.fragment = this

        (activity as? MainActivity)?.apply {
            val toolBar = viewBinding.layoutToolbar.toolbar
            initToolBar(
                toolBar,
                hasBack = false,
                hasBackRight = false,
                hasLeft = false,
                hasRight = false
            )
            setupToolbar(toolBar, getString(R.string.confirmation), isBackPress = false)
        }
    }

    fun onClick(v: View?){
        when(v?.id){
            R.id.btnBackToFeed -> {
                navController.popBackStack()
            }
        }
    }
}