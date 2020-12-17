package com.n16dccn159.admin.view.message

import com.n16dccn159.admin.R
import com.n16dccn159.admin.base.BaseFragment
import com.n16dccn159.admin.databinding.FragmentMessageBinding
import com.n16dccn159.admin.ext.initToolBar
import com.n16dccn159.admin.ext.setupToolbar
import com.n16dccn159.admin.view.MainActivity

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