package com.ptithcm.core.repository.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.toLiveData
import com.ptithcm.core.api.ApiClothesService
import com.ptithcm.core.api.ApiService
import com.ptithcm.core.data.remote.BaseDataSourceFactory
import com.ptithcm.core.data.remote.NetworkBoundResource
import com.ptithcm.core.model.*
import com.ptithcm.core.repository.CarouselDetailRepository
import com.ptithcm.core.util.PAGE_SIZE
import com.ptithcm.core.vo.ItemViewModel
import com.ptithcm.core.vo.ListResponse
import com.ptithcm.core.vo.Listing
import com.ptithcm.core.vo.Result
import retrofit2.Response

class CarouselDetailRepositoryImpl(
    private val api: ApiService,
    private val clothesApi: ApiClothesService
) : CarouselDetailRepository {
    override suspend fun getPagingBrandsProduct(
        vendorBrandId: List<String?>?,
        gender: Int?
    ): Listing<ItemViewModel> {
        val sourceFactory =
            object : BaseDataSourceFactory<Product, ItemViewModel>(status = MutableLiveData()) {
                override suspend fun createXCall(page: Int): Response<ListResponse<Product>> {
                    return if (gender == Gender.NONE.value)
                        api.getBrandProductDetail(vendorBrandId, null, page)
                    else
                        api.getBrandProductDetail(vendorBrandId, gender, page)
                }

                override suspend fun handleXResponse(
                    items: ListResponse<Product>, firstLoad: Boolean
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

    override suspend fun getVendorBrand(brandId: Int?): LiveData<Result<Brand>> {
        return object : NetworkBoundResource<Brand, Brand>() {
            override fun processResponse(response: Brand) = response
            override suspend fun createCall(): Response<Brand> = api.getVendorBrand(brandId)

        }.build().asLiveData()
    }

    override suspend fun getStoreBrand(storeId: Int?): LiveData<Result<Brand>> {
        return object : NetworkBoundResource<Brand, Brand>() {
            override fun processResponse(response: Brand) = response
            override suspend fun createCall(): Response<Brand> = api.getStoreBrand(storeId)
        }.build().asLiveData()
    }

    override suspend fun getPagingStoresCarousel(bandId: Int?): Listing<Stories> {

        val sourceFactory = object : BaseDataSourceFactory<Stories, Stories>(MutableLiveData()) {

            override suspend fun createXCall(page: Int): Response<ListResponse<Stories>> {
                return api.getStoreCarousel(bandId, page, PAGE_SIZE)
            }


        }

        val pagedList = sourceFactory.toLiveData(
            pageSize = PAGE_SIZE
        )

        return Listing(
            result = pagedList,
            status = sourceFactory.status,
            refresh = {
                sourceFactory.sourceLiveData.value?.invalidate()
            }
        )
    }

    override suspend fun getPagingBrandsCarousel(
        vendorBrandId: String?,
        gender: Int?
    ): Listing<ItemViewModel> {

        val sourceFactory =
            object : BaseDataSourceFactory<Product, ItemViewModel>(status = MutableLiveData()) {
                override suspend fun createXCall(page: Int): Response<ListResponse<Product>> {
                    return if (gender == Gender.NONE.value)
                        api.getBrandCarousel(vendorBrandId, null, page)
                    else
                        api.getBrandCarousel(vendorBrandId, gender, page)
                }

                override suspend fun handleXResponse(
                    items: ListResponse<Product>, firstLoad: Boolean
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

//    override suspend fun getPagingProductsCarousel(
//        mainCategories: ArrayList<Int>?, categories: ArrayList<Int>?,
//        filters: ArrayList<Int>?, gender: Int?
//    ): Listing<ItemViewModel> {
//        val sourceFactory =
//            object : BaseDataSourceFactory<Product, ItemViewModel>(status = MutableLiveData()) {
//                override suspend fun createXCall(page: Int): Response<ListResponse<Product>> {
//                    return api.getProductCarousel(
//                        mainCategories, if (gender == Gender.NONE.value) null else gender,
//                        page, categories, filters
//                    )
//                }
//
//                override suspend fun handleXResponse(
//                    items: ListResponse<Product>, firstLoad: Boolean
//                ): List<ItemViewModel> {
//                    val result = arrayListOf<ItemViewModel>()
//
//                    if (firstLoad) {
//                        val countView = CountViewModel(items.count, 0x0, null)
//                        result.add(countView)
//                    }
//
//                    result.addAll(items.results)
//                    return result
//                }
//            }
//
//        val pagedLiveData = sourceFactory.toLiveData(pageSize = PAGE_SIZE)
//
//        return Listing(
//            result = pagedLiveData,
//            status = sourceFactory.status,
//            refresh = {
//                sourceFactory.sourceLiveData.value?.invalidate()
//            }
//        )
//    }

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