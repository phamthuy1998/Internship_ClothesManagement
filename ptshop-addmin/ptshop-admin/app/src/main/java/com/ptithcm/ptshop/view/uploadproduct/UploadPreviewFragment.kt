package com.ptithcm.ptshop.view.uploadproduct

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility
import com.ptithcm.core.BuildConfig
import com.ptithcm.core.model.Product
import com.ptithcm.core.model.Tag
import com.ptithcm.core.model.UploadFile
import com.ptithcm.core.param.ProductPhotoUrl
import com.ptithcm.core.param.UploadProductParam
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.base.BaseActivity
import com.ptithcm.ptshop.base.BaseFragment
import com.ptithcm.ptshop.constant.EMPTY_STRING
import com.ptithcm.ptshop.constant.ERROR_CODE_404
import com.ptithcm.ptshop.constant.PESDK_REQUEST
import com.ptithcm.ptshop.constant.UPLOAD_PRODUCT_STORAGE
import com.ptithcm.ptshop.databinding.FragmentUploadProductPreviewBinding
import com.ptithcm.ptshop.databinding.LayoutBottomSheetBinding
import com.ptithcm.ptshop.databinding.LayoutTagBinding
import com.ptithcm.ptshop.ext.*
import com.ptithcm.ptshop.util.PopUp
import com.ptithcm.ptshop.view.MainActivity
import com.ptithcm.ptshop.view.uploadproduct.adapter.PreviewImageAdapter
import com.ptithcm.ptshop.view.uploadproduct.adapter.ProductAdapter
import com.ptithcm.ptshop.viewmodel.ShareDataViewModel
import com.ptithcm.ptshop.viewmodel.UploadViewModel
import kotlinx.android.synthetic.main.layout_toolbar_upload_product.view.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class UploadPreviewFragment : BaseFragment<FragmentUploadProductPreviewBinding>(),
    View.OnClickListener {
    override val layoutId: Int
        get() = R.layout.fragment_upload_product_preview

    val description = ObservableField("")
    val title = ObservableField("")
    private lateinit var previewImageAdapter: PreviewImageAdapter
    private lateinit var productAdapter: ProductAdapter
    private val transferUtility: TransferUtility by inject()
    private val shareDataViewModel: ShareDataViewModel by sharedViewModel(from = { requireActivity() })
    private val uploadViewModel by viewModel<UploadViewModel>()
    private var currentSelectedFiles: ArrayList<UploadFile>? = null
    private var currentSelectedProducts: ArrayList<Product>? = null
    private var currentSelectedTags: ArrayList<Tag>? = null
    private var editFile: UploadFile? = null
    val listUrl = arrayListOf<ProductPhotoUrl>()
    private var isError = false
    private var completeCounter = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        initAdapter()
    }

    private fun setupToolbar() {
        (requireActivity() as? UploadProductActivity)?.apply {
            initToolBar(
                viewBinding.layoutToolbar.toolbar,
                false,
                hasBackRight = false,
                hasTextLeft = true
            )
            setupToolbar(viewBinding.layoutToolbar.toolbar, getString(R.string.upload))
            viewBinding.layoutToolbar.toolbar.tvTitleToolbar.textSize = 16f
        }
    }

    override fun bindViewModel() {
        super.bindViewModel()
        uploadViewModel.uploadLiveData.observe(this, Observer {
            viewBinding.tvSuccess.visible()
            Handler().postDelayed({
                activity?.finish()
            }, 2000)
        })
        uploadViewModel.error.observe(this, Observer {
            if (it.second == ERROR_CODE_404) {
                (requireActivity() as? MainActivity)?.isShowErrorNetwork(true)
            } else {
                completeCounter = 0
                viewBinding.vUploading.gone()
                viewBinding.tvUpload.text = getString(R.string.upload)
                (activity as? UploadProductActivity)?.showError(it.first)
            }
        })

        uploadViewModel.networkState.observe(this, Observer {
        })

        shareDataViewModel.currentSelectedFile.observe(this, Observer {
            currentSelectedFiles = it
            if (!it.isNullOrEmpty() && it.first().isVideo == true) {
                viewBinding.isVideo = true
                viewBinding.videoView.apply {
                    setVideoPath(if (it.first().trimFile == null) it.first().file?.path else it.first().trimFile?.path)
                    seekTo(1)
                    setMediaController(null)
                    requestFocus()
                }
            } else {
                viewBinding.isVideo = false
                previewImageAdapter.setList(it)
            }
        })

        shareDataViewModel.currentSelectedProducts.observe(this, Observer {
            when (it?.size) {
                10 -> {
                    viewBinding.tvAddProduct.gone()
                    viewBinding.rcvProduct.visible()
                }
                0 -> {
                    viewBinding.tvAddProduct.visible()
                    viewBinding.rcvProduct.gone()
                }
                else -> {
                    viewBinding.tvAddProduct.visible()
                    viewBinding.rcvProduct.visible()
                }
            }
            productAdapter.setList(it)
            currentSelectedProducts = it
        })

        shareDataViewModel.currentSelectedTags.observe(this, Observer {
            updateUITag(it)
            currentSelectedTags = it
        })

        shareDataViewModel.titleLiveData.observe(this, Observer {
            viewBinding.edtTitle.setText(it)
        })

        shareDataViewModel.descriptionLiveData.observe(this, Observer {
            viewBinding.edtDescription.setText(it)
        })
    }

    override fun onPause() {
        super.onPause()
        shareDataViewModel.saveTitle(title.get() ?: EMPTY_STRING)
        shareDataViewModel.saveDescription(description.get() ?: EMPTY_STRING)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun bindEvent() {
        super.bindEvent()
        viewBinding.fragment = this
        viewBinding.videoView.setOnCompletionListener { viewBinding.ivPlayVideo.visible() }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tvAddProduct -> navController.navigate(R.id.action_add_product)
            R.id.tvAddTag, R.id.ctlTags -> navController.navigate(R.id.action_add_tag)
            R.id.tvUpload -> onClickUpload()
            R.id.tvRemoveVideo -> {
                shareDataViewModel.requestSelectedImage(arrayListOf())
                navController.popBackStack(R.id.fragmentUploadProduct, false)
            }
            R.id.ivPlayVideo -> {
                viewBinding.videoView.start()
                viewBinding.ivPlayVideo.gone()
            }
        }
    }

    private fun initAdapter() {
        previewImageAdapter = PreviewImageAdapter(
            arrayListOf(),
            this::onEditImage,
            this::onDeleteImage,
            this::onAddImage
        )
        viewBinding.rcvSelectedImage.adapter = previewImageAdapter

        productAdapter = ProductAdapter(arrayListOf(), this::onRemoveProduct)
        viewBinding.rcvProduct.adapter = productAdapter
    }

    fun lengthRemain(maxLength: Int, text: String?): String {
        return (maxLength - (text?.length ?: 0)).toString()
    }

    private fun onEditImage(file: UploadFile?) {
        editFile = file
//        val settingsList = createPesdkSettingsList().apply {
//            getSettingsModel(LoadSettings::class.java).apply {
//                source = if (file?.editFile == null) file?.file?.toUri() else file.editFile?.toUri()
//            }
//        }
//
//        PhotoEditorBuilder(requireActivity())
//            .setSettingsList(settingsList)
//            .startActivityForResult(this, PESDK_REQUEST)
    }

    private fun onDeleteImage(file: UploadFile?) {
        (requireActivity() as? BaseActivity<*>)?.showPopup(
            PopUp(
                R.layout.layout_bottom_sheet,
                isBottomSheet = true,
                isCancelable = true,
                messageQueue = { binding: ViewDataBinding? ->
                    (binding as? LayoutBottomSheetBinding)?.apply {
                        delete = getString(R.string.delete_image)
                        this.tvCancel.setOnClickListener {
                            (requireActivity() as? BaseActivity<*>)?.closePopup()
                        }
                        this.tvDelete.setOnClickListener {
                            (requireActivity() as? BaseActivity<*>)?.closePopup()
                            currentSelectedFiles?.removeAll { it.file?.path == file?.file?.path }
                            shareDataViewModel.requestSelectedImage(
                                currentSelectedFiles ?: arrayListOf()
                            )
                        }
                    }
                }
            )
        )
    }

    private fun onAddImage() {
        navController.popBackStack()
    }

    private fun onRemoveProduct(itemRemove: Product?) {
        currentSelectedProducts?.map { product ->
            if (product.obserCounter.get() ?: 0 > itemRemove?.obserCounter?.get() ?: 0) {
                product.obserCounter.set(product.obserCounter.get()?.minus(1))
            }
        }
        currentSelectedProducts?.removeAll { it.id == itemRemove?.id }
        shareDataViewModel.requestSelectProduct(currentSelectedProducts ?: arrayListOf())
    }

    private fun updateUITag(listTags: List<Tag>?) {
        if (listTags.isNullOrEmpty()) {
            viewBinding.tvAddTag.visible()
            viewBinding.ctlTags.gone()
        } else {
            viewBinding.fbTags.removeAllViewsInLayout()
            listTags.forEach {
                val binding = DataBindingUtil.inflate<LayoutTagBinding>(
                    LayoutInflater.from(context),
                    R.layout.layout_tag,
                    null,
                    false
                )
                binding.item = it
                binding.root.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(0, 0, 4, 4)
                }
                viewBinding.fbTags.addView(binding.root)
            }
            viewBinding.tvAddTag.gone()
            viewBinding.ctlTags.visible()
        }
    }

    private fun uploadFileToS3(uploadFile: UploadFile) {

        val finalFile = when {
            uploadFile.isVideo == true && uploadFile.trimFile != null -> uploadFile.trimFile
            uploadFile.isVideo != true && uploadFile.editFile != null -> uploadFile.editFile
            else -> uploadFile.file
        }

        val photoStoragePath = "$UPLOAD_PRODUCT_STORAGE${System.currentTimeMillis()}.jpg"
        listUrl.add(ProductPhotoUrl(photoStoragePath))
        val transferObserver = transferUtility.upload(
            BuildConfig.AWS_BUCKET,
            photoStoragePath,
            finalFile
        )
        transferObserverListener(transferObserver)
        if (uploadFile.isVideo == true) {
            val videoStoragePath = photoStoragePath.replace("jpg", "mov")
            listUrl.add(ProductPhotoUrl(videoStoragePath))
            val transferObserverVideo = transferUtility.upload(
                BuildConfig.AWS_BUCKET,
                videoStoragePath,
                finalFile
            )
            transferObserverListener(transferObserverVideo)
        }
    }

    private fun onClickUpload() {
        when {
            viewBinding.edtTitle.text.toString().trim().isEmpty() -> {
                (activity as? UploadProductActivity)?.showError("Please enter story title.")
            }
            currentSelectedProducts.isNullOrEmpty() -> {
                (activity as? UploadProductActivity)?.showError("Please pick products to be featured in the story.")
            }
            currentSelectedFiles.isNullOrEmpty() -> {
                (activity as? UploadProductActivity)?.showError("Please pick images that feature the products in the story")
            }
            else -> {
                currentSelectedFiles?.forEach { uploadFile ->
                    uploadFileToS3(uploadFile)
                }
                viewBinding.vUploading.visible()
                viewBinding.tvUpload.text = getString(R.string.uploadings, "0%")
            }
        }
    }

    private fun transferObserverListener(transferObserver: TransferObserver) {
        transferObserver.setTransferListener(object : TransferListener {
            override fun onStateChanged(id: Int, state: TransferState) {
                if (state == TransferState.COMPLETED) {
                    completeCounter++
                    if ((currentSelectedFiles?.first()?.isVideo == true && completeCounter == 2) ||
                        currentSelectedFiles?.first()?.isVideo == false && completeCounter == currentSelectedFiles?.size
                    ) {
                        uploadViewModel.uploadProduct(
                            UploadProductParam(
                                title = title.get()?.trim(),
                                products = currentSelectedProducts?.map { it.id },
                                description = if (description.get()?.isEmpty() == true) null else description.get(),
                                uploads = listUrl,
                                tags = currentSelectedTags?.map { it.id ?: 0 }
                            )
                        )
                    }
                }
            }

            override fun onProgressChanged(id: Int, bytesCurrent: Long, bytesTotal: Long) {
                if (!isError && bytesTotal > 0) {
                    val percent = bytesCurrent / bytesTotal * 100
                    viewBinding.tvUpload.text = getString(R.string.uploadings, "$percent%")
                }
            }

            override fun onError(id: Int, ex: Exception) {
                ex.printStackTrace()
                isError = true
                completeCounter = 0
                if (isAdded) {
                    viewBinding.vUploading.gone()
                    viewBinding.tvUpload.text = getString(R.string.upload)
                    (activity as? UploadProductActivity)?.showError(ex.message ?: "")
                }
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == PESDK_REQUEST) { // Editor has saved an Image.

//            val resultURI = data?.getParcelableExtra<Uri?>(ImgLyIntent.RESULT_IMAGE_URI)
//            currentSelectedFiles?.find { it.file?.path == editFile?.file?.path }.apply {
//                this?.editFile = File(resultURI?.path ?: "")
//            }
//            previewImageAdapter.setList(currentSelectedFiles)
        }
    }

//    private fun createPesdkSettingsList() = PhotoEditorSettingsList().apply {
//
//        getSettingsModel(UiConfigFilter::class.java).apply {
//            setFilterList(FilterPackBasic.getFilterPack())
//        }
//
//        getSettingsModel(UiConfigText::class.java).apply {
//            setFontList(FontPackBasic.getFontPack())
//        }
//
//        getSettingsModel(UiConfigFrame::class.java).apply {
//            setFrameList(FramePackBasic.getFramePack())
//        }
//
//        getSettingsModel(UiConfigOverlay::class.java).apply {
//            setOverlayList(OverlayPackBasic.getOverlayPack())
//        }
//
//        getSettingsModel(UiConfigSticker::class.java).apply {
//            setStickerLists(
//                StickerPackEmoticons.getStickerCategory(),
//                StickerPackShapes.getStickerCategory()
//            )
//        }
//
//        // Set custom editor image export settings
//        getSettingsModel(PhotoEditorSaveSettings::class.java).apply {
//            setExportDir(Directory.DCIM, "SnapShop")
//            setExportPrefix("result_")
//            savePolicy = PhotoEditorSaveSettings.SavePolicy.RETURN_ALWAYS_ONLY_OUTPUT
//        }
//    }
}