package com.n16dccn159.admin.view.shop

import com.n16dccn159.core.model.Gender

class ShopWomenFragment : BaseShopGenderFragment() {

    companion object{
        private val fragment = ShopWomenFragment()

        public fun newInstance() : ShopWomenFragment{
            return fragment
        }
    }

    override fun getShopType(): Gender = Gender.WOMEN

}