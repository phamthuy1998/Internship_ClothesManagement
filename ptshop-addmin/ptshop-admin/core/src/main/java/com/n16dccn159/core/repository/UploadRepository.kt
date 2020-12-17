package com.n16dccn159.core.repository

import androidx.lifecycle.LiveData
import com.n16dccn159.core.model.wish.WishResponse
import com.n16dccn159.core.param.UploadProductParam
import com.n16dccn159.core.vo.Result

interface UploadRepository {

    suspend fun addToWishList(id: Int?): LiveData<Result<WishResponse>>

    suspend fun uploadProduct(param: UploadProductParam): LiveData<Result<String>>

}