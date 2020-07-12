package com.sg.snapshop.viewmodel

import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.Transformations.switchMap
import com.sg.core.model.Brand
import com.sg.core.model.Designer
import com.sg.core.model.Stories
import com.sg.core.repository.BrandsRepository
import com.sg.core.util.INIT_PAGE
import com.sg.core.util.LIMIT_ITEM
import com.sg.core.vo.Result
import com.sg.snapshop.constant.KEY_EMPTY
import com.sg.snapshop.ext.deAccent
import com.sg.snapshop.ext.equalExt
import kotlinx.coroutines.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.math.roundToInt

class BrandsViewModel(private val repository: BrandsRepository) : ViewModel() {

    val brandsLiveData = MediatorLiveData<ArrayList<Brand>>()
    val storesLiveData = MediatorLiveData<ArrayList<Stories>>()
    private val brands = arrayListOf<Brand>()
    private val stories = arrayListOf<Stories>()

    val indexHeaderMap = HashMap<String, Int>()
    private val requestStoriesRefine = MutableLiveData<Int>()
    val brandsStoriesRefineLiveData: LiveData<ArrayList<Designer>> =
        switchMap(requestStoriesRefine) {
            switchStoriesLiveData(it)
        }

    val error = MutableLiveData<Pair<String, Int?>>()


    private val resultLiveData =
        brandsLiveData.combineWith(storesLiveData) { branches, stories ->
            Pair(branches, stories)
        }

    val brandsRefineLiveData = switchMap(resultLiveData) {
        liveData(context = viewModelScope.coroutineContext + Dispatchers.Default) {
           val designers =  mapBrandsAndStories(it.first, it.second)
            emit(designers)
        }
    }

    val pagedList  = map(brandsRefineLiveData) { it }

    fun getBrands(page: Int? = INIT_PAGE) {
        viewModelScope.launch {
            brandsLiveData.addSource(repository.getBrandsX(page)) {
                when (it) {
                    is Result.Error -> {
                        if (page == 1) {
                            error.value = Pair(it.message, it.code)
                        }
                    }
                    is Result.Success -> {

                        if (page == 1)
                            callNextPage(it.data?.count) { nextPage -> getBrands(nextPage) }

                        launch(Dispatchers.Default) {
                            brands.addAll(brands.size, it.data?.results ?: emptyList())

                            if (it.data?.next == null)
                                brandsLiveData.postValue(brands)

                        }
                    }
                }
            }
        }
    }

    fun getStories(page: Int? = INIT_PAGE) {
        viewModelScope.launch {
            storesLiveData.addSource(repository.getStoriesX(page)) {
                when (it) {
                    is Result.Error -> {
                        if (page == 1) {
                            error.value = Pair(it.message, it.code)
                        }
                    }
                    is Result.Success -> {
                        stories.addAll(stories.size, it.data?.results ?: emptyList())
                        if (it.data?.next == null)
                            storesLiveData.value = stories
                        if (page == 1)
                            callNextPage(it.data?.count) { nextPage -> getStories(nextPage) }
                    }
                }
            }
        }
    }

    private fun callNextPage(count: Int?, nextCall: (page: Int) -> Unit) {
        val totalPage = ((count ?: 0) / LIMIT_ITEM * 1.0).roundToInt()
        (totalPage > 1).let {
            for (i in 1..totalPage) {
                nextCall.invoke(i + 1)
            }
        }
    }

    private fun switchStoriesLiveData(storiesId: Int): LiveData<ArrayList<Designer>> {
        return switchMap(brandsRefineLiveData) {
            val data = MutableLiveData<ArrayList<Designer>>()
            data.value = getStoriesRefine(it, storiesId)
            return@switchMap data
        }
    }

    private suspend fun mapBrandsAndStories(
        brands: ArrayList<Brand>?,
        stories: ArrayList<Stories>?
    ): ArrayList<Designer> = withContext(Dispatchers.Default) {
        val designers = arrayListOf<Designer>()
        Log.d("Jay main ", Thread.currentThread().name)

        runBlocking {
            val mappingBranch = async {
                brands?.map {
                    Designer(
                        brandId = it.id, storeId = it.store_id,
                        name = "${it.name} (${it.brand_name})", isChoose = false, isSection = false
                    )
                }
            }
            val mappingStory = async {
                stories?.map {
                    Designer(
                        storeId = it.id,
                        name = it.brand_name,
                        isChoose = false,
                        isSection = false
                    )
                }
            }
            designers.addAll(mappingBranch.await()?.toCollection(ArrayList()) ?: emptyList())
            designers.addAll(mappingStory.await()?.toCollection(ArrayList()) ?: emptyList())
        }

        val groupList = designers.groupBy {
            it.name?.substring(0, 1)?.deAccent()?.toLowerCase(Locale.getDefault())
        }.mapValues {
            it.value.sortedBy { designer -> designer.name }
        }.toSortedMap(kotlin.Comparator { x, t -> x!!.compareTo(t!!) })

        designers.clear()

        groupList.entries.forEach {
            val keyTitle = it.key?.toUpperCase() ?: ""
            designers.add(
                Designer(
                    isSection = true,
                    name = keyTitle,
                    isChoose = false
                )
            )
            designers.addAll(it.value)

            // calculate header postition
            indexHeaderMap[keyTitle] = designers.size - 1 - it.value.size
        }
        return@withContext designers
    }

    private fun addSection(designers: ArrayList<Designer>): ArrayList<Designer> {
        val results = arrayListOf<Designer>()
        designers.forEachIndexed { index, designer ->
            if (index == 0 && !designer.isSection) {
                results.add(
                    designer.copy(
                        isSection = true,
                        name = designer.name?.subSequence(0, 1).toString(),
                        isChoose = false
                    )
                )
            } else if (designers[index - 1].name?.equalExt(designer.name) == false) {
                results.add(
                    designer.copy(
                        isSection = true,
                        name = designer.name?.substring(0, 1).toString(),
                        isChoose = false
                    )
                )
            }
            results.add(designer)
        }
        return results
    }

    fun calculateIndexesForName(items: ArrayList<Designer>): HashMap<String, Int> {
        val mapIndex = LinkedHashMap<String, Int>()
        for (i in items.indices) {
            val designer = items[i]
            var index = designer.name?.substring(0, 1)?.deAccent() ?: KEY_EMPTY
            index = index.toUpperCase(Locale.getDefault())

            if (!mapIndex.containsKey(index)) {
                mapIndex[index] = i
            }
        }
        return mapIndex
    }

    fun requestStoriesRefine(storiesId: Int?) {
        requestStoriesRefine.value = storiesId
    }

    private fun getStoriesRefine(
        arrayList: ArrayList<Designer>,
        storiesId: Int
    ): ArrayList<Designer> {
        val results = arrayListOf<Designer>()
        arrayList.forEach {
            if (it.storeId == storiesId && !it.isSection) {
                results.add(it)
            }
        }
        return addSection(results)
    }

}


fun <T, K, R> LiveData<T>.combineWith(
    liveData: LiveData<K>,
    block: (T?, K?) -> R
): LiveData<R> {
    val result = MediatorLiveData<R>()
    result.addSource(this) {
        if (this.value != null && liveData.value != null)
            result.value = block.invoke(this.value, liveData.value)
    }
    result.addSource(liveData) {
        if (this.value != null && liveData.value != null)
            result.value = block.invoke(this.value, liveData.value)
    }
    return result
}