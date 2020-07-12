package com.sg.snapshop.view.allcategories.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.sg.snapshop.view.allcategories.AllCategoriesKidsFragment
import com.sg.snapshop.view.allcategories.AllCategoriesMenFragment
import com.sg.snapshop.view.allcategories.AllCategoriesUnisexFragment
import com.sg.snapshop.view.allcategories.AllCategoriesWomenFragment

class AllCategoriesPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        const val PAGE_NUMBER = 4
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> AllCategoriesWomenFragment.newInstance()
            1 -> AllCategoriesMenFragment.newInstance()
            2 -> AllCategoriesKidsFragment.newInstance()
            else -> AllCategoriesUnisexFragment.newInstance()
        }
    }

    override fun getCount(): Int = PAGE_NUMBER
}