package com.sg.snapshop.view.allcategories

import com.sg.core.model.Gender

class AllCategoriesKidsFragment : BaseAllCategoriesFragment() {

    companion object{
        private val fragment = AllCategoriesKidsFragment()

        fun newInstance() : AllCategoriesKidsFragment = fragment
    }


    override fun getAllCategoriesType(): Gender = Gender.KIDS
}