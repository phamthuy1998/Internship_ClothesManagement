package com.ptithcm.ptshop.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ptithcm.core.vo.Result
import kotlinx.android.synthetic.main.layout_load_more.view.*

class NetworkStateItemViewHolder(
    val view: View,
    private val retryCallback: () -> Unit
) : RecyclerView.ViewHolder(view) {

    companion object {
        fun toVisibility(constraint: Boolean): Int {
            return if (constraint) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }

    fun <T> bindTo(networkState: Result<T>?) {
        view.apply {
            retryBtn.setOnClickListener {
                retryCallback()
            }
            // hide when networkState is Result.Loading
            progressBar.visibility =
                toVisibility(networkState is Result.LoadingMore)
//            retryBtn.visibility = toVisibility(networkState is Result.Error ||networkState is Result.ErrorPaging)
            tvMessage.visibility =
                toVisibility(networkState is Result.LoadingMore)
        }
    }
}
