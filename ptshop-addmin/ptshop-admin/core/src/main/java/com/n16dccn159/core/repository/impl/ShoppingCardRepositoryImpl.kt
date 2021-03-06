package com.n16dccn159.core.repository.impl

import androidx.lifecycle.LiveData
import com.n16dccn159.core.api.ApiClothesService
import com.n16dccn159.core.api.ApiService
import com.n16dccn159.core.data.remote.NetworkBoundResource
import com.n16dccn159.core.model.Basket
import com.n16dccn159.core.model.Brand
import com.n16dccn159.core.model.ProductClothesDetail
import com.n16dccn159.core.model.wish.ObjectResponse
import com.n16dccn159.core.param.AddProductParam
import com.n16dccn159.core.repository.ShoppingCardRepository
import com.n16dccn159.core.util.ObjectHandler
import com.n16dccn159.core.vo.MessageResponse
import com.n16dccn159.core.vo.Result
import retrofit2.Response

class ShoppingCardRepositoryImpl(val api: ApiService, val apiClothesService: ApiClothesService) :
    ShoppingCardRepository {

    override suspend fun updateBasket(param: AddProductParam): LiveData<Result<Basket>> {
        return object : NetworkBoundResource<Basket, Basket>() {
            override suspend fun createCall(): Response<Basket> = api.updateBasket(param)

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
        return object : NetworkBoundResource<Basket, Basket>() {
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
        return object : NetworkBoundResource<MessageResponse, MessageResponse>() {
            override fun processResponse(response: MessageResponse) = response
            override suspend fun createCall(): Response<MessageResponse> =
                api.removeItemFromBasket(id)
        }.build().asLiveData()
    }

    override suspend fun getProductDetail(id: Int?): LiveData<Result<ObjectResponse<ProductClothesDetail>>> {
        return object :
            NetworkBoundResource<ObjectResponse<ProductClothesDetail>, ObjectResponse<ProductClothesDetail>>() {
            override suspend fun createCall(): Response<ObjectResponse<ProductClothesDetail>> =
                apiClothesService.getProductDetail(id ?: 0)

            override fun processResponse(response: ObjectResponse<ProductClothesDetail>): ObjectResponse<ProductClothesDetail>? {
                return response
            }
        }.build().asLiveData()
    }

    override suspend fun getAllProductsInCart(ids: List<Int>): LiveData<Result<ArrayList<ProductClothesDetail>>> {
        return object :
            NetworkBoundResource<ArrayList<ProductClothesDetail>, ArrayList<ProductClothesDetail>>() {
            override suspend fun createCall(): Response<ArrayList<ProductClothesDetail>> =
                apiClothesService.getAllProductsInCart(ids)

            override fun processResponse(response: ArrayList<ProductClothesDetail>): ArrayList<ProductClothesDetail>? {
                response.forEachIndexed { i, newProduct ->
                    val curProduct = ObjectHandler.cart?.products?.getOrNull(i)
                    if (newProduct.id != curProduct?.id)
                        return@forEachIndexed

                    newProduct.run {
                        selectedColor = curProduct.selectedColor
                        selectedSize = curProduct.selectedSize
                        quantityInCart = curProduct.quantityInCart

                        hasChangedPrice =
                            newProduct.price != curProduct.price || newProduct.typePromotion != curProduct.typePromotion || newProduct.valuePromotion != curProduct.valuePromotion

                        val newSizeAndColor = newProduct.getSizeAndColorById(
                            sizeId = curProduct.selectedSize?.id,
                            colorId = curProduct.selectedColor?.id
                        )
                        val quantityInStock = newSizeAndColor?.quantity ?: 0
                        hasChangedQuantity =
                            curProduct.quantityInCart?.quantity ?: 0 > quantityInStock

                        hasChanged = hasChangedPrice || hasChangedQuantity

                    }
                }
                return response
            }
        }.build().asLiveData()
    }

    override suspend fun getShopDetail(id: Int?): LiveData<Result<Brand>> {
        return object : NetworkBoundResource<Brand, Brand>() {
            override fun processResponse(response: Brand) = response
            override fun shouldFetch(data: Brand?): Boolean = true
            override suspend fun createCall(): Response<Brand> = api.getStoreBrand(id)
        }.build().asLiveData()
    }
}