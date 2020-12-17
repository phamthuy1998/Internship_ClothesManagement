package com.n16dccn159.admin.view.profile

import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.n16dccn159.admin.R
import com.n16dccn159.admin.base.BaseFragment
import com.n16dccn159.admin.constant.KEY_ARGUMENT_OBJECT
import com.n16dccn159.admin.databinding.FragmentProfileCropImageBinding
import com.n16dccn159.admin.ext.transparentStatusBar
import com.n16dccn159.admin.util.FileUtil
import com.n16dccn159.admin.widget.CropImageView
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