package com.n16dccn159.admin.view.shop

import com.n16dccn159.core.model.Gender

class ShopUnisexFragment : BaseShopGenderFragment() {

    companion object{
        private val fragment = ShopUnisexFragment()

        public fun newInstance() : ShopUnisexFragment{
            return fragment
        }
    }

    override fun getShopType(): Gender = Gender.UNISEX
}