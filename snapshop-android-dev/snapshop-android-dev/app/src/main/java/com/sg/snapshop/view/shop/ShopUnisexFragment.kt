package com.sg.snapshop.view.shop

import com.sg.core.model.Gender

class ShopUnisexFragment : BaseShopGenderFragment() {

    companion object{
        private val fragment = ShopUnisexFragment()

        public fun newInstance() : ShopUnisexFragment{
            return fragment
        }
    }

    override fun getShopType(): Gender = Gender.UNISEX
}