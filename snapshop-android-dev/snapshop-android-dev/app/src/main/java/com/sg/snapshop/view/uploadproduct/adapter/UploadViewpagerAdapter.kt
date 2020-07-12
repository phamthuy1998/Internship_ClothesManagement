package com.sg.snapshop.view.uploadproduct.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.sg.snapshop.view.uploadproduct.UploadLibraryFragment
import com.sg.snapshop.view.uploadproduct.UploadPhotoFragment
import com.sg.snapshop.view.uploadproduct.UploadVideoFragment

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