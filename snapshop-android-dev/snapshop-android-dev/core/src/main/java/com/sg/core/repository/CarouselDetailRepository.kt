package com.sg.core.repository

import androidx.lifecycle.LiveData
import com.sg.core.model.Brand
import com.sg.core.model.Stories
import com.sg.core.param.RefineParam
import com.sg.core.vo.ItemViewModel
import com.sg.core.vo.Listing
import com.sg.core.vo.Result

interface CarouselDetailRepository {

    suspend fun getVendorBrand(brandId : Int?): LiveData<Result<Brand>>

    suspend fun getStoreBrand(storeId : Int?): LiveData<Result<Brand>>

    suspend fun getPagingBrandsCarousel(vendorBrandId : String?, gender : Int?): Listing<ItemViewModel>

    suspend fun getPagingBrandsProduct(vendorBrandId : List<String?>?, gender : Int?): Listing<ItemViewModel>

    suspend fun getPagingProductsCarousel(mainCategories : ArrayList<Int>?, categories: ArrayList<Int>?, filters: ArrayList<Int>?, gender : Int?): Listing<ItemViewModel>

    suspend fun getPagingStoresCarousel(bandId : Int?): Listing<Stories>

    suspend fun getPagingRefineProduct(refineParam: RefineParam?) : Listing<ItemViewModel>
}