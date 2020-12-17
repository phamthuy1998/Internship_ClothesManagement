package com.n16dccn159.admin.view.allcategories.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.n16dccn159.admin.view.allcategories.AllCategoriesKidsFragment
import com.n16dccn159.admin.view.allcategories.AllCategoriesMenFragment
import com.n16dccn159.admin.view.allcategories.AllCategoriesUnisexFragment
import com.n16dccn159.admin.view.allcategories.AllCategoriesWomenFragment
import org.jetbrains.anko.doAsync

class AllCategoriesPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        const val PAGE_NUMBER = 4
    }

    override fun getItem(position: Int): Fragment {
        doAsync {

        }
        return when(position){
            0 -> AllCategoriesWomenFragment.newInstance()
            1 -> AllCategoriesMenFragment.newInstance()
            2 -> AllCategoriesKidsFragment.newInstance()
            else -> AllCategoriesUnisexFragment.newInstance()
        }

    }

    override fun getCount(): Int = PAGE_NUMBER
}