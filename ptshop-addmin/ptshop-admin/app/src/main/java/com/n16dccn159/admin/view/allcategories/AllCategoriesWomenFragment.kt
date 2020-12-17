package com.n16dccn159.admin.view.allcategories

import com.n16dccn159.core.model.Gender

class AllCategoriesWomenFragment : BaseAllCategoriesFragment() {

    companion object{

        private val fragment = AllCategoriesWomenFragment()

        fun newInstance() : AllCategoriesWomenFragment = fragment
    }


    override fun getAllCategoriesType(): Gender = Gender.WOMEN
}