package com.ptithcm.core.repository

import androidx.lifecycle.LiveData
import com.ptithcm.core.model.Product
import com.ptithcm.core.model.wish.WishResponse
import com.ptithcm.core.vo.Result

interface WishListRepository {
    suspend fun getWishList(): LiveData<Result<ArrayList<Product>>>

    suspend fun addToWishList(id: Int?): LiveData<Result<WishResponse>>

    suspend fun removeFromWishList(id: Int?): LiveData<Result<WishResponse>>
}