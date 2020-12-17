package com.n16dccn159.admin.view.designer.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jay.widget.StickyHeaders
import com.n16dccn159.core.model.Designer
import com.n16dccn159.admin.R
import com.n16dccn159.admin.databinding.ItemSectionBrandBinding
import com.n16dccn159.admin.databinding.ItemStoryBinding
import com.n16dccn159.admin.widget.fastscroll.FastScrollRecyclerViewInterface
import java.util.HashMap

class AllStoresRecyclerViewAdapter(
    var brands: ArrayList<Designer>?,
    override var mapIndex: HashMap<String, Int>,
    private val listener: (designer: Designer?) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), StickyHeaders, StickyHeaders.ViewSetup,
    FastScrollRecyclerViewInterface {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_section_brand -> {
                val dataBinding = DataBindingUtil.inflate<ItemSectionBrandBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_section_brand,
                    parent,
                    false
                )
                SectionStoresViewHolder(dataBinding)
            }
            else -> {
                val dataBinding = DataBindingUtil.inflate<ItemStoryBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_story,
                    parent,
                    false
                )
                StoresViewHolder(dataBinding)
            }
        }
    }

    override fun getItemCount(): Int = brands?.size ?: 0

    override fun getItemViewType(position: Int): Int {
        return when {
            brands?.get(position)?.isSection == true -> R.layout.item_section_brand
            else -> R.layout.item_story
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val designer = brands?.get(position)
        when (getItemViewType(position)) {
            R.layout.item_section_brand -> {
                (holder as? SectionStoresViewHolder)?.bind(designer)
            }
            else -> {
                (holder as? StoresViewHolder)?.bind(designer)
                (holder as? StoresViewHolder)?.viewBinding?.tvName?.setOnClickListener {
                    listener.invoke(designer)
                }
            }
        }
    }

    override fun isStickyHeader(position: Int): Boolean {
        return getItemViewType(position) == R.layout.item_section_brand
    }

    override fun teardownStickyHeaderView(view: View) {
        ViewCompat.setElevation(view, 0f)
    }

    override fun setupStickyHeaderView(view: View) {
        ViewCompat.setElevation(view, 0f)
    }

    inner class StoresViewHolder(val viewBinding: ItemStoryBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(designer: Designer?) {
            viewBinding.brand = designer
        }
    }

    inner class SectionStoresViewHolder(private val viewBinding: ItemSectionBrandBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(designer: Designer?) {
            viewBinding.data = designer
        }
    }
}