package com.ptithcm.core.api

import com.ptithcm.core.CoreApplication
import com.ptithcm.core.model.*
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
    /* Product*/
    @GET("/api/allProductsOfCategory")
    suspend fun getProducts(
        @Query("categoryID") categoryID: Int,
        @Query("pageSize") pageSize: Int = 20,
        @Query("pageNumber") pageNumber: Int = 1,
        @Query("accountId") accountId: Int
    ): Response<ListResponse<ProductClothes>>

    @GET("/api/allProductsOfProvider")
    suspend fun getProductsProvider(
        @Query("providerId") providerId: Int,
        @Query("pageSize") pageSize: Int = 20,
        @Query("pageNumber") pageNumber: Int = 1,
        @Query("accountId") accountId: Int
    ): Response<ListResponse<ProductClothes>>

    @GET("/api/productDetail")
    suspend fun getProductDetail(
        @Query("productID") providerId: Int,
        @Query("accountId") accountId: Int? = CoreApplication.instance.account?.id
    ): Response<ProductClothesDetail>

    /*-----------------------------*/
    /* Providers*/
    @GET("/api/providers")
    suspend fun getProviders(): Response<ArrayList<Provider>>

    @GET("/api/providerDetail")
    suspend fun getDetailProvider(
        @Query("providerId") providerId: Int?
    ): Response<Provider>
}

