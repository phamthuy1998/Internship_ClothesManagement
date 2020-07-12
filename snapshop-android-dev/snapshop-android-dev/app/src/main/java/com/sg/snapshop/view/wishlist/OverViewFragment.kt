package com.sg.snapshop.view.wishlist

import android.view.View
import com.sg.core.model.Image
import com.sg.snapshop.R
import com.sg.snapshop.base.BaseFragment
import com.sg.snapshop.databinding.FragmentOverViewBinding
import com.sg.snapshop.ext.gone
import com.sg.snapshop.ext.visible
import com.sg.snapshop.view.MainActivity
import com.sg.snapshop.view.home.StoryDetailActivity
import com.sg.snapshop.view.wishlist.overview.ImageZoomPagerAdapter
import com.sg.snapshop.view.wishlist.overview.ProductionBannersPagerAdapter

class OverViewFragment: BaseFragment<FragmentOverViewBinding>() {

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

    private fun setUpViewpager(){
        val list = arguments?.getParcelableArrayList<Image>("list")
        val pos = arguments?.getInt("pos", 0) ?: 0

        viewBinding.vpImage.adapter = ImageZoomPagerAdapter( childFragmentManager,list as ArrayList<Image>)
        viewBinding.vpImage.currentItem = pos
        viewBinding.indicator.setupWithViewPager(viewBinding.vpImage)
    }

    fun onClick(v: View?){
        when(v?.id){
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