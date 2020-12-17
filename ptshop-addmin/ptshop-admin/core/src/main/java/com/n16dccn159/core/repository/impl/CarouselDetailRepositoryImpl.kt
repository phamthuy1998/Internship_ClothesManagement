package com.n16dccn159.core.repository.impl

import androidx.lifecycle.MutableLiveData
import androidx.paging.toLiveData
import com.n16dccn159.core.api.ApiClothesService
import com.n16dccn159.core.data.remote.BaseDataSourceFactory
import com.n16dccn159.core.model.CountViewModel
import com.n16dccn159.core.model.ProductClothes
import com.n16dccn159.core.model.SearchParams
import com.n16dccn159.core.param.ProductsOfCategoryRequestParam
import com.n16dccn159.core.param.ProductsOfProviderRequestParam
import com.n16dccn159.core.repository.CarouselDetailRepository
import com.n16dccn159.core.util.PAGE_SIZE
import com.n16dccn159.core.vo.ItemViewModel
import com.n16dccn159.core.vo.ListResponse
import com.n16dccn159.core.vo.Listing
import retrofit2.Response

class CarouselDetailRepositoryImpl(
    private val clothesApi: ApiClothesService
) : CarouselDetailRepository {
    override suspend fun getPagingProductsCarousel(
        param: ProductsOfCategoryRequestParam
    ): Listing<ItemViewModel> {
        val sourceFactory =
            object :
                BaseDataSourceFactory<ProductClothes, ItemViewModel>(status = MutableLiveData()) {
                override suspend fun createXCall(page: Int): Response<ListResponse<ProductClothes>> {
                    return clothesApi.getProducts(param.apply { pageNumber = page })
                }

                override suspend fun handleXResponse(
                    items: ListResponse<ProductClothes>, firstLoad: Boolean
                ): List<ItemViewModel> {
                    val result = arrayListOf<ItemViewModel>()

                    if (firstLoad) {
                        val countView = CountViewModel(items.count, 0x0, null)
                        result.add(countView)
                    }

                    result.addAll(items.results)
                    return result
                }
            }

        val pagedLiveData = sourceFactory.toLiveData(pageSize = PAGE_SIZE)

        return Listing(
            result = pagedLiveData,
            status = sourceFactory.status,
            refresh = {
                sourceFactory.sourceLiveData.value?.invalidate()
            }
        )
    }

    override suspend fun getPagingProductsProvider(
        param: ProductsOfProviderRequestParam
    ): Listing<ItemViewModel> {
        val sourceFactory =
            object :
                BaseDataSourceFactory<ProductClothes, ItemViewModel>(status = MutableLiveData()) {
                override suspend fun createXCall(page: Int): Response<ListResponse<ProductClothes>> {
                    return clothesApi.getProductsProvider(param.apply { pageNumber = page })
                }

                override suspend fun handleXResponse(
                    items: ListResponse<ProductClothes>, firstLoad: Boolean
                ): List<ItemViewModel> {
                    val result = arrayListOf<ItemViewModel>()

                    if (firstLoad) {
                        val countView = CountViewModel(items.count, 0x0, null)
                        result.add(countView)
                    }

                    result.addAll(items.results)
                    return result
                }
            }

        val pagedLiveData = sourceFactory.toLiveData(pageSize = PAGE_SIZE)

        return Listing(
            result = pagedLiveData,
            status = sourceFactory.status,
            refresh = {
                sourceFactory.sourceLiveData.value?.invalidate()
            }
        )
    }

    override suspend fun getPagingRefineProduct(searchParam: SearchParams?): Listing<ItemViewModel> {
        val sourceFactory =
            object :
                BaseDataSourceFactory<ProductClothes, ItemViewModel>(status = MutableLiveData()) {
                override suspend fun createXCall(page: Int): Response<ListResponse<ProductClothes>> {
                    return clothesApi.getRefineProducts(searchParam.apply {
                        this?.pageNumber = page
                    })
                }

                override suspend fun handleXResponse(
                    items: ListResponse<ProductClothes>, firstLoad: Boolean
                ): List<ItemViewModel> {
                    val result = arrayListOf<ItemViewModel>()

                    if (firstLoad) {
                        val countView = CountViewModel(items.count, 0x0, null)
                        result.add(countView)
                    }

                    result.addAll(items.results)
                    return result
                }
            }

        val pagedLiveData = sourceFactory.toLiveData(pageSize = PAGE_SIZE)

        return Listing(
            result = pagedLiveData,
            status = sourceFactory.status,
            refresh = {
                sourceFactory.sourceLiveData.value?.invalidate()
            }
        )
    }
}