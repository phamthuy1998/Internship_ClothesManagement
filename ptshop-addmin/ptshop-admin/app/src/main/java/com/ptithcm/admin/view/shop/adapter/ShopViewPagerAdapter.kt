package com.ptithcm.admin.view.shop.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ptithcm.admin.view.shop.ShopMenFragment
import com.ptithcm.admin.view.shop.ShopUnisexFragment
import com.ptithcm.admin.view.shop.ShopWomenFragment

class ShopViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        const val PAGE_NUMBER = 3
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> ShopWomenFragment.newInstance()
            1 -> ShopMenFragment.newInstance()
            else -> ShopUnisexFragment.newInstance()
        }
    }

    override fun getCount(): Int  = PAGE_NUMBER
}