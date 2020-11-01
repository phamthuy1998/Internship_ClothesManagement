package com.ptithcm.ptshop.view.rating

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.base.BaseFragment
import com.ptithcm.ptshop.databinding.FragmentCreateReviewBinding
import com.ptithcm.ptshop.ext.initToolBar
import com.ptithcm.ptshop.ext.setupToolbar
import com.ptithcm.ptshop.ext.visible
import com.ptithcm.ptshop.view.MainActivity


class CreateReviewFragment : BaseFragment<FragmentCreateReviewBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_create_review

    override fun bindEvent() {
        super.bindEvent()
        setupToolbar()
    }

    private fun setupToolbar() {
        (requireActivity() as? MainActivity)?.apply {
            viewBinding.btnNav.visible()
            initToolBar(viewBinding.layoutToolbar.toolbar, hasBackRight = false)
            setupToolbar(
                viewBinding.layoutToolbar.toolbar,
                getString(R.string.write_reviews)
            )
        }
    }


    override fun bindViewModel() {
        super.bindViewModel()

    }
}