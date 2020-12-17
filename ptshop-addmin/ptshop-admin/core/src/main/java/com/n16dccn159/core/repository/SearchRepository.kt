package com.n16dccn159.core.repository

import com.n16dccn159.core.param.RefineParam
import com.n16dccn159.core.vo.ItemViewModel
import com.n16dccn159.core.vo.Listing

interface SearchRepository {

    suspend fun getPagingSearchProduct(key: String?,sport:String?=null,style:String?=null) : Listing<ItemViewModel>

    suspend fun getPagingRefineProduct(refineParam: RefineParam?): Listing<ItemViewModel>

}