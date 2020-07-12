package com.sg.core.repository

import androidx.lifecycle.LiveData
import com.sg.core.model.Product
import com.sg.core.model.wish.WishResponse
import com.sg.core.vo.Result

interface WishListRepository {
    suspend fun getWishList(): LiveData<Result<ArrayList<Product>>>

    suspend fun addToWishList(id: Int?): LiveData<Result<WishResponse>>

    suspend fun removeFromWishList(id: Int?): LiveData<Result<WishResponse>>
}