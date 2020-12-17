package com.n16dccn159.admin.view.allcategories

import com.n16dccn159.core.model.Gender

class AllCategoriesKidsFragment : BaseAllCategoriesFragment() {

    companion object{
        private val fragment = AllCategoriesKidsFragment()

        fun newInstance() : AllCategoriesKidsFragment = fragment
    }


    override fun getAllCategoriesType(): Gender = Gender.KIDS
}