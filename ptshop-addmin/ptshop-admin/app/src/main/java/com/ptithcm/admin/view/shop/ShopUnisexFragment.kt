package com.ptithcm.admin.view.shop

import com.ptithcm.core.model.Gender

class ShopUnisexFragment : BaseShopGenderFragment() {

    companion object{
        private val fragment = ShopUnisexFragment()

        public fun newInstance() : ShopUnisexFragment{
            return fragment
        }
    }

    override fun getShopType(): Gender = Gender.UNISEX
}