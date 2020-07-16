package com.sg.snapshop.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sg.core.model.Categories
import com.sg.core.model.Category
import com.sg.core.model.Gender
import com.sg.core.repository.ShopRepository
import com.sg.core.vo.Result
import kotlinx.coroutines.launch

class ShopViewModel(private val repository : ShopRepository)  : ViewModel() {


    val categoriesLiveData = MediatorLiveData<ArrayList<Category>>()
    val networkState = MutableLiveData<Boolean>()
    val error = MutableLiveData<Pair<String, Int?>>()

    fun getMainCategories(genderID: Int? = 0) {
        viewModelScope.launch {
            categoriesLiveData.addSource(repository.getMainCategories(genderID ?: 0)) {
                when (it) {
                    is Result.Loading -> {
                        networkState.value = true
                    }
                    is Result.Error -> {
                        networkState.value = false
                        error.value = Pair(it.message, it.code)
                    }
                    is Result.Success -> {
                        networkState.value = false
//                        var results: ArrayList<Category> = addSection(it)
                        categoriesLiveData.value =addSection(it.data?: arrayListOf())
                    }
                }
            }
        }
    }


    private fun filterGender(type : Gender, mainCategories : ArrayList<Categories>) : ArrayList<Categories>{
        val results = arrayListOf<Categories>()
        mainCategories.forEach {
            it.images?.forEach {image ->
                if (image.gender == type.value && it.popular){
                    results.add(it)
                    return@forEach
                }
            }
        }
        return results
    }

    private fun addSection(mainCategories : ArrayList<Category>) : ArrayList<Category>{
        val results = arrayListOf<Category>()
        var count = 0
        mainCategories.forEachIndexed { index, item ->
            count++
            results.add(item)
            if ((index + 1) % 3 == 0){
                results.add(item.copy(isSection = true, countSection = count))
                count = 0
            }
        }
        if (mainCategories.size % 3 != 0){
            count = 3 - mainCategories.size % 3
            while(count > 0){
                results.add(Category())
                count--
            }
            results.add(mainCategories.last().copy(isSection = true, countSection = mainCategories.size % 3))
        }
        return results
    }


}