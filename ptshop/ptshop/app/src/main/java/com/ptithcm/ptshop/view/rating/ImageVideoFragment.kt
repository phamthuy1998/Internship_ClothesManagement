package com.ptithcm.ptshop.view.rating

import android.view.View
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.base.BaseFragment
import com.ptithcm.ptshop.databinding.FragmentImageVideoBinding
import com.ptithcm.ptshop.databinding.FragmentOverViewBinding
import com.ptithcm.ptshop.ext.gone
import com.ptithcm.ptshop.ext.visible
import com.ptithcm.ptshop.view.MainActivity
import com.ptithcm.ptshop.view.home.StoryDetailActivity
import com.ptithcm.ptshop.view.rating.adapter.ImgVideoPageAdapter
import com.ptithcm.ptshop.view.wishlist.overview.ImageZoomPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class ImageVideoFragment : BaseFragment<FragmentImageVideoBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_image_video

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

    override fun onResume() {
        super.onResume()
        activity?.btnNav?.visibility = View.GONE
    }

    private fun setUpViewpager() {
        val list = arguments?.getStringArrayList("list")
        val pos = arguments?.getInt("pos", 0) ?: 0
        viewBinding.vpImage.adapter =
            ImgVideoPageAdapter(childFragmentManager, list ?: arrayListOf())
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