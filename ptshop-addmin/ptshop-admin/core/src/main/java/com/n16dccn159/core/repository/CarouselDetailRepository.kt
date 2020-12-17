package com.n16dccn159.core.repository

import com.n16dccn159.core.model.SearchParams
import com.n16dccn159.core.param.ProductsOfCategoryRequestParam
import com.n16dccn159.core.param.ProductsOfProviderRequestParam
import com.n16dccn159.core.vo.ItemViewModel
import com.n16dccn159.core.vo.Listing

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