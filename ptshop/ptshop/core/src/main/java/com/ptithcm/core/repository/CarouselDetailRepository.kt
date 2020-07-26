package com.ptithcm.core.repository

import androidx.lifecycle.LiveData
import com.ptithcm.core.model.Brand
import com.ptithcm.core.model.Stories
import com.ptithcm.core.vo.ItemViewModel
import com.ptithcm.core.vo.Listing
import com.ptithcm.core.vo.Result

interface CarouselDetailRepository {

    suspend fun getVendorBrand(brandId: Int?): LiveData<Result<Brand>>

    suspend fun getStoreBrand(storeId: Int?): LiveData<Result<Brand>>

    suspend fun getPagingBrandsCarousel(
        vendorBrandId: String?,
        gender: Int?
    ): Listing<ItemViewModel>

    suspend fun getPagingBrandsProduct(
        vendorBrandId: List<String?>?,
        gender: Int?
    ): Listing<ItemViewModel>

//    suspend fun getPagingProductsCarousel(
//        mainCategories: ArrayList<Int>?,
//        categories: ArrayList<Int>?,
//        filters: ArrayList<Int>?,
//        gender: Int?
//    ): Listing<ItemViewModel>

    suspend fun getPagingStoresCarousel(bandId: Int?): Listing<Stories>

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
}