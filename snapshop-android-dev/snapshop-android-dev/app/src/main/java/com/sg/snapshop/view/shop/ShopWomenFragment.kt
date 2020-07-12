package com.sg.snapshop.view.shop

import com.sg.core.model.Gender

class ShopWomenFragment : BaseShopGenderFragment() {

    companion object{
        private val fragment = ShopWomenFragment()

        public fun newInstance() : ShopWomenFragment{
            return fragment
        }
    }

    override fun getShopType(): Gender = Gender.WOMEN

}