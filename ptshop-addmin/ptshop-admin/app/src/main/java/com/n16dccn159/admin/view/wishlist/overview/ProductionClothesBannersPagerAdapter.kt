package com.n16dccn159.admin.view.wishlist.overview

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ProductionClothesBannersPagerAdapter(
    fm: FragmentManager,
    private val promotionBanners: List<String>,
    private val listener: ((list: List<String>, pos: Int) -> Unit)? = null
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): androidx.fragment.app.Fragment {
        val promotionBanner = promotionBanners[position]
        return ImageFragment.newInstance(promotionBanner) {
            listener?.invoke(promotionBanners, position)
        }
    }

    override fun getCount(): Int = promotionBanners.size
}