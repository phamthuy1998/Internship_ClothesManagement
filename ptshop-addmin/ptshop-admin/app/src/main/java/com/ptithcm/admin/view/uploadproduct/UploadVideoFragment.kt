package com.ptithcm.admin.view.uploadproduct

import android.annotation.SuppressLint
import android.graphics.Matrix
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.DisplayMetrics
import android.util.Size
import android.view.Surface
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.camera.core.*
import androidx.core.os.bundleOf
import com.ptithcm.admin.R
import com.ptithcm.admin.base.BaseFragment
import com.ptithcm.admin.constant.KEY_FILE
import com.ptithcm.admin.constant.PERMISSION_AUDIO
import com.ptithcm.admin.databinding.FragmentUploadProductVideoBinding
import com.ptithcm.admin.ext.*
import org.jetbrains.anko.support.v4.toast
import java.io.File
import java.util.concurrent.Executors

@SuppressLint("RestrictedApi")
class UploadVideoFragment : BaseFragment<FragmentUploadProductVideoBinding>(),
    View.OnClickListener {
    override val layoutId: Int
        get() = R.layout.fragment_upload_product_video

    private var isRecoding = false
    private var timer60s: CountDownTimer? = null
    private var timer = 0

    private var lensFacing = CameraX.LensFacing.BACK
    private val executor = Executors.newSingleThreadExecutor()
    private var videoCapture: VideoCapture? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.textureView.addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
            updateTransform()
        }
    }

    override fun bindViewModel() {
        super.bindViewModel()
        timer60s = object : CountDownTimer(60000, 1000) {
            override fun onFinish() {
                stopRecordVideo()
            }

            override fun onTick(millisUntilFinished: Long) {
                timer++
                viewBinding.tvTime.text = if (timer < 10) "00:0$timer" else "00:$timer"
            }
        }
    }

    override fun bindEvent() {
        super.bindEvent()
        viewBinding.btnRotateCamera.setOnClickListener(this)
        viewBinding.btnCaptureVideo.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnCaptureVideo -> {
                if (activity?.hasAudioPermission() == false) {
                    activity?.requestAudioPermission(PERMISSION_AUDIO)
                } else {
                    timer60s?.cancel()
                    timer = 0
                    when (isRecoding) {
                        false -> {
                            recordVideo()
                        }
                        true -> {
                            stopRecordVideo()
                        }
                    }
                }
            }
            R.id.btnRotateCamera -> {
                lensFacing = if (CameraX.LensFacing.FRONT == lensFacing) {
                    CameraX.LensFacing.BACK
                } else {
                    CameraX.LensFacing.FRONT
                }
                startCamera()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewBinding.textureView.post { startCamera() }
    }

    override fun onPause() {
        super.onPause()
        CameraX.unbindAll()
        stopRecordVideo()
        timer60s?.cancel()
        timer = 0
    }

    private fun startCamera() {
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
        preview.setOnPreviewOutputUpdateListener {
            val parent = viewBinding.textureView.parent as ViewGroup
            parent.removeView(viewBinding.textureView)
            viewBinding.textureView.surfaceTexture = it.surfaceTexture
            parent.addView(viewBinding.textureView, 0)
            updateTransform()
        }

        val videoCaptureConfig = VideoCaptureConfig.Builder().apply {
            setLensFacing(lensFacing)
            setTargetRotation(viewBinding.textureView.display.rotation)
        }.build()

        // Build the image capture use case and attach button click listener
        videoCapture = VideoCapture(videoCaptureConfig)

        CameraX.bindToLifecycle(this, preview, videoCapture)
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

    private fun recordVideo() {
        (requireActivity() as? UploadProductActivity)?.viewBinding?.layoutToolbar?.tvCancelToolbar?.disable()
        viewBinding.btnRotateCamera.disable()
        isRecoding = true
        viewBinding.btnCaptureVideo.disable()
        (parentFragment as? UploadProductFragment)?.viewBinding?.viewPager?.isDisable(true)
        disableTabLayout(true)
        val dest = File(
            context?.externalMediaDirs?.first(), "captured_video.mp4"
        )

        videoCapture?.startRecording(dest, executor, object : VideoCapture.OnVideoSavedListener {
            override fun onVideoSaved(file: File) {
                navController.navigate(R.id.action_trim_video, bundleOf(KEY_FILE to file))
            }

            override fun onError(
                videoCaptureError: VideoCapture.VideoCaptureError,
                message: String,
                cause: Throwable?
            ) {
                viewBinding.textureView.post { toast("Error") }
            }
        })

        Handler().postDelayed({
            timer60s?.start()
            viewBinding.tvTime.visible()
            viewBinding.btnCaptureVideo.enable()
        }, 1000)
    }

    private fun stopRecordVideo() {
        if (isRecoding) {
            (requireActivity() as? UploadProductActivity)?.viewBinding?.layoutToolbar?.tvCancelToolbar?.enable()
            viewBinding.btnRotateCamera.enable()
            (parentFragment as? UploadProductFragment)?.viewBinding?.viewPager?.isDisable(false)
            disableTabLayout(false)
            videoCapture?.stopRecording()
            isRecoding = false
            viewBinding.tvTime.gone()
        }
    }

    private fun disableTabLayout(isDisable: Boolean) {
        val tabChild =
            (parentFragment as? UploadProductFragment)?.viewBinding?.tabLayout?.getChildAt(0) as LinearLayout
        val count =
            (parentFragment as? UploadProductFragment)?.viewBinding?.viewPager?.childCount ?: 0
        for (i in 0..count) {
            tabChild.getChildAt(i)?.setOnTouchListener { _, _ ->
                isDisable
            }
        }
    }
}