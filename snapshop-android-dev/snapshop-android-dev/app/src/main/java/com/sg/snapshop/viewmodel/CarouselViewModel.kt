package com.sg.snapshop.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sg.core.model.Carousel
import com.sg.core.repository.ShopRepository
import com.sg.core.vo.ListResponse
import com.sg.core.vo.Result
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