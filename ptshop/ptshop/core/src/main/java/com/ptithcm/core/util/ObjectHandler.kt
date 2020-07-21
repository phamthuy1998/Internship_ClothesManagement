package com.ptithcm.core.util

import com.ptithcm.core.CoreApplication
import com.ptithcm.core.model.ProductVariant

object ObjectHandler {

    var notLoginBasket = CoreApplication.instance.notLoginBasket
    private val prodWishList by lazy {
        CoreApplication.instance.prodWishList
    }

    fun isLogin() = CoreApplication.instance.profile != null

    // start section shopping bag

    fun notLoginBasketSize() = notLoginBasket.size

    fun removeProdFromBasket(prodId: Long){
        val basket = CoreApplication.instance.basket
        basket?.product_variants?.forEachIndexed { index, productVariant ->
            if (productVariant.product_variant.id == prodId){
                val newList = ArrayList<ProductVariant>(basket.product_variants)
                newList.removeAt(index)
                basket.product_variants = newList
                return@forEachIndexed
            }
        }
    }

        //check quantity of a product in prefsUtil
    fun getQuantityFromLocal(variantId: Long?): Int{
        return if (isLogin()) {
            //check in basket - get from BE
            val mapVariant = getBasketVariant()
            mapVariant?.get(variantId) ?: 0
        }else{
            // not_login_basket - just save in local
            val productVariant = notLoginBasket.find {
                it.product_variant.id == variantId
            }
            productVariant?.quantity ?: 0
        }
    }

    fun getNumberItem() = (
        if (isLogin())
            CoreApplication.instance.basket?.product_variants
        else
            notLoginBasket
    )?.fold(0, {sum, prod -> sum + prod.quantity }) ?: 0

    private fun getBasketVariant(): Map<Long? , Int>? {
        var map = mapOf<Long?, Int>()
        val basket = CoreApplication.instance.basket
        basket?.product_variants?.forEach {
            map = map + Pair(it.product_variant.id, it.quantity)
        }
        return map
    }

    fun addToNotLoginBasket(productVariant: ProductVariant){
        val index = notLoginBasket.indexOfFirst {
            it.product_variant.id == productVariant.product_variant.id
        }
        if (index != -1){
            val exitProductVariant = notLoginBasket[index]
            notLoginBasket.removeAt(index)
            notLoginBasket.add(
                index,
                ProductVariant(
                    exitProductVariant.product_variant,
                    exitProductVariant.quantity + productVariant.quantity,
                    exitProductVariant.applied_discount
                )
            )
        } else {
            notLoginBasket.add(productVariant)
        }
        CoreApplication.instance.saveBasketToPref(notLoginBasket)
    }

    fun adjustProdInNotLoginBasket(productVariant: ProductVariant, previousVariantID: Long = -1L): ArrayList<ProductVariant>{
        if (previousVariantID == -1L) {
            notLoginBasket.replaceAll {
                if (it.product_variant.id == productVariant.product_variant.id){
                    productVariant
                }else{
                    it
                }
            }
        }else{
            var isExist = false
            var previousIndex = -1
            notLoginBasket = notLoginBasket.mapIndexed { index, it ->
                if (it.product_variant.id == productVariant.product_variant.id){
                    it.quantity += 1
                    isExist = true
                }
                if (it.product_variant.id == previousVariantID){
                    previousIndex = index
                }
                it
            } as ArrayList<ProductVariant>
            if (isExist.not()){
                notLoginBasket.add(productVariant)
            }
            notLoginBasket.removeAt(previousIndex)
        }
        CoreApplication.instance.saveBasketToPref(notLoginBasket)
        return notLoginBasket
    }

    fun removeFromNotLoginBasket(id: Long): ArrayList<ProductVariant>{
        notLoginBasket.removeAll {
            it.product_variant.id == id
        }
        CoreApplication.instance.saveBasketToPref(notLoginBasket)
        return notLoginBasket
    }
    // end section shopping bag

    // start section wish list
    fun isInWishList(id: Int?) = prodWishList.contains(id)

    fun addAllToWishListLocal(arr: ArrayList<Int>){
        prodWishList.clear()
        prodWishList.addAll(arr)
        CoreApplication.instance.saveWishListToPref(prodWishList)
    }

    fun addToWishListLocal(id: Int){
        prodWishList.add(id)
        CoreApplication.instance.saveWishListToPref(prodWishList)
    }

    fun removeFromWishListLocal(id: Int){
        prodWishList.remove(id)
        CoreApplication.instance.saveWishListToPref(prodWishList)
    }
    // end section wish list

}