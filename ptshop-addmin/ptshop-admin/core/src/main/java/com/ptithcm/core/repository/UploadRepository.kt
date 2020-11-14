package com.ptithcm.core.repository

import androidx.lifecycle.LiveData
import com.ptithcm.core.model.wish.WishResponse
import com.ptithcm.core.param.UploadProductParam
import com.ptithcm.core.vo.Result

interface UploadRepository {

    suspend fun addToWishList(id: Int?): LiveData<Result<WishResponse>>

    suspend fun uploadProduct(param: UploadProductParam): LiveData<Result<String>>

}