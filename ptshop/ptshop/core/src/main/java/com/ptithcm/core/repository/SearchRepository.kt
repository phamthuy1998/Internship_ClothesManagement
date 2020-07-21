package com.ptithcm.core.repository

import com.ptithcm.core.param.RefineParam
import com.ptithcm.core.vo.ItemViewModel
import com.ptithcm.core.vo.Listing

interface SearchRepository {

    suspend fun getPagingSearchProduct(key: String?,sport:String?=null,style:String?=null) : Listing<ItemViewModel>

    suspend fun getPagingRefineProduct(refineParam: RefineParam?): Listing<ItemViewModel>

}