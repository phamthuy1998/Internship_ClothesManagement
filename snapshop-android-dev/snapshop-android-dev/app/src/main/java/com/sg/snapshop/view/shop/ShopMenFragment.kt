package com.sg.snapshop.view.shop

import com.sg.core.model.Gender

class ShopMenFragment : BaseShopGenderFragment() {

    companion object{
        private val fragment = ShopMenFragment()

        public fun newInstance() : ShopMenFragment{
            return fragment
        }
    }

    override fun getShopType(): Gender = Gender.MEN
}