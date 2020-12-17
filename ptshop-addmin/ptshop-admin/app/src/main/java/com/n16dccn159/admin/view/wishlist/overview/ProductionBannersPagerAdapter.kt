package com.n16dccn159.admin.view.wishlist.overview

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.n16dccn159.core.model.Image

class ProductionBannersPagerAdapter(fm: FragmentManager, private val promotionBanners: List<Image>, private val listener: ((list: List<Image>, pos: Int) -> Unit)? = null) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): androidx.fragment.app.Fragment {
        val promotionBanner = promotionBanners[position]
        return ImageFragment.newInstance(promotionBanner.src_original){
            listener?.invoke(promotionBanners, position)
        }
    }

    override fun getCount(): Int = promotionBanners.size
}