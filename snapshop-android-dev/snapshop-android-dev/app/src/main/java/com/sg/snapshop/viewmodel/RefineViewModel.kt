package com.sg.snapshop.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sg.core.model.Gender
import com.sg.core.param.CategoriesParam
import com.sg.core.param.RefineParam

class RefineViewModel : ViewModel() {

    val categoriesParamLiveData = MutableLiveData<Pair<CategoriesParam, Gender>>()
    val sizeType  = MutableLiveData<Int>()
    val refineLiveData = MutableLiveData<Pair<RefineParam?, Boolean>>()
}