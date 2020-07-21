package com.ptithcm.ptshop.view.card

import android.view.View
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.base.BaseFragment
import com.ptithcm.ptshop.databinding.FragmentConfirmCheckoutBinding
import com.ptithcm.ptshop.ext.initToolBar
import com.ptithcm.ptshop.ext.setupToolbar
import com.ptithcm.ptshop.view.MainActivity

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