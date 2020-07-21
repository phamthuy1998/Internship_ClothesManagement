package com.ptithcm.ptshop.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ptithcm.core.model.Carousel
import com.ptithcm.core.repository.ShopRepository
import com.ptithcm.core.vo.ListResponse
import com.ptithcm.core.vo.Result
import kotlinx.coroutines.launch

class CarouselViewModel(private val repository: ShopRepository) : ViewModel() {

    val carouselLiveData = MediatorLiveData<ListResponse<Carousel>>()
    val errorCarousel = MutableLiveData<Pair<String, Int?>>()

    fun getCarousels(){
        viewModelScope.launch {
            carouselLiveData.addSource(repository.getCarousels()){
                when(it){
                    is Result.Error -> {
                        errorCarousel.value = Pair(it.message, it.code)
                    }
                    is Result.Success -> {
                        carouselLiveData.value = it.data
                    }
                }
            }
        }
    }
}