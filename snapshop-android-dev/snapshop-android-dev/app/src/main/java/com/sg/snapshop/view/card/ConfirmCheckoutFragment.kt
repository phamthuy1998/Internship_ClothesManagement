package com.sg.snapshop.view.card

import android.view.View
import com.sg.snapshop.R
import com.sg.snapshop.base.BaseFragment
import com.sg.snapshop.databinding.FragmentConfirmCheckoutBinding
import com.sg.snapshop.ext.initToolBar
import com.sg.snapshop.ext.setupToolbar
import com.sg.snapshop.view.MainActivity

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