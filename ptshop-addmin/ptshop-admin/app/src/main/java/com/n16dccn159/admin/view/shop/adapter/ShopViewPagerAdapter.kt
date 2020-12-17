package com.n16dccn159.admin.view.shop.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.n16dccn159.admin.view.shop.BaseShopGenderFragment
import com.n16dccn159.admin.view.shop.ShopMenFragment
import com.n16dccn159.admin.view.shop.ShopUnisexFragment
import com.n16dccn159.admin.view.shop.ShopWomenFragment

class ShopViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        const val PAGE_NUMBER = 3
    }
    private var arrFragment: ArrayList<BaseShopGenderFragment> = arrayListOf()
    init {
        arrFragment.add(ShopWomenFragment.newInstance())
        arrFragment.add(ShopMenFragment.newInstance())
        arrFragment.add(ShopUnisexFragment.newInstance())
    }

    override fun getItem(position: Int): Fragment {
        return arrFragment.get(position)
    }

    fun onRefresh(position: Int){
        arrFragment.get(position).onRefreshData()
    }

    override fun getCount(): Int  = PAGE_NUMBER
}