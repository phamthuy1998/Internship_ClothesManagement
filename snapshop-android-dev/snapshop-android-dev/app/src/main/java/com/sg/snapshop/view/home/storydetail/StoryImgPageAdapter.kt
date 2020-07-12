package com.sg.snapshop.view.home.storydetail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.sg.core.BuildConfig
import com.sg.core.model.Upload
import com.sg.snapshop.view.wishlist.overview.ImageFragment
import com.sg.snapshop.view.wishlist.overview.VideoFragment

class StoryImgPageAdapter(fragmentManager: FragmentManager, private val uploads: List<Upload>): FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        val url = "${BuildConfig.AWS_URL_IMAGE}${uploads[position].url}"
        return if (url.contains(".mov"))
                VideoFragment.newInstance(url)
                else ImageFragment.newInstance(url, isForStoryDetail = true)
    }

    override fun getCount(): Int = uploads.size
}