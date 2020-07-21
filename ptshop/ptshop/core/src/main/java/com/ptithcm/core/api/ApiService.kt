package com.ptithcm.core.api

import com.ptithcm.core.model.*
import com.ptithcm.core.model.Tag
import com.ptithcm.core.model.wish.WishResponse
import com.ptithcm.core.param.*
import com.ptithcm.core.util.INIT_PAGE
import com.ptithcm.core.util.LIMIT_ITEM
import com.ptithcm.core.util.PAGE_SIZE
import com.ptithcm.core.vo.ApiResponse
import com.ptithcm.core.vo.ListResponse
import com.ptithcm.core.vo.MessageResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("/api/payments/currencies/")
    suspend fun getSupportedCurrencies(): Response<ArrayList<SupportedCurrency>>

    @GET("/api/stories/tags/?limit=1000")
    suspend fun getTags(): Response<ListResponse<Tag>>

    @GET("/api/stories/list/")
    suspend fun getAllStories(
        @Query("tags") tags: List<Int>?, @Query("vendor_brand") vendor_id: Int?,
        @Query("brand") brand_id: Int?, @Query("page") page: Int?,
        @Query("limit") limit: Int?
    ): Response<ListResponse<Stories>>

    @GET("/api/products/me/wishlist/")
    suspend fun getAllWishList(): Response<ArrayList<Product>>

    @POST("/api/products/me/wishlist/")
    suspend fun addToWishList(@Body param: WishListParam): Response<WishResponse>

    @HTTP(method = "DELETE", path = "/api/products/me/wishlist/", hasBody = true)
    @FormUrlEncoded
    suspend fun removeFromWishList(@Field("product_id") product_id: Int?): Response<WishResponse>

    @POST("/api/users/login/")
    suspend fun logIn(@Body param: LogInParam): Response<Profile>

    @POST("/api/users/register/")
    suspend fun signUp(@Body param: RegisterParam): Response<Profile>

    @DELETE("/api/users/logout/")
    suspend fun logOut(): Response<Void>

    @POST("/api/users/forgot_password/")
    suspend fun forgotPassword(@Body param: ForgotPasswordParam): Response<ApiResponse<String>>

    @GET("/api/users/me/")
    suspend fun getProfile(): Response<User>

    @PATCH("/api/users/me/")
    suspend fun updateProfile(@Body param: UpdateDetailParam): Response<User>

    @PATCH("/api/users/me/")
    suspend fun changePassword(@Body param: ChangePasswordParam): Response<User>

    @PATCH("/api/users/me/")
    suspend fun updateBookAddress(@Body param: UpdateAddressParam): Response<User>

    @GET("/api/brands/carousels")
    suspend fun getCarousels(): Response<ListResponse<Carousel>>

    @GET("/api/products/main-categories/?ordering=order_by&limit=100")
    suspend fun getMainCategories(): Response<ListResponse<Categories>>

    @GET("/api/products/filter")
    suspend fun getProductFilter(): Response<ApiResponse<ProductsFilter>>

    @GET("api/payments/basket/shipping_locations/")
    suspend fun getListLocation(): Response<ListLocation>

    @PATCH("api/payments/basket/")
    suspend fun updateBookAddressPayment(@Body param: UpdateAddressParam): Response<Basket>

    @GET("api/payments/basket/shipping_locations/{id}/")
    suspend fun getLocation(@Path("id") id: Int): Response<Location>

    @GET("api/payments/stripe/payment-methods/")
    suspend fun getPaymentMethods(): Response<ArrayList<CreditCard?>>

    @DELETE("api/payments/stripe/payment-methods/{cardId}/")
    suspend fun deletePaymentMethod(@Path("cardId") cardId: String): Response<Void>

    @PUT("api/payments/stripe/payment-methods/{cardId}/")
    suspend fun updatePaymentMethod(@Path("cardId") cardId: String, @Body param: UpdatePaymentMethodParam): Response<CreditCard>

    @POST("api/payments/stripe/payment-methods/")
    suspend fun postPaymentMethod(@Body param: PaymentMethodParam): Response<ApiResponse<String>>

    @GET("/api/brands/brand/{brandId}/")
    suspend fun getVendorBrand(@Path("brandId") brandId: Int?): Response<Brand>

    @GET("/api/brands/store/{storeId}/")
    suspend fun getStoreBrand(@Path("storeId") storeId: Int?): Response<Brand>

    @GET("/api/brands/brand/list/")
    suspend fun getPopularBrands(
        @Query("filter_popular") filterPopular: Boolean?, @Query("only_image") onlyImage: Boolean?,
        @Query("ordering") ordering: String?, @Query("page") page: Int?,
        @Query("limit") limit: Int?
    ): Response<ListResponse<Brand>>

    @GET("api/products/")
    suspend fun getBrandCarousel(
        @Query("designers") designers: String?, @Query("gender") gender: Int?,
        @Query("page") page: Int?, @Query("limit") limit: Int = PAGE_SIZE
    ): Response<ListResponse<Product>>

    @GET("api/products/")
    suspend fun getBrandProductDetail(
        @Query("designers") designers: List<String?>?, @Query("gender") gender: Int?,
        @Query("page") page: Int?, @Query("limit") limit: Int = PAGE_SIZE
    ): Response<ListResponse<Product>>

    @GET("api/stories/list/")
    suspend fun getStoreCarousel(
        @Query("brand") brand: Int?, @Query("page") page: Int?,
        @Query("limit") pageSize: Int?
    ): Response<ListResponse<Stories>>

    @GET("api/products/")
    suspend fun getProductCarousel(
        @Query("main_category") mainCategories: ArrayList<Int>?, @Query("gender") gender: Int?,
        @Query("page") page: Int?, @Query("category") categories: ArrayList<Int>?, @Query("filters") filters: ArrayList<Int>?, @Query(
            "limit"
        ) limit: Int? = PAGE_SIZE
    ): Response<ListResponse<Product>>

    @GET("/api/products/{id}/")
    suspend fun getProductDetail(@Path("id") id: Int?): Response<Product>

    @GET("/api/brands/brand/list/flat/?only_image=false&ordering=-popular_score")
    suspend fun getBrands(
        @Query("page") page: Int?,
        @Query("limit") limit: Int = LIMIT_ITEM
    ): Response<ListResponse<Brand>>

    @GET("/api/brands/store/list/?ordering=-popular_score")
    suspend fun getStories(
        @Query("page") page: Int?,
        @Query("limit") limit: Int = LIMIT_ITEM
    ): Response<ListResponse<Stories>>

    @PATCH("/api/payments/basket/")
    suspend fun updateBasket(@Body param: AddProductParam): Response<Basket>

    @GET("/api/payments/basket/")
    suspend fun getBasket(): Response<Basket>

    @DELETE("/api/payments/basket/{id}/")
    suspend fun removeItemFromBasket(@Path("id") id: Long): Response<MessageResponse>

    @GET("api/products/")
    suspend fun getProductRefine(
        @Query("designers") designers: ArrayList<String>?, @Query("gender") genders: ArrayList<Int>?,
        @Query("main_category") mainCategories: ArrayList<Int>?, @Query("category") categories: ArrayList<Int>?,
        @Query("filters") filters: ArrayList<Int>?, @Query("colour") colours: ArrayList<String>?,
        @Query("size") sizes: ArrayList<String>?, @QueryMap options: Map<String, String?>,
        @QueryMap optionsSearch: Map<String, String?>, @Query("page") page: Int = INIT_PAGE,
        @Query("limit") limit: Int = PAGE_SIZE
    ): Response<ListResponse<Product>>

    @GET("api/products/")
    suspend fun getProductSearch(
        @QueryMap optionsSearch: Map<String, String?>, @Query("page") page: Int?, @Query("limit") limit: Int? = PAGE_SIZE
    ): Response<ListResponse<Product>>

    @GET("/api/payments/checkout/")
    suspend fun getCheckout(): Response<Checkout>

    @GET("/api/payments/checkout/shipping_rates/{brand_id}/{identifier}")
    suspend fun getShippingRates(@Path("brand_id") id: Int?, @Path("identifier") identifier: String?): Response<ShippingRate>

    @PATCH("/api/payments/checkout/")
    suspend fun addDiscountCode(@Body param: DiscountParam): Response<Checkout>

    @GET("/api/payments/checkout/tax/{brand_id}/{identifier}")
    suspend fun getTaxeCheckout(@Path("brand_id") id: Int?, @Path("identifier") identifier: String?): Response<TaxCheckout>

    @PATCH("/api/payments/checkout/shipping_rates/{brand_id}/{identifier}/")
    suspend fun checkShippingRate(
        @Path("brand_id") id: Int?,
        @Path("identifier") identifier: String?, @Body param: ShippingRateParam
    ): Response<Checkout>

    @POST("/api/payments/checkout/payment/")
    suspend fun checkout(@Body param: CheckoutParam): Response<Checkout>

    @GET("api/brands/me/products/")
    suspend fun searchProduct(
        @Query("name") name: String?, @Query("page") page: Int?
    ): Response<ListResponse<Product>>

    @POST("/api/stories/?")
    suspend fun uploadProduct(@Body param: UploadProductParam): Response<ApiResponse<String>>

    @DELETE("/api/stories/inactive/{id}/? ")
    suspend fun deleteBrandStory(@Path("id") id: Int?): Response<Void>

    @GET("/api/products/")
    suspend fun getProductsDetail(
        @Query("product") ids: ArrayList<Int>?,
        @Query("page") page: Int = 1
    ): Response<ListResponse<Product>>
}