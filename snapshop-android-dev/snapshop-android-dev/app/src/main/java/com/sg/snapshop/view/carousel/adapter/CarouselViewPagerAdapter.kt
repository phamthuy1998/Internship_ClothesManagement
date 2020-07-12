package com.sg.snapshop.view.carousel.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.sg.core.model.TypeCarousel
import com.sg.snapshop.view.carousel.CarouselAboutFragment
import com.sg.snapshop.view.carousel.CarouselProductFragment
import com.sg.snapshop.view.carousel.CarouselStoriesFragment

class CarouselViewPagerAdapter(private val type : String?, fm: FragmentManager, private val isFromBrandProfile: Boolean = false) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        const val PAGE_ALL_NUMBER = 3
        private const val PAGE_BRAND_NUMBER = 1
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> CarouselProductFragment()
            1 -> CarouselStoriesFragment(isFromBrandProfile)
            else -> CarouselAboutFragment()
        }
    }

    override fun getCount(): Int = when(type){
        TypeCarousel.BRAND.value -> PAGE_BRAND_NUMBER
        else -> PAGE_ALL_NUMBER
    }


}