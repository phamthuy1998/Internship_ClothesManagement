package com.ptithcm.core.repository

import com.ptithcm.core.model.SearchParams
import com.ptithcm.core.vo.ItemViewModel
import com.ptithcm.core.vo.Listing

interface CarouselDetailRepository {
    suspend fun getPagingProductsCarousel(
        categoryID: Int,
        pageSize: Int,
        pageNumber: Int,
        accountId: Int
    ): Listing<ItemViewModel>

    suspend fun getPagingProductsProvider(
        providerId: Int,
        pageSize: Int,
        pageNumber: Int,
        accountId: Int
    ): Listing<ItemViewModel>

    suspend fun getPagingRefineProduct(
        searchParam: SearchParams?
    ): Listing<ItemViewModel>


}