package com.ptithcm.ptshop.view.allcategories

import com.ptithcm.core.model.Gender

class AllCategoriesMenFragment : BaseAllCategoriesFragment() {

    companion object{
        private val fragment = AllCategoriesMenFragment()

        fun newInstance() : AllCategoriesMenFragment = fragment
    }

    override fun getAllCategoriesType(): Gender = Gender.MEN
}