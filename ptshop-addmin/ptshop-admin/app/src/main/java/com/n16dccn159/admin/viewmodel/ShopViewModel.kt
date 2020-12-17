package com.n16dccn159.admin.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.n16dccn159.core.model.Category
import com.n16dccn159.core.repository.ShopRepository
import com.n16dccn159.core.vo.Result
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
                        categoriesLiveData.value = addSection(it.data ?: arrayListOf())
                    }
                }
            }
        }
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