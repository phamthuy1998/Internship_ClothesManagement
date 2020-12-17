package com.n16dccn159.core.repository

import androidx.lifecycle.LiveData
import com.n16dccn159.core.model.Product
import com.n16dccn159.core.model.ProductsFilter
import com.n16dccn159.core.vo.ListResponse
import com.n16dccn159.core.vo.Listing
import com.n16dccn159.core.vo.Result

interface ProductFilterRepository {
    suspend fun testCall(): LiveData<ProductsFilter>

    suspend fun getProductFilter(): LiveData<Result<ProductsFilter>>

    suspend fun searchProductPaging(name: String?, page: Int?): Listing<Product>

    suspend fun searchProduct(name: String?, page: Int?): LiveData<Result<ListResponse<Product>>>
}