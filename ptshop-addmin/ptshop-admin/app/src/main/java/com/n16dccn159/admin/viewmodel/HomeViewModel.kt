package com.n16dccn159.admin.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.n16dccn159.core.model.Product
import com.n16dccn159.core.model.Stories
import com.n16dccn159.core.model.Tag
import com.n16dccn159.core.param.StoryParam
import com.n16dccn159.core.repository.HomeRepository
import com.n16dccn159.core.vo.Listing
import com.n16dccn159.core.vo.Result
import kotlinx.coroutines.launch

class HomeViewModel(private val repo: HomeRepository): ViewModel() {

    val storyLiveData = MediatorLiveData<PagedList<Stories>>()
    val error = MutableLiveData<Pair<String, Int?>>()
    val networkState = MutableLiveData<Boolean>()
    val networkLoadMore = MutableLiveData<Boolean>()

    private lateinit var repoResult: Listing<Stories>

    val productLiveData = MediatorLiveData<Product>()
    val productsLiveData = MediatorLiveData<ArrayList<Product>>()
    val tagsLiveData = MediatorLiveData<ArrayList<Tag>>()
    val listTagChoose = MutableLiveData<ArrayList<Int>>()

    private var isRefresh = false

    val deleteResult = MediatorLiveData<Boolean>()

    fun getAllStories(param: StoryParam){
        isRefresh = false
        viewModelScope.launch {
            repoResult = repo.getAllStories(param)

            storyLiveData.addSource(repoResult.result) {
                storyLiveData.value = it
                networkState.value = false
            }

            storyLiveData.addSource(repoResult.status) {
                when (it) {
                    is Result.Loading -> {
                        if (isRefresh.not())
                            networkState.value = true
                    }
                    is Result.LoadingMore -> {
                        networkLoadMore.value = true
                    }
                    is Result.Error -> {
                        networkState.value = false
                        networkLoadMore.value = false
                        error.value = Pair(it.message, it.code)
                    }
                    is Result.Success -> {
                        networkLoadMore.value = false
                    }
                }
            }
        }
    }

    fun getAllTags(){
        viewModelScope.launch {
            tagsLiveData.addSource(repo.getAllTags()){
                when(it){
                    Result.Loading -> {
                        networkState.value = true
                    }
                    is Result.Error -> {
                        error.value = Pair(it.message, it.code)
                    }
                    is Result.Success -> {
                        tagsLiveData.value = it.data?.results
                    }
                }
            }
        }
    }

    fun getProductDetail(id: Int){
        viewModelScope.launch {
            productLiveData.addSource(repo.getProductDetail(id)){
                when(it){
                    Result.Loading -> {}
                    is Result.Error -> {
                        error.value = Pair(it.message, it.code)
                    }
                    is Result.Success -> {
                        productLiveData.value = it.data
                    }
                }
            }
        }
    }

    fun getProductsDetail(ids: ArrayList<Int>?){
        viewModelScope.launch {
            productsLiveData.addSource(repo.getProductsDetail(ids)){
                when(it){
                    Result.Loading -> {}
                    is Result.Error -> {
                        error.value = Pair(it.message, it.code)
                    }
                    is Result.Success -> {
                        productsLiveData.value = it.data?.results
                    }
                }
            }
        }
    }

    fun refreshStories() {
        repoResult.refresh.invoke()
        isRefresh = true
    }

    fun deleteStory(id: Int?){
        viewModelScope.launch {
            deleteResult.addSource(repo.deleteStory(id)){
                when(it){
                    Result.Loading -> {}
                    is Result.Error -> {
                        error.value = Pair(it.message, it.code)
                    }
                    is Result.Success -> {
                        deleteResult.value = true
                    }
                }
            }
        }
    }
}