package com.ptithcm.admin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ptithcm.core.model.Filter

class RefineViewModel : ViewModel() {
    val filterLiveData = MutableLiveData<Pair<Filter?, Boolean>>()
}