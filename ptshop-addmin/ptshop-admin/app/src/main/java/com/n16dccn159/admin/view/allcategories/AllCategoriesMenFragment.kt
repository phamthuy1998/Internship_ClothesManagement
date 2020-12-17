package com.n16dccn159.admin.view.allcategories

import com.n16dccn159.core.model.Gender

class AllCategoriesMenFragment : BaseAllCategoriesFragment() {

    companion object{
        private val fragment = AllCategoriesMenFragment()

        fun newInstance() : AllCategoriesMenFragment = fragment
    }

    override fun getAllCategoriesType(): Gender = Gender.MEN
}