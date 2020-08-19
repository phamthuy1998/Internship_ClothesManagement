package com.ptithcm.ptshop.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ptithcm.core.model.SearchParams

class RefineViewModel : ViewModel() {
    val filterLiveData = MutableLiveData<Pair<SearchParams?, Boolean>>()
}