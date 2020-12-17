package com.n16dccn159.admin.view.wishlist.overview

import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.n16dccn159.admin.R
import com.n16dccn159.admin.util.BitmapResolver
import kotlinx.android.synthetic.main.fragment_image_zoom.*
import kotlinx.coroutines.runBlocking


class ImageZoomFragment(var listener: (() -> Unit)? = null) : Fragment() {

    companion object {
        const val KEY_PROMOTION_BANNER = "key_promotion_banner"

        fun newInstance(
            promotionBanner: String?,
            listener: (() -> Unit)? = null
        ): ImageZoomFragment {
            val fragment = ImageZoomFragment(listener)
            fragment.arguments = Bundle().apply {
                putString(KEY_PROMOTION_BANNER, promotionBanner)
            }
            return fragment
        }
    }

    private var imageUri: Uri? = null
    private var image: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        arguments?.let {
            image = it.getString(ImageFragment.KEY_PROMOTION_BANNER)
            imageUri = it.getParcelable("uri")
        }
        return inflater.inflate(R.layout.fragment_image_zoom, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (imageUri != null) {
            runBlocking {
                val bitmap: Bitmap? =
                    BitmapResolver.getBitmap(
                        requireContext().contentResolver,
                        imageUri
                    )
                ivProductionOverView.setImageBitmap(bitmap)
            }
        } else {
            val glideApp = Glide.with(requireContext())
            glideApp.load(image).into(ivProductionOverView)
        }
        ivProductionOverView.setOnClickListener {
            listener?.invoke()
        }
    }
}