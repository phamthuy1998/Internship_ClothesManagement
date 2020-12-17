package com.n16dccn159.admin.view.home

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.n16dccn159.core.BuildConfig
import com.n16dccn159.core.model.Stories
import com.n16dccn159.admin.R
import com.n16dccn159.admin.databinding.ItemHomeSmallBinding
import kotlin.random.Random

class HomePagingAdapter(val listener: ((item: Stories?) -> Unit)?): PagedListAdapter<Stories, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object  : DiffUtil.ItemCallback<Stories>() {
            override fun areItemsTheSame(oldItem: Stories, newItem: Stories): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Stories, newItem: Stories): Boolean {
                return oldItem == newItem
            }

        }
    }

    private var isShowLoadMore: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            R.layout.item_home_small -> {
                val binding =
                    DataBindingUtil.inflate<ItemHomeSmallBinding>(LayoutInflater.from(parent.context), R.layout.item_home_small, parent, false)
                ItemViewHolder(binding)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_load_more, parent, false)
                LoadingViewHolder(view)
            }
        }
    }

    override fun getItemViewType(position: Int): Int =
        if (isShowLoadMore && position == itemCount -1)
            R.layout.layout_load_more
        else
            R.layout.item_home_small

    override fun getItemCount(): Int = super.getItemCount() + if (isShowLoadMore) 1 else 0

    fun setNetworkState(isShowLoadMore: Boolean = false) {
        val hadShowMore = this.isShowLoadMore
        this.isShowLoadMore = isShowLoadMore
        val hasExtraRow = isShowLoadMore
        if (hadShowMore != hasExtraRow) {
            if (hadShowMore) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasExtraRow && isShowLoadMore) {
            notifyItemChanged(itemCount - 1)
        }
    }

    fun removeLoading(){
        notifyItemChanged(itemCount - 1)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (isShowLoadMore)
            return
        /*  this sort because
            ios: item position is 1 2(big one) 3
            android: item position 1 3(big one) 2 */
        val width = 1
        val height = 3

        holder.itemView.layoutParams = RecyclerView.LayoutParams(width, height)
        val item = when{
            position%6 == 1 && position/6 % 2 == 0 && position + 1 <= itemCount -1 -> getItem(position + 1)
            (position - 1)%6 == 1 && (position - 1)/6 % 2 == 0 -> getItem(position - 1)
            else -> getItem(position)
        }
        (holder as? ItemViewHolder)?.bind(item)
    }

    inner class ItemViewHolder(private val binding: ItemHomeSmallBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(story: Stories?) {
            binding.story = story
            binding.root.setOnClickListener {
                listener?.invoke(story)
            }
            if (story?.uploads?.size ?: 0 > 0) {
                story?.uploads?.sortBy{ it.id }
                loadImage("${BuildConfig.AWS_URL_IMAGE}${story?.uploads?.first()?.url}", binding.ivItemHomeSmall)
            }
            else{
                val arr = arrayListOf(R.color.black, R.color.blue, R.color.red)
                binding.ivItemHomeSmall.setImageResource(arr.random())
            }
        }
    }

    inner class LoadingViewHolder(view: View) : RecyclerView.ViewHolder(view)

    private fun loadImage(src: String?, v: AppCompatImageView){
        val rnd = Random
        val backgroundColor = (Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)))
        Glide.with(v.context)
            .load(src)
            .apply(RequestOptions().placeholder(ColorDrawable(backgroundColor)))
            .into(v)
    }
}