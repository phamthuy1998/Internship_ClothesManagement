package com.n16dccn159.admin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.n16dccn159.core.model.Filter

class RefineViewModel : ViewModel() {
    val filterLiveData = MutableLiveData<Pair<Filter?, Boolean>>()
}