package com.ptithcm.admin.view.message

import com.ptithcm.admin.R
import com.ptithcm.admin.base.BaseFragment
import com.ptithcm.admin.databinding.FragmentMessageBinding
import com.ptithcm.admin.ext.initToolBar
import com.ptithcm.admin.ext.setupToolbar
import com.ptithcm.admin.view.MainActivity

class MessageFragment : BaseFragment<FragmentMessageBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_message

    override fun bindEvent() {
        super.bindEvent()
        setupToolbar()
    }

    private fun setupToolbar() {
        (requireActivity() as? MainActivity)?.apply {
            initToolBar(
                viewBinding.layoutToolbar.toolbar, false,
                hasBackRight = false,
                hasLeft = false,
                hasRight = false
            )
            setupToolbar(
                viewBinding.layoutToolbar.toolbar,
                getString(R.string.message)
            )
        }
    }

}