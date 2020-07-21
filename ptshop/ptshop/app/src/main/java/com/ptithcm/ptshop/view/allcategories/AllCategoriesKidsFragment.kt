package com.ptithcm.ptshop.view.allcategories

import com.ptithcm.core.model.Gender

class AllCategoriesKidsFragment : BaseAllCategoriesFragment() {

    companion object{
        private val fragment = AllCategoriesKidsFragment()

        fun newInstance() : AllCategoriesKidsFragment = fragment
    }


    override fun getAllCategoriesType(): Gender = Gender.KIDS
}