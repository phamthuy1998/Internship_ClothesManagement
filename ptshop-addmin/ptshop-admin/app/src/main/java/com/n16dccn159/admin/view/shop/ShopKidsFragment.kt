package com.n16dccn159.admin.view.shop

import com.n16dccn159.core.model.Gender

class ShopKidsFragment : BaseShopGenderFragment() {

    companion object{
        private val fragment = ShopKidsFragment()

        public fun newInstance() : ShopKidsFragment{
            return fragment
        }
    }

    override fun getShopType(): Gender = Gender.KIDS
}