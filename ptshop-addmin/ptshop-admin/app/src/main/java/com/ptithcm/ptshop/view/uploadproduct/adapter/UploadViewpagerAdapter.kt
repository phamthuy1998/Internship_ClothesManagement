package com.ptithcm.ptshop.view.uploadproduct.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ptithcm.ptshop.view.uploadproduct.UploadLibraryFragment
import com.ptithcm.ptshop.view.uploadproduct.UploadPhotoFragment
import com.ptithcm.ptshop.view.uploadproduct.UploadVideoFragment

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