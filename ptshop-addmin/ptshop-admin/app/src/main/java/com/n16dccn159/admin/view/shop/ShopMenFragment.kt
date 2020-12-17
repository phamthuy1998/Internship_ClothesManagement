package com.n16dccn159.admin.view.shop

import com.n16dccn159.core.model.Gender

class ShopMenFragment : BaseShopGenderFragment() {

    companion object{
        private val fragment = ShopMenFragment()

        fun newInstance() : ShopMenFragment{
            return fragment
        }
    }

    override fun getShopType(): Gender = Gender.MEN
}