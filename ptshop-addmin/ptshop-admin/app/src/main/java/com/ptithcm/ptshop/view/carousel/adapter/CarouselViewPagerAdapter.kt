package com.ptithcm.ptshop.view.carousel.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ptithcm.ptshop.view.carousel.CarouselAboutFragment
import com.ptithcm.ptshop.view.carousel.CarouselProductFragment

class CarouselViewPagerAdapter(
    fm: FragmentManager
) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        const val PAGE_ALL_NUMBER = 2
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> CarouselProductFragment()
            else -> CarouselAboutFragment()
        }
    }

    override fun getCount(): Int = PAGE_ALL_NUMBER
}