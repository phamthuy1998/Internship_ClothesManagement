package com.sg.core.repository

import androidx.lifecycle.LiveData
import com.sg.core.model.wish.WishResponse
import com.sg.core.param.UploadProductParam
import com.sg.core.vo.Result

interface UploadRepository {

    suspend fun addToWishList(id: Int?): LiveData<Result<WishResponse>>

    suspend fun uploadProduct(param: UploadProductParam): LiveData<Result<String>>

}