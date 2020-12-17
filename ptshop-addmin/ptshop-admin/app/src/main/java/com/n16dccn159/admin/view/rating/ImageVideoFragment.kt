package com.n16dccn159.admin.view.rating

import android.view.View
import com.n16dccn159.admin.R
import com.n16dccn159.admin.base.BaseFragment
import com.n16dccn159.admin.databinding.FragmentImageVideoBinding
import com.n16dccn159.admin.ext.gone
import com.n16dccn159.admin.ext.visible
import com.n16dccn159.admin.view.MainActivity
import com.n16dccn159.admin.view.home.StoryDetailActivity
import com.n16dccn159.admin.view.rating.adapter.ImgVideoPageAdapter
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