package com.sg.core.repository

import com.sg.core.param.RefineParam
import com.sg.core.vo.ItemViewModel
import com.sg.core.vo.Listing

interface SearchRepository {

    suspend fun getPagingSearchProduct(key: String?,sport:String?=null,style:String?=null) : Listing<ItemViewModel>

    suspend fun getPagingRefineProduct(refineParam: RefineParam?): Listing<ItemViewModel>

}