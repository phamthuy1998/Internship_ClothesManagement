package com.ptithcm.admin.view.wishlist

import android.view.View
import com.ptithcm.admin.R
import com.ptithcm.admin.base.BaseFragment
import com.ptithcm.admin.databinding.FragmentOverViewBinding
import com.ptithcm.admin.ext.gone
import com.ptithcm.admin.ext.visible
import com.ptithcm.admin.view.MainActivity
import com.ptithcm.admin.view.home.StoryDetailActivity
import com.ptithcm.admin.view.wishlist.overview.ImageZoomPagerAdapter

class OverViewFragment : BaseFragment<FragmentOverViewBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_over_view

    override fun bindEvent() {
        setUpViewpager()

        viewBinding.fragment = this

        (activity as? MainActivity)?.apply {
            viewBinding.layoutToolbar.toolbar.gone()
        }
        (activity as? StoryDetailActivity)?.apply {
            viewBinding.layoutToolbar.toolbar.gone()
        }
    }

    private fun setUpViewpager() {
        val list = arguments?.getStringArrayList("list")
        val pos = arguments?.getInt("pos", 0) ?: 0
        val fromRating = arguments?.getBoolean("fromRating", false) ?: false
        viewBinding.vpImage.adapter =
            ImageZoomPagerAdapter(childFragmentManager, list ?: arrayListOf())
        viewBinding.vpImage.currentItem = pos
        viewBinding.indicator.setupWithViewPager(viewBinding.vpImage)
    }

    fun onClick(v: View?) {
        when (v?.id) {
            R.id.ivClose -> {
                navController.popBackStack()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity as? MainActivity)?.apply {
            viewBinding.layoutToolbar.toolbar.visible()
        }
    }
}