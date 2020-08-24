package com.ptithcm.core.repository

import com.ptithcm.core.model.SearchParams
import com.ptithcm.core.param.ProductsOfCategoryRequestParam
import com.ptithcm.core.param.ProductsOfProviderRequestParam
import com.ptithcm.core.vo.ItemViewModel
import com.ptithcm.core.vo.Listing

interface CarouselDetailRepository {
    suspend fun getPagingProductsCarousel(
        param: ProductsOfCategoryRequestParam
    ): Listing<ItemViewModel>

    suspend fun getPagingProductsProvider(
        param: ProductsOfProviderRequestParam
    ): Listing<ItemViewModel>

    suspend fun getPagingRefineProduct(
        searchParam: SearchParams?
    ): Listing<ItemViewModel>
}