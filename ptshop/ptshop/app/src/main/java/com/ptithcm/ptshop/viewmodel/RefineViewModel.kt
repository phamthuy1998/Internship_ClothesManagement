package com.ptithcm.ptshop.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ptithcm.core.model.Gender
import com.ptithcm.core.model.SearchParams
import com.ptithcm.core.param.CategoriesParam
import com.ptithcm.core.param.RefineParam

class RefineViewModel : ViewModel() {

    val categoriesParamLiveData = MutableLiveData<Pair<CategoriesParam, Gender>>()
    val sizeType  = MutableLiveData<Int>()
    val refineLiveData = MutableLiveData<Pair<RefineParam?, Boolean>>()
    val filterLiveData = MutableLiveData<Pair<SearchParams?, Boolean>>()
}