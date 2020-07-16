package com.sg.core.api

import com.sg.core.model.Account
import com.sg.core.model.Category
import com.sg.core.model.wish.ObjectResponse
import com.sg.core.param.ChangePassParam
import com.sg.core.param.EditAccountParam
import com.sg.core.param.ForgotPasswordParam
import com.sg.core.param.LogInParam
import retrofit2.Response
import retrofit2.http.*

interface ApiClothesService {
    @POST("/api/login")
    suspend fun signIn(@Body loginParam: LogInParam): Response<ObjectResponse<Account>>

    @DELETE("/api/logout")
    suspend fun logOut(): Response<Void>

    @POST("/api/forgotPassword")
    suspend fun forgotPassword(@Body param: ForgotPasswordParam): Response<ObjectResponse<String>>

    @POST("/api/signUp")
    suspend fun signUp(@Body acc: Account): Response<ObjectResponse<Account>>

    @PUT("/api/changePassword")
    suspend fun changePassword(@Body param: ChangePassParam): Response<ObjectResponse<Account>>

    @PUT("/api/changeAccInfo")
    suspend fun updateProfile(@Body param: EditAccountParam): Response<ObjectResponse<Account>>

    @GET("/api/categories")
    suspend fun getMainCategories(@Query("genderID")  genderID: Int): Response<ArrayList<Category>>
}

