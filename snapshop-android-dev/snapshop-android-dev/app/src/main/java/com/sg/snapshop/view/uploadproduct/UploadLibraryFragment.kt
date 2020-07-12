package com.sg.snapshop.view.uploadproduct

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.sg.core.model.UploadFile
import com.sg.snapshop.R
import com.sg.snapshop.base.BaseFragment
import com.sg.snapshop.databinding.FragmentUploadProductLibraryBinding
import com.sg.snapshop.ext.disable
import com.sg.snapshop.ext.enable
import com.sg.snapshop.view.uploadproduct.adapter.LibraryAdapter
import com.sg.snapshop.viewmodel.ShareDataViewModel
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class UploadLibraryFragment : BaseFragment<FragmentUploadProductLibraryBinding>(),
    View.OnClickListener {
    override val layoutId: Int
        get() = R.layout.fragment_upload_product_library

    private lateinit var libraryAdapter: LibraryAdapter

    private var selectedFileList: ArrayList<UploadFile>? = null
    private var countImage: Int = 0

    private val shareDataViewModel: ShareDataViewModel by sharedViewModel(from = { requireActivity() })

    companion object {
        private val fragment = UploadLibraryFragment()

        fun newInstance(): UploadLibraryFragment = fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        countImage = 0
        selectedFileList = arrayListOf()
        loadImagesFromGallery()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        response()
    }

    override fun bindEvent() {
        super.bindEvent()
        viewBinding.tvUploadSelected.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tvUploadSelected -> {
                navController.navigate(if (selectedFileList?.get(0)?.isVideo == true) R.id.action_trim_video else R.id.action_upload_preview)
            }
        }
    }

    private fun loadImagesFromGallery() {
        if (shareDataViewModel.loadedImage.value == null || shareDataViewModel.loadedImage.value?.size == 0) {
            shareDataViewModel.requestImages(requireContext())
        }

    }

    private fun init() {
        libraryAdapter = LibraryAdapter(arrayListOf(), this::onImageSelected)
        viewBinding.rcvLibrary.adapter = libraryAdapter
    }

    private fun response() {
        shareDataViewModel.loadedImage.observe(this, Observer {
            it?.let {
                onUpdateSelectedImage(it)
            }
        })

        shareDataViewModel.currentSelectedFile.observe(this, Observer { listFiles ->
            countImage = listFiles.size
            selectedFileList = listFiles
            when {
                listFiles.size > 1 && listFiles.any { it.isVideo == true } -> {
                    (activity as? UploadProductActivity)?.showError(getString(R.string.err_select_library))
                    viewBinding.tvUploadSelected.disable()
                    viewBinding.tvUploadSelected.alpha = 0.5f
                }
                countImage > 3 -> {
                    (activity as? UploadProductActivity)?.showError(getString(R.string.error_more_than_3_images))
                    viewBinding.tvUploadSelected.disable()
                    viewBinding.tvUploadSelected.alpha = 0.5f
                }
                countImage == 0 -> {
                    viewBinding.tvUploadSelected.disable()
                    viewBinding.tvUploadSelected.alpha = 0.5f
                }
                else -> {
                    viewBinding.tvUploadSelected.enable()
                    viewBinding.tvUploadSelected.alpha = 1f
                }
            }

        })
    }

    private fun onUpdateSelectedImage(fileList: ArrayList<UploadFile>) {
        fileList.forEach {
            it.obserIsSelected.set(false)
            it.canSelect = true
        }
        selectedFileList?.forEach { itemImage ->
            fileList.find {
                itemImage.file?.path?.equals(it.file?.path) == true
            }?.apply {
                this.obserIsSelected.set(true)
                this.canSelect = false
            }
        }
        libraryAdapter.setList(fileList)
    }

    private fun onImageSelected(file: UploadFile?) {
        if (file?.canSelect == true) {
            when (file.obserIsSelected.get()) {
                true -> {
                    file.obserIsSelected.set(false)
                    removeImage(selectedFileList, file)
                }
                false -> {
                    file.obserIsSelected.set(true)
                    selectedFileList?.add(file)
                    shareDataViewModel.requestSelectedImage(selectedFileList ?: arrayListOf())
                }
            }
        }
    }

    private fun removeImage(currentSelectedList: ArrayList<UploadFile>?, itemRemove: UploadFile) {
        var position = -1
        doAsync {
            currentSelectedList?.apply {
                forEachIndexed { index, image ->
                    if (image.file?.path?.equals(itemRemove.file?.path) == true) {
                        position = index
                        return@forEachIndexed
                    }
                }
                currentSelectedList.removeAt(position)
            }

            uiThread {
                shareDataViewModel.requestSelectedImage(currentSelectedList ?: arrayListOf())
            }
        }
    }
}
