package com.ptithcm.admin.view.shop

import com.ptithcm.core.model.Gender

class ShopMenFragment : BaseShopGenderFragment() {

    companion object{
        private val fragment = ShopMenFragment()

        public fun newInstance() : ShopMenFragment{
            return fragment
        }
    }

    override fun getShopType(): Gender = Gender.MEN
}