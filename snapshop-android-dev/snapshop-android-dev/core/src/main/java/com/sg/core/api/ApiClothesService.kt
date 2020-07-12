package com.sg.core.api

import com.sg.core.model.Account
import com.sg.core.model.wish.ObjectResponse
import com.sg.core.param.LogInParam
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiClothesService {
    @POST("/api/login")
    suspend fun signIn(@Body loginParam: LogInParam): Response<ObjectResponse<Account>>
}