package com.sg.snapshop.view.wishlist.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.sg.snapshop.R
import kotlinx.android.synthetic.main.fragment_image_zoom.*

class ImageZoomFragment(var listener: (() -> Unit)? = null) : Fragment() {

    companion object {
        const val KEY_PROMOTION_BANNER = "key_promotion_banner"

        fun newInstance(promotionBanner: String?, listener: (() -> Unit)? = null): ImageZoomFragment {
            val fragment = ImageZoomFragment(listener)
            fragment.arguments = Bundle().apply {
                putString(KEY_PROMOTION_BANNER, promotionBanner)
            }
            return fragment
        }
    }

    private var image: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        arguments?.let {
            image = it.getString(ImageFragment.KEY_PROMOTION_BANNER)
        }
        return inflater.inflate(R.layout.fragment_image_zoom, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val glideApp = Glide.with(context!!)
        glideApp.load(image).into(ivProductionOverView)
        ivProductionOverView.setOnClickListener {
            listener?.invoke()
        }
    }
}