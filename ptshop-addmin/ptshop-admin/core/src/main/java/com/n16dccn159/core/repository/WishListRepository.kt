package com.n16dccn159.core.repository

import androidx.lifecycle.LiveData
import com.n16dccn159.core.model.ProductClothes
import com.n16dccn159.core.model.wish.ObjectResponse
import com.n16dccn159.core.vo.Result

interface WishListRepository {
    suspend fun getWishList(): LiveData<Result<ObjectResponse<ArrayList<ProductClothes>>>>

    suspend fun addAndRemoveToWishList(id: Int?): LiveData<Result<ObjectResponse<Int>>>
}