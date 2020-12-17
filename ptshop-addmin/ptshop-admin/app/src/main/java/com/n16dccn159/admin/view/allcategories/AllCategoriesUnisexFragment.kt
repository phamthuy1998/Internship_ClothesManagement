package com.n16dccn159.admin.view.allcategories

import com.n16dccn159.core.model.Gender

class AllCategoriesUnisexFragment : BaseAllCategoriesFragment() {

    companion object {
        private val fragment = AllCategoriesUnisexFragment()
        fun newInstance(): AllCategoriesUnisexFragment = fragment
    }

    override fun getAllCategoriesType(): Gender = Gender.UNISEX
}