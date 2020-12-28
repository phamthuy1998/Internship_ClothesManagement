package com.ptithcm.core.util

import com.ptithcm.core.CoreApplication
import com.ptithcm.core.model.Cart
import com.ptithcm.core.model.ProductClothesDetail
import com.ptithcm.core.model.ProductVariant

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

    fun getNumberItem() = CoreApplication.instance.cart?.products?.fold(
        0,
        { sum, prod -> sum + (prod.quantityInCart?.quantity ?: 0) }) ?: 0

    fun getTotalPrice() = CoreApplication.instance.cart?.products?.fold(
        0.0,
        { sum, prod -> sum + (prod.getFinalPrice() * (prod.quantityInCart?.quantity ?: 1)) })

    fun getAllIdProdsInCart(): List<Int> = cart?.products?.map { it.id } ?: arrayListOf()

    //check quantity of a product in prefsUtil
    fun getQuantityProductClothesFromLocal(productId: Int?, sizeId: Int?, colorId: Int?): Int {
        val products = getProductsInCart()
        return products?.firstOrNull { it.id == productId && it.quantityInCart?.sizeId == sizeId && it.quantityInCart?.colorID == colorId }?.quantityInCart?.quantity
            ?: 0
    }

    private fun getProductsInCart(): List<ProductClothesDetail>? {
        val cart = CoreApplication.instance.cart
        return cart?.products
    }

    fun addToCart(product: ProductClothesDetail?) {
        product ?: return
        val index =
            cart?.products?.indexOfFirst { it.id == product.id && it.quantityInCart?.sizeId == product.quantityInCart?.sizeId && it.quantityInCart?.colorID == product.quantityInCart?.colorID }
                ?: -1

        if (index != -1) {
            val exitProduct = cart?.products?.getOrNull(index) ?: return
            exitProduct.quantityInCart?.quantity =
                exitProduct.quantityInCart?.quantity?.plus((product.quantityInCart?.quantity ?: 0))
        } else {
            cart?.products?.add(product)
        }
        CoreApplication.instance.saveCartToPref(cart)
    }

    fun adjustProductInCart(product: ProductClothesDetail?): ArrayList<ProductClothesDetail>? {
        val productsInCart = cart?.products
        product ?: return productsInCart

        val products =
            productsInCart?.filter { it.id == product.id && it.selectedColor?.id == product.selectedColor?.id && it.selectedSize?.id == product.selectedSize?.id }
        val isExist = products?.size ?: 0 > 1

        if (isExist) {
            val quantity = products?.fold(0, { sum, prod ->
                sum + (prod.quantityInCart?.quantity ?: 0)
            })

            products?.forEachIndexed { index, prod ->
                if (index == 0)
                    prod.quantityInCart?.quantity = quantity?.coerceAtMost(
                        prod.getSizeAndColorById(
                            sizeId = prod.selectedSize?.id,
                            colorId = prod.selectedColor?.id
                        )?.quantity ?: 0
                    )
                else {
                    productsInCart.remove(prod)
                }
            }
        }
        saveCartToPref()
        return getCartFromPref()?.products
    }


    fun saveCartToPref() {
        CoreApplication.instance.saveCartToPref(cart)
    }

    fun getCartFromPref(): Cart? {
        cart = CoreApplication.instance.getCartFromPref()
        return cart
    }
    // end section shopping bag

    // start section wish list
    fun isInWishList(id: Int?) = false
}