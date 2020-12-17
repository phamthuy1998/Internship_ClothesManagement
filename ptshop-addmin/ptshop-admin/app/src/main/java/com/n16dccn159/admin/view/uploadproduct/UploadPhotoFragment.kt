package com.n16dccn159.admin.view.uploadproduct

import android.graphics.Matrix
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Size
import android.view.Surface
import android.view.View
import android.view.ViewGroup
import androidx.camera.core.*
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.n16dccn159.admin.R
import com.n16dccn159.admin.base.BaseFragment
import com.n16dccn159.admin.databinding.FragmentUploadProductPhotoBinding
import com.n16dccn159.admin.util.FileUtil
import com.n16dccn159.admin.viewmodel.ShareDataViewModel
import com.n16dccn159.core.model.UploadFile
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.support.v4.toast
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.io.File
import java.util.concurrent.Executors

class UploadPhotoFragment : BaseFragment<FragmentUploadProductPhotoBinding>(),
    View.OnClickListener {
    override val layoutId: Int
        get() = R.layout.fragment_upload_product_photo
    private val shareDataViewModel: ShareDataViewModel by sharedViewModel(from = { requireActivity() })
    private var selectedFileList: ArrayList<UploadFile>? = null
    private var lensFacing = CameraX.LensFacing.BACK
    private val executor = Executors.newSingleThreadExecutor()
    private var imageCapture: ImageCapture? = null
    private var capturedFile: UploadFile? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectedFileList = arrayListOf()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.fragment = this
        response()
        viewBinding.textureView.addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
            updateTransform()
        }
    }

    override fun onResume() {
        super.onResume()
        viewBinding.textureView.post { bindCamera() }
    }

    override fun onPause() {
        super.onPause()
        CameraX.unbindAll()
    }

    private fun response() {
        shareDataViewModel.currentSelectedFile.observe(this, Observer {
            selectedFileList = it
        })
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnTakePhoto -> {
                val dest =
                    File(context?.externalMediaDirs?.first(), "${System.currentTimeMillis()}.jpg")
                imageCapture?.takePicture(dest, executor, object : ImageCapture.OnImageSavedListener {
                        override fun onImageSaved(file: File) {
                            capturedFile = UploadFile(file)
                            viewBinding.isCaptured = true
                            viewBinding.textureView.post {
                                Glide.with(requireContext())
                                    .load(file.path)
                                    .into(viewBinding.ivResult)
                            }
                        }

                        override fun onError(
                            imageCaptureError: ImageCapture.ImageCaptureError,
                            message: String,
                            cause: Throwable?
                        ) {
                            viewBinding.textureView.post { toast("Error") }
                        }
                    })
            }
            R.id.btnRetake -> {
                FileUtil().deleteImageFromDisk(capturedFile?.file?.path ?: "")
                viewBinding.isCaptured = false
            }
            R.id.btnUpload -> {
                capturedFile?.let {
                    selectedFileList?.add(it)
                }
                shareDataViewModel.requestSelectedImage(selectedFileList ?: arrayListOf())
                navController.navigate(R.id.action_upload_preview)
            }
            R.id.btnRotateCamera -> {
                lensFacing = if (CameraX.LensFacing.FRONT == lensFacing) {
                    viewBinding.isFront = false
                    CameraX.LensFacing.BACK
                } else {
                    viewBinding.isFront = true
                    CameraX.LensFacing.FRONT
                }
                bindCamera()
            }
            R.id.btnToggleSplash -> {
                when (imageCapture?.flashMode){
                    FlashMode.AUTO -> {
                        imageCapture?.flashMode = FlashMode.OFF
                        viewBinding.btnToggleSplash.imageResource = R.drawable.ic_flash_off
                    }
                    FlashMode.OFF -> {
                        imageCapture?.flashMode = FlashMode.ON
                        viewBinding.btnToggleSplash.imageResource = R.drawable.ic_flash_on
                    }
                    FlashMode.ON -> {
                        imageCapture?.flashMode = FlashMode.AUTO
                        viewBinding.btnToggleSplash.imageResource = R.drawable.ic_flash_auto
                    }
                }
            }
        }
    }

    private fun bindCamera() {
        CameraX.unbindAll()
        // Preview config for the camera
        val metrics = DisplayMetrics().also { viewBinding.textureView.display.getRealMetrics(it) }
        val screenSize = Size(metrics.widthPixels, metrics.heightPixels)

        val previewConfig = PreviewConfig.Builder().apply {
            setLensFacing(lensFacing)
//            setTargetAspectRatio(screenAspectRatio)
            setTargetResolution(screenSize)
            setTargetRotation(viewBinding.textureView.display.rotation)
        }.build()

        val preview = Preview(previewConfig)

        // Handles the output data of the camera
        preview.setOnPreviewOutputUpdateListener { previewOutput ->
            // Displays the camera image in our preview view
            val parent = viewBinding.textureView.parent as ViewGroup
            parent.removeView(viewBinding.textureView)
            viewBinding.textureView.surfaceTexture = previewOutput.surfaceTexture
            parent.addView(viewBinding.textureView, 0)
            updateTransform()
        }

        // Image capture config which controls the Flash and Lens
        val imageCaptureConfig = ImageCaptureConfig.Builder()
            .setLensFacing(lensFacing)
            .setCaptureMode(ImageCapture.CaptureMode.MAX_QUALITY)
            .setTargetRotation(viewBinding.textureView.display.rotation)
            .build()

        imageCapture = ImageCapture(imageCaptureConfig)

        // Bind the camera to the lifecycle
        CameraX.bindToLifecycle(this as LifecycleOwner, imageCapture, preview)
    }

    private fun updateTransform() {
        val matrix = Matrix()
        val centerX = viewBinding.textureView.width / 2f
        val centerY = viewBinding.textureView.height / 2f

        val rotationDegrees = when (viewBinding.textureView.display.rotation) {
            Surface.ROTATION_0 -> 0
            Surface.ROTATION_90 -> 90
            Surface.ROTATION_180 -> 180
            Surface.ROTATION_270 -> 270
            else -> return
        }
        matrix.postRotate(-rotationDegrees.toFloat(), centerX, centerY)
        viewBinding.textureView.setTransform(matrix)
    }

}