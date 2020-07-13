package com.sg.core.api

import com.sg.core.model.Account
import com.sg.core.model.Profile
import com.sg.core.model.wish.ObjectResponse
import com.sg.core.param.ForgotPasswordParam
import com.sg.core.param.LogInParam
import com.sg.core.param.RegisterParam
import com.sg.core.vo.ApiResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiClothesService {
    @POST("/api/login")
    suspend fun signIn(@Body loginParam: LogInParam): Response<ObjectResponse<Account>>

    @DELETE("/api/logout")
    suspend fun logOut(): Response<Void>

    @POST("/api/forgotPassword")
    suspend fun forgotPassword(@Body param: ForgotPasswordParam): Response<ObjectResponse<String>>

    @POST("/api/signUp")
    suspend fun signUp(@Body acc: Account): Response<ObjectResponse<Account>>
}