package com.sg.snapshop.view.shop

import com.sg.core.model.Gender

class ShopKidsFragment : BaseShopGenderFragment() {

    companion object{
        private val fragment = ShopKidsFragment()

        public fun newInstance() : ShopKidsFragment{
            return fragment
        }
    }

    override fun getShopType(): Gender = Gender.KIDS
}