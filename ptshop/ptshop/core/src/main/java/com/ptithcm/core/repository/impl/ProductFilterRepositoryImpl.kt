package com.ptithcm.core.repository.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.toLiveData
import com.ptithcm.core.api.ApiService
import com.ptithcm.core.data.remote.BaseDataSourceFactory
import com.ptithcm.core.data.remote.NetworkBoundResource
import com.ptithcm.core.model.Product
import com.ptithcm.core.model.ProductsFilter
import com.ptithcm.core.repository.ProductFilterRepository
import com.ptithcm.core.vo.ApiResponse
import com.ptithcm.core.vo.ListResponse
import com.ptithcm.core.vo.Listing
import com.ptithcm.core.vo.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class ProductFilterRepositoryImpl(private val api: ApiService) : ProductFilterRepository {

    override suspend fun testCall(): LiveData<ProductsFilter> = withContext(Dispatchers.IO) {
        val mutableLiveData = MutableLiveData<ProductsFilter>()
        val result = api.getProductFilter()

        mutableLiveData.postValue( result.body()?.data)
        return@withContext mutableLiveData
    }
    override suspend fun getProductFilter(): LiveData<Result<ProductsFilter>> {

        return object : NetworkBoundResource<ApiResponse<ProductsFilter>, ProductsFilter>() {
            override fun processResponse(response: ApiResponse<ProductsFilter>): ProductsFilter? {
                return response.data
            }
//
//            override suspend fun loadFromDb(): ProductsFilter? {
//               return productDao.getProductFilters()
//            }

            override fun shouldFetch(data: ProductsFilter?): Boolean  = true
//
//            override suspend fun saveCallResult(item: ProductsFilter?) {
//                productDao.saveProductFilter(item ?: return)
//            }

            override suspend fun createCall(): Response<ApiResponse<ProductsFilter>> =
                api.getProductFilter()

        }.build().asLiveData()
    }

    override suspend fun searchProductPaging(name: String?, page: Int?): Listing<Product> {
        val status = MutableLiveData<Result<Product>>()

        val sourceFactory = object : BaseDataSourceFactory<Product, Product>(status) {

            override suspend fun createXCall(page: Int): Response<ListResponse<Product>> {
                return api.searchProduct(name, page)
            }
        }

        val pagedList = sourceFactory.toLiveData(
            pageSize = 20
        )

        return Listing(
            result = pagedList,
            status = status,
            refresh = {
                sourceFactory.sourceLiveData.value?.invalidate()
            }
        )
    }

    override suspend fun searchProduct(
        name: String?,
        page: Int?
    ): LiveData<Result<ListResponse<Product>>> {
        return object : NetworkBoundResource<ListResponse<Product>, ListResponse<Product>>(){
            override fun processResponse(response: ListResponse<Product>): ListResponse<Product>? {
                return response
            }

            override fun shouldFetch(data: ListResponse<Product>?): Boolean = true

            override suspend fun createCall(): Response<ListResponse<Product>> = api.searchProduct( name, page )

        }.build().asLiveData()
    }
}