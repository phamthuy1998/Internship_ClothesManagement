package com.sg.snapshop.view.wishlist.overview

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.sg.core.model.Image

class ImageZoomPagerAdapter(fm: FragmentManager, private val promotionBanners: List<Image>, private val listener: ((list: List<Image>, pos: Int) -> Unit)? = null) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): androidx.fragment.app.Fragment {
        val promotionBanner = promotionBanners[position]
        return ImageZoomFragment.newInstance(promotionBanner.src_original){
            listener?.invoke(promotionBanners, position)
        }
    }

    override fun getCount(): Int = promotionBanners.size
}