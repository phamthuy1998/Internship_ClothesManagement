package com.ptithcm.ptshop.view.shop.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ptithcm.ptshop.view.shop.ShopKidsFragment
import com.ptithcm.ptshop.view.shop.ShopMenFragment
import com.ptithcm.ptshop.view.shop.ShopUnisexFragment
import com.ptithcm.ptshop.view.shop.ShopWomenFragment

class ShopViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        const val PAGE_NUMBER = 4
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> ShopWomenFragment.newInstance()
            1 -> ShopMenFragment.newInstance()
            2 -> ShopKidsFragment.newInstance()
            else -> ShopUnisexFragment.newInstance()
        }
    }

    override fun getCount(): Int  = PAGE_NUMBER
}