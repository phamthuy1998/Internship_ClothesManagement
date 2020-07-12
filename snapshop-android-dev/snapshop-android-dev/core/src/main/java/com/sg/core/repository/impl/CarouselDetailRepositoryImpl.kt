package com.sg.core.repository.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.toLiveData
import com.sg.core.api.ApiService
import com.sg.core.data.remote.BaseDataSourceFactory
import com.sg.core.data.remote.NetworkBoundResource
import com.sg.core.model.*
import com.sg.core.param.RefineParam
import com.sg.core.param.RefineRequestParam
import com.sg.core.repository.CarouselDetailRepository
import com.sg.core.util.PAGE_SIZE
import com.sg.core.vo.ItemViewModel
import com.sg.core.vo.ListResponse
import com.sg.core.vo.Listing
import com.sg.core.vo.Result
import retrofit2.Response

class CarouselDetailRepositoryImpl(private val api: ApiService) : CarouselDetailRepository {
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

    override suspend fun getPagingProductsCarousel(
        mainCategories: ArrayList<Int>?, categories: ArrayList<Int>?,
        filters: ArrayList<Int>?, gender: Int?
    ): Listing<ItemViewModel> {
        val sourceFactory =
            object : BaseDataSourceFactory<Product, ItemViewModel>(status = MutableLiveData()) {
                override suspend fun createXCall(page: Int): Response<ListResponse<Product>> {
                    return api.getProductCarousel(
                        mainCategories, if (gender == Gender.NONE.value) null else gender,
                        page, categories, filters
                    )
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

    override suspend fun getPagingRefineProduct(refineParam: RefineParam?): Listing<ItemViewModel> {
//        val refineRequestParam = RefineRequestParam().convertParam(refineParam)
        val refineRequestParam = RefineRequestParam().convertParam(refineParam)
        val options = RefineRequestParam().optionsMap(refineParam)
        val optionsSearch = RefineRequestParam().optionsSearchMap(refineParam)
        val sourceFactory =
            object : BaseDataSourceFactory<Product, ItemViewModel>(status = MutableLiveData()) {
                override suspend fun createXCall(page: Int): Response<ListResponse<Product>> {
                    return api.getProductRefine(
                        designers = refineRequestParam.designers,
                        genders = refineRequestParam.gender,
                        mainCategories = refineRequestParam.main_category,
                        categories = refineRequestParam.category,
                        filters = refineRequestParam.filters,
                        sizes = refineRequestParam.size,
                        colours = refineRequestParam.colour,
                        optionsSearch = optionsSearch,
                        options = options, page = page)
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
}