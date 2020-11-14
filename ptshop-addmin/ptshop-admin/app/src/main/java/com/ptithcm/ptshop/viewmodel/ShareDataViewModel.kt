package com.ptithcm.ptshop.viewmodel

import android.content.Context
import android.provider.MediaStore
import androidx.lifecycle.*
import com.ptithcm.core.model.Product
import com.ptithcm.core.model.Tag
import com.ptithcm.core.model.UploadFile
import com.ptithcm.core.repository.ShareDataRepository
import com.ptithcm.core.vo.Result
import com.ptithcm.ptshop.constant.EMPTY_STRING
import kotlinx.coroutines.launch
import java.io.File

class ShareDataViewModel(private val context: Context, private val repo: ShareDataRepository) : ViewModel() {

    val tagsLiveData = MediatorLiveData<ArrayList<Tag>>()
    val error = MutableLiveData<Pair<String, Int?>>()
    val networkState = MutableLiveData<Boolean>()

    private var requestImages = MutableLiveData<Context>()
    private var requestSelectedImage = MutableLiveData<ArrayList<UploadFile>>()
    private var requestSelectProduct = MutableLiveData<ArrayList<Product>>()
    private var requestSelectTag = MutableLiveData<ArrayList<Tag>>()
    var titleLiveData = MutableLiveData<String>(EMPTY_STRING)
    var descriptionLiveData = MutableLiveData<String>(EMPTY_STRING)

    var loadedImage = Transformations.switchMap(requestImages) {
        loadMediaStore()
    }

    val currentSelectedFile: LiveData<ArrayList<UploadFile>> = Transformations.switchMap(requestSelectedImage) {
        val data = MutableLiveData<ArrayList<UploadFile>>()
        data.value = it
        data
    }

    val currentSelectedProducts = Transformations.switchMap(requestSelectProduct) {
        val data = MutableLiveData<ArrayList<Product>>()
        data.value = it
        data
    }

    val currentSelectedTags = Transformations.switchMap(requestSelectTag){
        MutableLiveData<ArrayList<Tag>>().apply {
            value = it
        }
    }

    fun saveTitle(title: String){
        titleLiveData.value = title
    }

    fun saveDescription(description: String){
        descriptionLiveData.value = description
    }

    fun requestImages(context: Context) {
        requestImages.value = context
    }

    fun requestSelectedImage(fileList: ArrayList<UploadFile>) {
        requestSelectedImage.value = fileList
    }

    fun requestSelectProduct(listProduct: ArrayList<Product>){
        requestSelectProduct.value = listProduct
    }

    fun requestSelectTag(listTags: ArrayList<Tag>){
        requestSelectTag.value = listTags
    }

    private fun loadMediaStore(): LiveData<ArrayList<UploadFile>> {
        val data = MutableLiveData<ArrayList<UploadFile>>()
        val listOfAllImages = ArrayList<UploadFile>()
        val projection = arrayOf(
            MediaStore.Files.FileColumns._ID,
            MediaStore.Files.FileColumns.DATE_ADDED,
            MediaStore.Files.FileColumns.DATA,
            MediaStore.Files.FileColumns.MEDIA_TYPE
        )
        val selection = (MediaStore.Files.FileColumns.MEDIA_TYPE + "="
                + MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE
                + " OR "
                + MediaStore.Files.FileColumns.MEDIA_TYPE + "="
                + MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO)
        val queryUri = MediaStore.Files.getContentUri("external")
        val cursor = context.contentResolver?.query(
            queryUri,
            projection,
            selection,
            null,
            MediaStore.Files.FileColumns.DATE_ADDED + " DESC"
        )
        cursor?.let {
            val columnType = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.MEDIA_TYPE)
            val columnIndexData = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA)
//            val columnIndexID = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID)
            while (cursor.moveToNext()) {
                val imageData = cursor.getString(columnIndexData)
                val isVideo = cursor.getInt(columnType) == MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO
//                val uriImage = Uri.withAppendedPath(queryUri, "" + cursor.getLong(columnIndexID))
                listOfAllImages.add(UploadFile(File(imageData), isVideo = isVideo))
            }
        }
        cursor?.close()
        return data.apply { value = listOfAllImages }
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
}