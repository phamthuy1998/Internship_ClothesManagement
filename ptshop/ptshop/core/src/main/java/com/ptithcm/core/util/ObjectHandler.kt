package com.ptithcm.core.util

import com.ptithcm.core.CoreApplication
import com.ptithcm.core.model.ProductClothesDetail
import com.ptithcm.core.model.ProductVariant
import com.ptithcm.core.model.SizesColor

object ObjectHandler {

    var cart = CoreApplication.instance.cart


    fun isLogin() = CoreApplication.instance.account != null

    // start section shopping bag

    fun cartSize() = cart?.products?.size ?: 0

    fun removeProdFromBasket(prodId: Long) {
        val basket = CoreApplication.instance.basket
        basket?.product_variants?.forEachIndexed { index, productVariant ->
            if (productVariant.product_variant.id == prodId) {
                val newList = ArrayList<ProductVariant>(basket.product_variants)
                newList.removeAt(index)
                basket.product_variants = newList
                return@forEachIndexed
            }
        }
    }

    fun getNumberItem() = CoreApplication.instance.cart?.products?.fold(0, { sum, prod -> sum + (prod.quantityInCart?.quantity ?: 0) }) ?: 0

    fun getTotalPrice() = CoreApplication.instance.cart?.products?.fold(0.0, {sum, prod -> sum + (prod.getFinalPrice() * (prod.quantityInCart?.quantity ?: 1))})

    //check quantity of a product in prefsUtil
    fun getQuantityProductClothesFromLocal(productId: Int?, sizeId: Int?, colorId: Int?): Int {
        val products = getProductsInCart()
        return products?.firstOrNull { it.providerId == productId && it.quantityInCart?.sizeId == sizeId && it.quantityInCart?.colorID == colorId }?.quantityInCart?.quantity ?: 0
    }

    private fun getProductsInCart(): List<ProductClothesDetail>? {
        val cart = CoreApplication.instance.cart
        return cart?.products
    }

    fun addToCart(product: ProductClothesDetail?) {
        product ?: return
        val index = cart?.products?.indexOfFirst { it.id == product.id && it.quantityInCart?.sizeId == product.quantityInCart?.sizeId && it.quantityInCart?.colorID == product.quantityInCart?.colorID } ?: -1

        if (index != -1) {
            val exitProduct = cart?.products?.getOrNull(index) ?: return
            cart?.products?.removeAt(index)
            cart?.products?.add(exitProduct)
        } else {
            cart?.products?.add(product)
        }
        CoreApplication.instance.saveCartToPref(cart)
    }

//    fun adjustProdInNotLoginBasket(
//        productVariant: ProductVariant,
//        previousVariantID: Long = -1L
//    ): ArrayList<ProductVariant> {
//        if (previousVariantID == -1L) {
//            notLoginBasket.replaceAll {
//                if (it.product_variant.id == productVariant.product_variant.id) {
//                    productVariant
//                } else {
//                    it
//                }
//            }
//        } else {
//            var isExist = false
//            var previousIndex = -1
//            notLoginBasket = notLoginBasket.mapIndexed { index, it ->
//                if (it.product_variant.id == productVariant.product_variant.id) {
//                    it.quantity += 1
//                    isExist = true
//                }
//                if (it.product_variant.id == previousVariantID) {
//                    previousIndex = index
//                }
//                it
//            } as ArrayList<ProductVariant>
//            if (isExist.not()) {
//                notLoginBasket.add(productVariant)
//            }
//            notLoginBasket.removeAt(previousIndex)
//        }
//        CoreApplication.instance.saveBasketToPref(notLoginBasket)
//        return notLoginBasket
//    }

//    fun removeFromNotLoginBasket(id: Long): ArrayList<ProductVariant> {
//        notLoginBasket.removeAll {
//            it.product_variant.id == id
//        }
//        CoreApplication.instance.saveBasketToPref(notLoginBasket)
//        return notLoginBasket
//    }
    // end section shopping bag

    // start section wish list
    fun isInWishList(id: Int?) = false
}