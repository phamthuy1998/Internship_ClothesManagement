package com.ptithcm.core.repository.impl

import androidx.lifecycle.MutableLiveData
import androidx.paging.toLiveData
import com.ptithcm.core.api.ApiService
import com.ptithcm.core.data.remote.BaseDataSourceFactory
import com.ptithcm.core.model.CountViewModel
import com.ptithcm.core.model.Product
import com.ptithcm.core.param.RefineParam
import com.ptithcm.core.param.RefineRequestParam
import com.ptithcm.core.repository.SearchRepository
import com.ptithcm.core.util.PAGE_SIZE
import com.ptithcm.core.vo.ItemViewModel
import com.ptithcm.core.vo.ListResponse
import com.ptithcm.core.vo.Listing
import retrofit2.Response

class SearchRepositoryImpl(private val api: ApiService) : SearchRepository {
    override suspend fun getPagingSearchProduct(
        key: String?, sport: String?, style: String?
    ): Listing<ItemViewModel> {
        val sourceFactory =
            object : BaseDataSourceFactory<Product, ItemViewModel>(status = MutableLiveData()) {
                override suspend fun createXCall(page: Int): Response<ListResponse<Product>> {
                    val hashMap = hashMapOf<String, String?>()
                    if (!key.isNullOrEmpty()) hashMap["search"] = key
                    if (!sport.isNullOrEmpty()) hashMap["sports"] = sport
                    if (!style.isNullOrEmpty()) hashMap["styles"] = style
                    return api.getProductSearch(hashMap, page = page)
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

    override suspend fun getPagingRefineProduct(
        refineParam: RefineParam?
    ): Listing<ItemViewModel> {
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
                        options = options, page = page
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

}