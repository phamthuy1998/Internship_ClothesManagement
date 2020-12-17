package com.n16dccn159.admin.view.wishlist.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.n16dccn159.admin.R
import com.n16dccn159.admin.constant.KEY_ARGUMENT_BOOLEAN
import kotlinx.android.synthetic.main.fragment_production_overview.*

class ImageFragment(var listener: (() -> Unit)? = null) : Fragment() {

    companion object {
        const val KEY_PROMOTION_BANNER = "key_promotion_banner"
        fun newInstance(promotionBanner: String?, isForStoryDetail: Boolean = false, listener: (() -> Unit)? = null): ImageFragment {
            val fragment = ImageFragment(listener)
            fragment.arguments = Bundle().apply {
                putString(KEY_PROMOTION_BANNER, promotionBanner)
                putBoolean(KEY_ARGUMENT_BOOLEAN, isForStoryDetail)
            }
            return fragment
        }
    }

    private var image: String? = null
    private var isForStoryDetail: Boolean = false

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        arguments?.let {
            image = it.getString(KEY_PROMOTION_BANNER)
            isForStoryDetail = it.getBoolean(KEY_ARGUMENT_BOOLEAN)
        }
        val idLayout = if (isForStoryDetail)
            R.layout.fragment_story_overview
        else
            R.layout.fragment_production_overview

        return inflater.inflate(idLayout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val glideApp = Glide.with(context!!)
        glideApp
            .load(image)
            .error(R.drawable.ic_place_holder)
            .placeholder(R.drawable.ic_place_holder)
            .into(ivProductionOverView)
        ivProductionOverView.setOnClickListener {
            listener?.invoke()
        }
    }
}