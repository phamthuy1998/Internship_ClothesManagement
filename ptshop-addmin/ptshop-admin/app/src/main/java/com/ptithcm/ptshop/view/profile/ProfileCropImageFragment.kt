package com.ptithcm.ptshop.view.profile

import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.base.BaseFragment
import com.ptithcm.ptshop.constant.KEY_ARGUMENT_OBJECT
import com.ptithcm.ptshop.databinding.FragmentProfileCropImageBinding
import com.ptithcm.ptshop.ext.transparentStatusBar
import com.ptithcm.ptshop.util.FileUtil
import com.ptithcm.ptshop.widget.CropImageView
import kotlinx.android.synthetic.main.fragment_profile_crop_image.*

class ProfileCropImageFragment : BaseFragment<FragmentProfileCropImageBinding>(),
    View.OnClickListener, CropImageView.OnEditImageCompleteListener {

    override val layoutId = R.layout.fragment_profile_crop_image

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as? ProfileCropImageActivity)?.apply {
            transparentStatusBar(true)
        }
        val uri = activity?.intent?.getParcelableExtra<Uri>(KEY_ARGUMENT_OBJECT)
        cropImageView?.setOnEditImageCompleteListener(this)
        viewBinding.cropImageView.setImageUriAsync(
            FileUtil().handleSamplingAndRotationBitmap(
                requireContext(), uri
            ) ?: return
        )
    }

    override fun bindEvent() {
        super.bindEvent()
        viewBinding.tvCancel.setOnClickListener(this)
        viewBinding.tvChoose.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tvCancel -> navController.popBackStack()
            R.id.tvChoose -> {
                viewBinding.cropImageView.getCroppedImageAsync()
            }
        }
    }

    override fun onCropImageComplete(result: Bitmap?) {
        val file = FileUtil().createFile(requireContext(), "photo", ".jpg")
        FileUtil().createImageToFile(result, file)
        (activity as? ProfileCropImageActivity)?.returnIntent(file)
    }
}