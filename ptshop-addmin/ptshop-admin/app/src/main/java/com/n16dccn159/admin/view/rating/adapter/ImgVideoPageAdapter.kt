package com.n16dccn159.admin.view.rating.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.n16dccn159.admin.view.wishlist.overview.ImageZoomFragment
import com.n16dccn159.admin.view.wishlist.overview.VideoFragment

class ImgVideoPageAdapter(
    fragmentManager: FragmentManager,
    private val urlList: List<String>
) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        val url = urlList[position]
        return if (url.contains(".mov") || url.contains(".mp4"))
            VideoFragment.newInstance(url)
        else ImageZoomFragment.newInstance(url)
    }

    override fun getCount(): Int = urlList.size
}