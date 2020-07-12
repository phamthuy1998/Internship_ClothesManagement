package com.sg.snapshop.view.uploadproduct

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.innovattic.rangeseekbar.RangeSeekBar
import com.sg.core.model.UploadFile
import com.sg.snapshop.R
import com.sg.snapshop.base.BaseFragment
import com.sg.snapshop.constant.KEY_FILE
import com.sg.snapshop.databinding.FragmentTrimVideoBinding
import com.sg.snapshop.ext.*
import com.sg.snapshop.util.FileUtil
import com.sg.snapshop.viewmodel.ShareDataViewModel
import kotlinx.android.synthetic.main.layout_toolbar_upload_product.view.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.io.File


class TrimVideoFragment : BaseFragment<FragmentTrimVideoBinding>(), View.OnClickListener,
    RangeSeekBar.SeekBarChangeListener {

    override val layoutId: Int
        get() = R.layout.fragment_trim_video
    private val shareDataViewModel: ShareDataViewModel by sharedViewModel(from = { requireActivity() })
    private var selectedFileList: ArrayList<UploadFile>? = null
    private var videoFile: File? = null
    private var videoDuration = 0L
    private var startMilliseconds = 0L
    private var endMilliseconds = 0L
    private var minValue = 0
    private var maxValue = 100

    private fun setupToolbar() {
        (requireActivity() as? UploadProductActivity)?.apply {
            initToolBar(viewBinding.layoutToolbar.toolbar, true, hasBackRight = false)
            setupToolbar(viewBinding.layoutToolbar.toolbar, getString(R.string.upload))
            viewBinding.layoutToolbar.toolbar.ivBack.setOnClickListener {
                if (arguments?.getSerializable(KEY_FILE) == null)
                    shareDataViewModel.requestSelectedImage(arrayListOf())
                navController.popBackStack()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        videoFile = arguments?.getSerializable(KEY_FILE) as? File
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        videoFile?.let {
            viewBinding.videoView.apply {
                videoDuration = FileUtil().getFileDuration(it)
                endMilliseconds = videoDuration
                viewBinding.tvEndTime.text = videoDuration.convertMillieToHMmSs()
                setVideoPath(videoFile?.path)
                seekTo(1)
                setMediaController(null)
                requestFocus()
            }
        }
    }

    override fun bindViewModel() {
        super.bindViewModel()
        shareDataViewModel.currentSelectedFile.observe(this, Observer {
            selectedFileList = it
            if (videoFile == null && !it.isNullOrEmpty()) {
                viewBinding.videoView.apply {
                    videoFile = it.first().file
                    videoDuration = FileUtil().getFileDuration(it.first().file)
                    endMilliseconds = videoDuration
                    viewBinding.tvEndTime.text = videoDuration.convertMillieToHMmSs()
                    setVideoPath(it.first().file?.path)
                    seekTo(1)
                    setMediaController(null)
                    requestFocus()
                }
            }
        })
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun bindEvent() {
        super.bindEvent()
        viewBinding.vUploading.setOnClickListener(this)
        viewBinding.ivPlayVideo.setOnClickListener(this)
        viewBinding.tvConfirmVideo.setOnClickListener(this)
        viewBinding.videoView.setOnCompletionListener { viewBinding.ivPlayVideo.visible() }
        viewBinding.rsbTrimVideo.seekBarChangeListener = this
        viewBinding.videoView.setOnTouchListener { _, _ ->
            viewBinding.videoView.seekTo(startMilliseconds.toInt())
            viewBinding.videoView.start()
            viewBinding.ivPlayVideo.gone()
            true
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ivPlayVideo -> {
                viewBinding.videoView.seekTo(startMilliseconds.toInt())
                viewBinding.videoView.start()
                viewBinding.ivPlayVideo.gone()
            }
            R.id.tvConfirmVideo -> {
                if (startMilliseconds != 0L || endMilliseconds != videoDuration) {
                    viewBinding.vUploading.visible()
                    val srcPath = videoFile?.path ?: ""
                    val destFile = File(context?.externalMediaDirs?.first(), "trim_video.mp4")
                    FileUtil().genVideoUsingMuxer(srcPath, destFile.path, startMilliseconds.toInt(), endMilliseconds.toInt())
                    videoFile?.let {
                        shareDataViewModel.requestSelectedImage(
                            arrayListOf(
                                UploadFile(
                                    file = videoFile,
                                    trimFile = destFile,
                                    isVideo = true
                                )
                            )
                        )
                    }
                    navController.navigate(R.id.action_upload_preview)
                } else {
                    videoFile?.let {
                        shareDataViewModel.requestSelectedImage(
                            arrayListOf(
                                UploadFile(
                                    file = videoFile,
                                    isVideo = true
                                )
                            )
                        )
                    }
                    navController.navigate(R.id.action_upload_preview)
                }
            }
        }
    }


    override fun onStartedSeeking() {}

    override fun onStoppedSeeking() {}

    override fun onValueChanged(minThumbValue: Int, maxThumbValue: Int) {
        startMilliseconds = (videoDuration * minThumbValue / 100)
        endMilliseconds = (videoDuration * maxThumbValue / 100)
        viewBinding.tvStartTime.text = startMilliseconds.convertMillieToHMmSs()
        viewBinding.tvEndTime.text = endMilliseconds.convertMillieToHMmSs()
        viewBinding.videoView.seekTo(if (minValue != minThumbValue) startMilliseconds.toInt() else endMilliseconds.toInt())
        minValue = minThumbValue
        maxValue = maxThumbValue
    }
}