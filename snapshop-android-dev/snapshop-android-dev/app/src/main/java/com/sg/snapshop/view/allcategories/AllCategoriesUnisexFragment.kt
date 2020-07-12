package com.sg.snapshop.view.allcategories

import com.sg.core.model.Gender

class AllCategoriesUnisexFragment : BaseAllCategoriesFragment() {

    companion object{
        private val fragment = AllCategoriesUnisexFragment()

        fun newInstance() : AllCategoriesUnisexFragment = fragment
    }

    override fun getAllCategoriesType(): Gender = Gender.UNISEX
}