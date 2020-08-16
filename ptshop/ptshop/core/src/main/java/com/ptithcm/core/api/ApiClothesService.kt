package com.ptithcm.core.api

import com.ptithcm.core.CoreApplication
import com.ptithcm.core.model.*
import com.ptithcm.core.model.wish.ObjectResponse
import com.ptithcm.core.param.ChangePassParam
import com.ptithcm.core.param.EditAccountParam
import com.ptithcm.core.param.LogInParam
import com.ptithcm.core.param.RequestCheckoutParam
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

    @POST("/api/forgot-password")
    suspend fun forgotPassword(@Query("email") email: String): Response<ObjectResponse<String>>

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
    ): Response<ObjectResponse<ProductClothesDetail>>

    @POST("/api/get-all-product-cart")
    suspend fun getAllProductsInCart(
        @Body ids: List<Int>
    ): Response<ArrayList<ProductClothesDetail>>

    @POST("/api/get-products")
    suspend fun getRefineProducts(
        @Body searchParams: SearchParams?
    ): Response<ListResponse<ProductClothes>>

    /*-----------------------------*/
    /* Wish Product*/
    @GET("/api/favoriteProducts")
    suspend fun getAllWishList(
        @Query("pageSize") pageSize: Int? = 100,
        @Query("accountId") accountId: Int? = CoreApplication.instance.account?.id
    ): Response<ObjectResponse<ArrayList<ProductClothes>>>

    @PUT("/api/checkFavoriteProduct")
    suspend fun addAndRemoveToWishList(
        @Query("productId") providerId: Int?,
        @Query("accountId") accountId: Int? = CoreApplication.instance.account?.id
    ): Response<ObjectResponse<Int>>


    /*-----------------------------*/
    /* Providers*/
    @GET("/api/providers")
    suspend fun getProviders(): Response<ArrayList<Provider>>

    @GET("/api/providerDetail")
    suspend fun getDetailProvider(
        @Query("providerId") providerId: Int?
    ): Response<Provider>

    /*-----------------------------*/
    /* Address*/
    @GET("/api/address")
    suspend fun getAllAddress(@Query("accountId") accountId: Int? = CoreApplication.instance.account?.id): Response<ArrayList<ShoppingAddress>>

    @POST("/api/add-addess")
    suspend fun addAddress(@Body param: ShoppingAddress): Response<ObjectResponse<Int>>

    @PUT("/api/edit-addess")
    suspend fun updateAddress(@Body param: ShoppingAddress): Response<ObjectResponse<Int>>

    @DELETE("/api/del-addess")
    suspend fun deleteAddress(@Query("addressId") addressId: Int?): Response<ObjectResponse<Int>>

    /*-----------------------------*/
    /* Invoice*/
    @GET("/api/allInvoice")
    suspend fun getAllInvoices(
        @Query("pageSize") pageSize: Int = 20,
        @Query("pageNumber") pageNumber: Int = 1,
        @Query("statusId") statusId: Int,
        @Query("accountId") accountId: Int? = CoreApplication.instance.account?.id
    ): Response<ListResponse<Invoice>>

    @GET("/api/invoice-detail")
    suspend fun getInvoiceDetail(@Query("invoicecId") invoiceId: Int?): Response<ObjectResponse<InvoiceDetail>>

    @POST("/api/addInvoice")
    suspend fun requestCheckout(@Body param: RequestCheckoutParam): Response<ObjectResponse<Any>>
}

