package com.sg.snapshop.view.allcategories

import com.sg.core.model.Gender

class AllCategoriesWomenFragment : BaseAllCategoriesFragment() {

    companion object{

        private val fragment = AllCategoriesWomenFragment()

        fun newInstance() : AllCategoriesWomenFragment = fragment
    }


    override fun getAllCategoriesType(): Gender = Gender.WOMEN
}