package com.sg.snapshop.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import com.dinuscxj.refresh.IRefreshStatus
import com.sg.snapshop.R
import com.sg.snapshop.databinding.RefreshLayoutViewBinding

class RecyclerRefreshLayout(context: Context, attrs: AttributeSet? = null, text: String? = ""): FrameLayout(context, attrs), IRefreshStatus{

    private var binding: RefreshLayoutViewBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.refresh_layout_view, this, true)

    override fun refreshing() {
        binding
    }

    override fun pullToRefresh() {

    }

    override fun refreshComplete() {

    }

    override fun releaseToRefresh() {

    }

    override fun reset() {

    }

    override fun pullProgress(pullDistance: Float, pullProgress: Float) {

    }

    init {
        binding.text = text
    }

}