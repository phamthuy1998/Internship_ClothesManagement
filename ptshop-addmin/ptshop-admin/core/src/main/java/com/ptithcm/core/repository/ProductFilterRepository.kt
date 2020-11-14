package com.ptithcm.core.repository

import androidx.lifecycle.LiveData
import com.ptithcm.core.model.Product
import com.ptithcm.core.model.ProductsFilter
import com.ptithcm.core.vo.ListResponse
import com.ptithcm.core.vo.Listing
import com.ptithcm.core.vo.Result

interface ProductFilterRepository {
    suspend fun testCall(): LiveData<ProductsFilter>

    suspend fun getProductFilter(): LiveData<Result<ProductsFilter>>

    suspend fun searchProductPaging(name: String?, page: Int?): Listing<Product>

    suspend fun searchProduct(name: String?, page: Int?): LiveData<Result<ListResponse<Product>>>
}