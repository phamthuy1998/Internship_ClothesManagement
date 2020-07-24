package com.ptithcm.core.repository.impl

import androidx.lifecycle.LiveData
import com.ptithcm.core.api.ApiService
import com.ptithcm.core.data.remote.NetworkBoundResource
import com.ptithcm.core.model.Basket
import com.ptithcm.core.model.Brand
import com.ptithcm.core.model.Product
import com.ptithcm.core.param.AddProductParam
import com.ptithcm.core.repository.ShoppingCardRepository
import com.ptithcm.core.util.ObjectHandler
import com.ptithcm.core.vo.MessageResponse
import com.ptithcm.core.vo.Result
import retrofit2.Response

class ShoppingCardRepositoryImpl(val api: ApiService): ShoppingCardRepository {

    override suspend fun updateBasket(param: AddProductParam): LiveData<Result<Basket>> {
        return object : NetworkBoundResource<Basket, Basket>(){
            override suspend fun createCall(): Response<Basket> =api.updateBasket(param)

            override fun processResponse(response: Basket): Basket? {
                response.product_variants.forEach {
                    it.product_variant.apply {
                        product?.isAddProduct = ObjectHandler.isInWishList(product?.id)
                        checkIfWrongPrice()
                    }
                }
                return response
            }
        }.build().asLiveData()
    }

    override suspend fun getAllCard(): LiveData<Result<Basket>> {
        return object : NetworkBoundResource<Basket, Basket>(){
            override suspend fun createCall(): Response<Basket> = api.getBasket()

            override fun processResponse(response: Basket): Basket? {
                response.product_variants.forEach {
                    it.product_variant.apply {
                        product?.isAddProduct = ObjectHandler.isInWishList(product?.id)
                        checkIfWrongPrice()
                    }
                }
                return response
            }
        }.build().asLiveData()
    }

    override suspend fun removeFromBasket(id: Long): LiveData<Result<MessageResponse>> {
        return object : NetworkBoundResource<MessageResponse, MessageResponse>(){
            override fun processResponse(response: MessageResponse) = response
            override suspend fun createCall(): Response<MessageResponse> = api.removeItemFromBasket(id)
        }.build().asLiveData()
    }

    override suspend fun getProductDetail(id: Int?): LiveData<Result<Product>> {
        return object : NetworkBoundResource<Product, Product>(){
            override suspend fun createCall(): Response<Product> = api.getProductDetail(id)

            override fun processResponse(response: Product): Product? {
                response.apply {
                    variants?.sortBy { it.position }
                    checkVariantWrongPrice()
                    isAddProduct = ObjectHandler.isInWishList(response.id)
                }
                return response
            }
        }.build().asLiveData()
    }

    override suspend fun getShopDetail(id: Int?): LiveData<Result<Brand>> {
        return object : NetworkBoundResource<Brand, Brand>(){
            override fun processResponse(response: Brand) = response
            override fun shouldFetch(data: Brand?): Boolean = true
            override suspend fun createCall(): Response<Brand> = api.getStoreBrand(id)
        }.build().asLiveData()
    }
}