package com.ptithcm.admin.view.allcategories

import com.ptithcm.core.model.Gender

class AllCategoriesUnisexFragment : BaseAllCategoriesFragment() {

    companion object {
        private val fragment = AllCategoriesUnisexFragment()
        fun newInstance(): AllCategoriesUnisexFragment = fragment
    }

    override fun getAllCategoriesType(): Gender = Gender.UNISEX
}