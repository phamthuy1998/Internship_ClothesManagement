package com.ptithcm.core.api

import com.ptithcm.core.model.Account
import com.ptithcm.core.model.Category
import com.ptithcm.core.model.Product
import com.ptithcm.core.model.ProductClothes
import com.ptithcm.core.model.wish.ObjectResponse
import com.ptithcm.core.param.ChangePassParam
import com.ptithcm.core.param.EditAccountParam
import com.ptithcm.core.param.ForgotPasswordParam
import com.ptithcm.core.param.LogInParam
import com.ptithcm.core.vo.ListResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiClothesService {
    /*-----------------------------*/
    /* Auth */
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

    /*-----------------------------*/
    /* Category */
    @GET("/api/categories")
    suspend fun getMainCategories(@Query("genderID") genderID: Int): Response<ArrayList<Category>>

    /*-----------------------------*/
    /* Produtc*/
    @GET("/api/products")
    suspend fun getProducts(
        @Query("categoryID") categoryID: Int,
        @Query("pageSize") pageSize: Int = 20,
        @Query("pageNumber") pageNumber: Int = 1,
        @Query("accountId") accountId: Int
    ): Response<ListResponse<ProductClothes>>


}

