package com.sg.core.repository

import androidx.lifecycle.LiveData
import com.sg.core.model.Product
import com.sg.core.model.ProductsFilter
import com.sg.core.vo.ListResponse
import com.sg.core.vo.Listing
import com.sg.core.vo.Result

interface ProductFilterRepository {
    suspend fun testCall(): LiveData<ProductsFilter>

    suspend fun getProductFilter(): LiveData<Result<ProductsFilter>>

    suspend fun searchProductPaging(name: String?, page: Int?): Listing<Product>

    suspend fun searchProduct(name: String?, page: Int?): LiveData<Result<ListResponse<Product>>>
}