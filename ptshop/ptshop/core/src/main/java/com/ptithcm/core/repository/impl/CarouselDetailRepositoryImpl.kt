package com.ptithcm.core.repository.impl

import androidx.lifecycle.MutableLiveData
import androidx.paging.toLiveData
import com.ptithcm.core.api.ApiClothesService
import com.ptithcm.core.data.remote.BaseDataSourceFactory
import com.ptithcm.core.model.CountViewModel
import com.ptithcm.core.model.ProductClothes
import com.ptithcm.core.model.SearchParams
import com.ptithcm.core.repository.CarouselDetailRepository
import com.ptithcm.core.util.PAGE_SIZE
import com.ptithcm.core.vo.ItemViewModel
import com.ptithcm.core.vo.ListResponse
import com.ptithcm.core.vo.Listing
import retrofit2.Response

class CarouselDetailRepositoryImpl(
    private val clothesApi: ApiClothesService
) : CarouselDetailRepository {
    override suspend fun getPagingProductsCarousel(
        categoryID: Int,
        pageSize: Int,
        pageNumber: Int,
        accountId: Int
    ): Listing<ItemViewModel> {
        val sourceFactory =
            object :
                BaseDataSourceFactory<ProductClothes, ItemViewModel>(status = MutableLiveData()) {
                override suspend fun createXCall(page: Int): Response<ListResponse<ProductClothes>> {
                    return clothesApi.getProducts(
                        categoryID, pageSize, page, accountId
                    )
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
        providerId: Int,
        pageSize: Int,
        pageNumber: Int,
        accountId: Int
    ): Listing<ItemViewModel> {
        val sourceFactory =
            object :
                BaseDataSourceFactory<ProductClothes, ItemViewModel>(status = MutableLiveData()) {
                override suspend fun createXCall(page: Int): Response<ListResponse<ProductClothes>> {
                    return clothesApi.getProductsProvider(
                        providerId, pageSize, page, accountId
                    )
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

    override suspend fun getPagingRefineProduct(searchParams: SearchParams?): Listing<ItemViewModel> {
        val sourceFactory =
            object :
                BaseDataSourceFactory<ProductClothes, ItemViewModel>(status = MutableLiveData()) {
                override suspend fun createXCall(page: Int): Response<ListResponse<ProductClothes>> {
                    return clothesApi.getRefineProducts(searchParams.apply {
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