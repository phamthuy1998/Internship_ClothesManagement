package com.sg.snapshop.view.allcategories

import com.sg.core.model.Gender

class AllCategoriesMenFragment : BaseAllCategoriesFragment() {

    companion object{
        private val fragment = AllCategoriesMenFragment()

        fun newInstance() : AllCategoriesMenFragment = fragment
    }

    override fun getAllCategoriesType(): Gender = Gender.MEN
}