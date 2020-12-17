package com.n16dccn159.admin.view.uploadproduct.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.n16dccn159.admin.view.uploadproduct.UploadLibraryFragment
import com.n16dccn159.admin.view.uploadproduct.UploadPhotoFragment
import com.n16dccn159.admin.view.uploadproduct.UploadVideoFragment

class UploadViewpagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    override fun getCount() = 3

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> UploadPhotoFragment()
            1 -> UploadVideoFragment()
            else -> UploadLibraryFragment()
        }
    }
}