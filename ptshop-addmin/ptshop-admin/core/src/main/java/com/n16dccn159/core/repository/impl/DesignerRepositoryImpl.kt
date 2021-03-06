package com.n16dccn159.core.repository.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.toLiveData
import com.n16dccn159.core.api.ApiService
import com.n16dccn159.core.data.remote.BaseDataSourceFactory
import com.n16dccn159.core.data.remote.NetworkBoundResource
import com.n16dccn159.core.model.Brand
import com.n16dccn159.core.param.BrandParam
import com.n16dccn159.core.repository.DesignerRepository
import com.n16dccn159.core.vo.ListResponse
import com.n16dccn159.core.vo.Listing
import com.n16dccn159.core.vo.Result
import retrofit2.Response

class DesignerRepositoryImpl(val api: ApiService) : DesignerRepository {

    override suspend fun getPoupularBrands(param: BrandParam): LiveData<Result<ListResponse<Brand>>> {
        return object : NetworkBoundResource<ListResponse<Brand>, ListResponse<Brand>>(){
            override fun processResponse(response: ListResponse<Brand>) = response
            override suspend fun createCall(): Response<ListResponse<Brand>> = api.getPopularBrands(param.filter_popular, param.only_image, param.ordering, param.page, param.limit)

        }.build().asLiveData()
    }

    override suspend fun getPopularBrandsPaging(param: BrandParam): Listing<Brand> {
        val status = MutableLiveData<Result<Brand>>()

        val sourceFactory = object : BaseDataSourceFactory<Brand, Brand>(status) {
            override suspend fun createXCall(page: Int): Response<ListResponse<Brand>> {
                return api.getPopularBrands(
                    param.filter_popular,
                    param.only_image,
                    param.ordering,
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
}