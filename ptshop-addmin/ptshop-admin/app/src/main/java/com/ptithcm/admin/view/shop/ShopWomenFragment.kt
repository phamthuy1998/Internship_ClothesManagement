package com.ptithcm.admin.view.shop

import com.ptithcm.core.model.Gender

class ShopWomenFragment : BaseShopGenderFragment() {

    companion object{
        private val fragment = ShopWomenFragment()

        public fun newInstance() : ShopWomenFragment{
            return fragment
        }
    }

    override fun getShopType(): Gender = Gender.WOMEN

}