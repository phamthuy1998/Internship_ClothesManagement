package com.n16dccn159.admin.view.card

import android.view.View
import com.n16dccn159.admin.R
import com.n16dccn159.admin.base.BaseFragment
import com.n16dccn159.admin.databinding.FragmentConfirmCheckoutBinding
import com.n16dccn159.admin.ext.initToolBar
import com.n16dccn159.admin.ext.setupToolbar
import com.n16dccn159.admin.view.MainActivity

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