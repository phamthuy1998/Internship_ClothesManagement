package com.ptithcm.core.repository

import androidx.lifecycle.LiveData
import com.ptithcm.core.model.ProductClothes
import com.ptithcm.core.model.wish.ObjectResponse
import com.ptithcm.core.vo.Result

interface WishListRepository {
    suspend fun getWishList(): LiveData<Result<ObjectResponse<ArrayList<ProductClothes>>>>

    suspend fun addAndRemoveToWishList(id: Int?): LiveData<Result<ObjectResponse<Int>>>
}