package com.ptithcm.admin.view.uploadproduct.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ptithcm.core.model.UploadFile
import com.ptithcm.admin.R
import com.ptithcm.admin.databinding.ItemPreviewImageBinding

class PreviewImageAdapter(
    private var listItem: List<UploadFile?>?,
    private val listenerEdit: (file: UploadFile?) -> Unit,
    private val listenerDelete: (file: UploadFile?) -> Unit,
    private val listenerAdd: () -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun setList(data: List<UploadFile>?) {
        when (data?.size) {
            0 -> this.listItem = listOf(null, null, null)
            1 -> this.listItem = listOf(data[0], null, null)
            2 -> this.listItem = listOf(data[0], data[1], null)
            else -> this.listItem = data
        }
        notifyDataSetChanged()
    }

    //    override fun getItemCount() = if ( listItem?.size ?: 0 > 3 ) 3 else listItem?.size ?: 0
    override fun getItemCount() = 3

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemPreviewImageBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_preview_image, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as ItemViewHolder

        viewHolder.bind(listItem?.get(position))

        viewHolder.binding.ivEdit.setOnClickListener {
            listenerEdit(listItem?.get(position))
        }

        viewHolder.binding.ivDelete.setOnClickListener {
            listenerDelete(listItem?.get(position))
        }

        viewHolder.binding.lnAddImage.setOnClickListener {
            listenerAdd()
        }


    }

    inner class ItemViewHolder(val binding: ItemPreviewImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: UploadFile?) {
            binding.item = item
            Glide.with(binding.root)
                .load(if (item?.editFile == null) item?.file?.path else item.editFile?.path)
                .into(binding.ivProduct)

        }
    }

}