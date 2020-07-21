package com.ptithcm.ptshop.view.allcategories

import com.ptithcm.core.model.Gender

class AllCategoriesWomenFragment : BaseAllCategoriesFragment() {

    companion object{

        private val fragment = AllCategoriesWomenFragment()

        fun newInstance() : AllCategoriesWomenFragment = fragment
    }


    override fun getAllCategoriesType(): Gender = Gender.WOMEN
}