package com.sg.core.repository

import androidx.lifecycle.LiveData
import com.sg.core.model.Basket
import com.sg.core.model.Brand
import com.sg.core.model.Product
import com.sg.core.param.AddProductParam
import com.sg.core.vo.MessageResponse
import com.sg.core.vo.Result

interface ShoppingCardRepository {
    suspend fun updateBasket(param: AddProductParam): LiveData<Result<Basket>>

    suspend fun getAllCard(): LiveData<Result<Basket>>

    suspend fun removeFromBasket(id: Long): LiveData<Result<MessageResponse>>

    suspend fun getProductDetail(id: Int?): LiveData<Result<Product>>

    suspend fun getShopDetail(id: Int?): LiveData<Result<Brand>>
}