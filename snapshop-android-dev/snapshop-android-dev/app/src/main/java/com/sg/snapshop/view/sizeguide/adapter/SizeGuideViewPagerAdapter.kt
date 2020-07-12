package com.sg.snapshop.view.sizeguide.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.sg.snapshop.view.sizeguide.KidSizeFragment
import com.sg.snapshop.view.sizeguide.MenSizeFragment
import com.sg.snapshop.view.sizeguide.WomenSizeFragment

class SizeGuideViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        const val PAGE_NUMBER = 3
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> WomenSizeFragment()
            1 -> MenSizeFragment()
            else -> KidSizeFragment()
        }
    }

    override fun getCount(): Int  = PAGE_NUMBER
}