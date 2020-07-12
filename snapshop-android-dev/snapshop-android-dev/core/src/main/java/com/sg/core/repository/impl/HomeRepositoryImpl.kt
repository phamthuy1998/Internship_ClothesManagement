package com.sg.core.repository.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.toLiveData
import com.sg.core.api.ApiService
import com.sg.core.data.remote.BaseDataSourceFactory
import com.sg.core.data.remote.NetworkBoundResource
import com.sg.core.model.Product
import com.sg.core.model.Stories
import com.sg.core.model.Tag
import com.sg.core.param.StoryParam
import com.sg.core.repository.HomeRepository
import com.sg.core.util.ObjectHandler
import com.sg.core.vo.ListResponse
import com.sg.core.vo.Listing
import com.sg.core.vo.Result
import retrofit2.Response

class HomeRepositoryImpl(val api: ApiService): HomeRepository {

    override suspend fun getAllStories(param: StoryParam): Listing<Stories> {
        val status = MutableLiveData<Result<Stories>>()

        val sourceFactory = object : BaseDataSourceFactory<Stories, Stories>(status) {

            override suspend fun createXCall(page: Int): Response<ListResponse<Stories>> {
                return api.getAllStories(
                    param.tags,
                    param.vendor_brand,
                    param.brand,
                    page,
                    param.limit
                )
            }
        }

        val pagedList = sourceFactory.toLiveData(
            pageSize = param.limit ?: 0
        )

        return Listing(
            result = pagedList,
            status = status,
            refresh = {
                sourceFactory.sourceLiveData.value?.invalidate()
            }
        )
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

    override suspend fun getAllTags(): LiveData<Result<ListResponse<Tag>>> {
        return object : NetworkBoundResource<ListResponse<Tag>, ListResponse<Tag>>(){
            override fun processResponse(response: ListResponse<Tag>) = response
            override suspend fun createCall(): Response<ListResponse<Tag>> = api.getTags()
        }.build().asLiveData()
    }

    override suspend fun deleteStory(id: Int?): LiveData<Result<Void>> {
        return object : NetworkBoundResource<Void, Void>(){
            override fun processResponse(response: Void) = null
            override suspend fun createCall() = api.deleteBrandStory(id)
        }.build().asLiveData()
    }

    override suspend fun getProductsDetail(
        ids: ArrayList<Int>?
    ): LiveData<Result<ListResponse<Product>>> {
        return object : NetworkBoundResource<ListResponse<Product>, ListResponse<Product>>(){
            override suspend fun createCall(): Response<ListResponse<Product>> = api.getProductsDetail(ids)

            override fun processResponse(response: ListResponse<Product>): ListResponse<Product>? {
                return response
            }

        }.build().asLiveData()
    }
}