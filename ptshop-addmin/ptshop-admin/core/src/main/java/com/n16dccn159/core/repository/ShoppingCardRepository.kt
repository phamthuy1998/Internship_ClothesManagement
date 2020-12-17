package com.n16dccn159.core.repository

import androidx.lifecycle.LiveData
import com.n16dccn159.core.model.Basket
import com.n16dccn159.core.model.Brand
import com.n16dccn159.core.model.ProductClothesDetail
import com.n16dccn159.core.model.wish.ObjectResponse
import com.n16dccn159.core.param.AddProductParam
import com.n16dccn159.core.vo.MessageResponse
import com.n16dccn159.core.vo.Result

interface ShoppingCardRepository {
    suspend fun updateBasket(param: AddProductParam): LiveData<Result<Basket>>

    suspend fun getAllCard(): LiveData<Result<Basket>>

    suspend fun removeFromBasket(id: Long): LiveData<Result<MessageResponse>>

    suspend fun getShopDetail(id: Int?): LiveData<Result<Brand>>

    suspend fun getProductDetail(id: Int?): LiveData<Result<ObjectResponse<ProductClothesDetail>>>

    suspend fun getAllProductsInCart(ids: List<Int>): LiveData<Result<ArrayList<ProductClothesDetail>>>
}