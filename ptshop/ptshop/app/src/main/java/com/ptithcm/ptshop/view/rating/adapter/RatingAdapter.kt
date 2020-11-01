package com.ptithcm.ptshop.view.rating.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuPopupHelper
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ptithcm.core.model.Rating
import com.ptithcm.core.model.SubRating
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.databinding.ItemRatingStatisticBinding
import com.ptithcm.ptshop.databinding.LayoutRatingItemBinding
import com.ptithcm.ptshop.view.question.adapter.ITEM_DEL
import com.ptithcm.ptshop.view.question.adapter.ITEM_EDIT
import java.lang.reflect.Field
import java.util.*


class RatingAdapter(
    val listener: ((item: Rating?, position: Int?, typeEvent: Int, posImage: Int?) -> Unit)? = null,
    val accID: Int?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_ITEM = 1
        const val DATE_INPUT_FORMAT = "yyyy-MM-dd'T'HH:mm:ss+00:00"
        const val ITEM_IMAGE = 4
    }

    private var ratingList = arrayListOf<Rating>()

    var currentPosition: Int? = null

    override fun getItemId(position: Int): Long =
        ratingList[position].ratingID.hashCode().toLong()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> {
                val binding = DataBindingUtil.inflate<ItemRatingStatisticBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_rating_statistic,
                    parent,
                    false
                )
                HeaderViewHolder(binding)
            }
            else -> {
                val binding = DataBindingUtil.inflate<LayoutRatingItemBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.layout_rating_item,
                    parent,
                    false
                )
                ItemViewHolder(binding)
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) TYPE_HEADER
        else TYPE_ITEM
    }

    override fun getItemCount(): Int = ratingList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> holder.bind()
            is ItemViewHolder -> holder.bind(ratingList[position])
        }
    }

    fun setDataRating() {

        notifyItemChanged(0)
    }

    fun submitList(list: ArrayList<Rating>) {
        ratingList.clear()
        ratingList.add(Rating())
        ratingList.addAll(list)
        notifyDataSetChanged()
    }

    fun addQuestion(rating: Rating) {
        ratingList.add(rating)
        notifyItemChanged(ratingList.size - 1)
    }

    fun addSubRating(rating: SubRating?, position: Int?) {
        if (position != null) {
            if (rating != null) {
                ratingList[position].subComments?.add(rating)

            }
            notifyItemChanged(position)
        }
    }

    fun delRatingFailure() {
        ratingList.removeAt(ratingList.size - 1)
    }

    fun removeItem(position: Int?) {
        position ?: return
        ratingList.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class ItemViewHolder(val binding: LayoutRatingItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("RestrictedApi")
        fun bind(questionItem: Rating) {
            binding.apply {
                item = questionItem
                accountID = accID

                btnMore.setOnClickListener {
                    currentPosition = adapterPosition
                    showPopup(questionItem, btnMore)
                }

                ivImage1.setOnClickListener {
                    listener?.invoke(item, adapterPosition, ITEM_IMAGE, 0)
                }
                ivImage2.setOnClickListener {
                    listener?.invoke(item, adapterPosition, ITEM_IMAGE, 1)
                }
                ivImageVideo.setOnClickListener {
                    listener?.invoke(item, adapterPosition, ITEM_IMAGE, 2)
                }
                executePendingBindings()
            }
        }

        @SuppressLint("RestrictedApi")
        private fun showPopup(
            item: Rating?,
            btnMore: AppCompatImageView
        ) {
            val popup = PopupMenu(btnMore.context, btnMore)
            popup.menuInflater.inflate(R.menu.menu_question_item, popup.menu)

            popup.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.menuEdit -> {
                        listener?.invoke(item, adapterPosition, ITEM_EDIT, null)
                        true
                    }
                    R.id.menuDel -> {
                        listener?.invoke(item, adapterPosition, ITEM_DEL, null)
                        true
                    }

                    else -> false
                }
            }
            try {
                val mFieldPopup: Field = popup.javaClass.getDeclaredField("mPopup")
                mFieldPopup.isAccessible = true
                val mPopup: MenuPopupHelper = mFieldPopup.get(popup) as MenuPopupHelper
                mPopup.setForceShowIcon(true)
            } catch (e: Exception) {
            }

            popup.show()
        }
    }

    inner class HeaderViewHolder(val binding: ItemRatingStatisticBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {

        }
    }
}