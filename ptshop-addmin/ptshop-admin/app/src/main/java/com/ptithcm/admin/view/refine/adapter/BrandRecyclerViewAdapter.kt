package com.ptithcm.admin.view.refine.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jay.widget.StickyHeaders
import com.ptithcm.admin.R
import com.ptithcm.admin.databinding.ItemRefineBrandBinding
import com.ptithcm.admin.databinding.ItemSectionBrandBinding
import com.ptithcm.admin.ext.gone
import com.ptithcm.admin.widget.fastscroll.FastScrollRecyclerViewInterface
import com.ptithcm.core.model.Designer
import java.util.*

class BrandRecyclerViewAdapter(
    var brands: ArrayList<Designer>?,
    override var mapIndex: HashMap<String, Int>,
    private val listener: (designer: Designer?, position: Int) -> Unit
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
                SectionBrandViewHolder(dataBinding)
            }
            else -> {
                val dataBinding = DataBindingUtil.inflate<ItemRefineBrandBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_refine_brand,
                    parent,
                    false
                )
                BrandViewHolder(dataBinding)
            }
        }
    }

    override fun getItemCount(): Int = brands?.size ?: 0

    override fun getItemViewType(position: Int): Int {
        if (brands?.get(position)?.isSection == true) {
            return R.layout.item_section_brand
        }
        return R.layout.item_refine_brand
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val designer = brands?.get(position)
        when (getItemViewType(position)) {
            R.layout.item_section_brand -> {
                (holder as? SectionBrandViewHolder)?.bind(designer)
            }
            else -> {
                (holder as? BrandViewHolder)?.viewBinding?.ivApply?.gone()
                (holder as? BrandViewHolder)?.bind(designer)
                (holder as? BrandViewHolder)?.viewBinding?.tvName?.setOnClickListener {
                    listener.invoke(designer, position)
                }
            }
        }
    }

    override fun teardownStickyHeaderView(view: View) {
        ViewCompat.setElevation(view, 0f)
    }

    override fun setupStickyHeaderView(view: View) {
        ViewCompat.setElevation(view, 0f)
    }

    override fun isStickyHeader(position: Int): Boolean {
        return getItemViewType(position) == R.layout.item_section_brand
    }

    inner class BrandViewHolder(val viewBinding: ItemRefineBrandBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(designer: Designer?) {
            viewBinding.brand = designer
            viewBinding.isChoose = designer?.isChoose
        }
    }

    inner class SectionBrandViewHolder(private val viewBinding: ItemSectionBrandBinding) :
        RecyclerView.ViewHolder(
            viewBinding
                .root
        ) {

        fun bind(designer: Designer?) {
            viewBinding.data = designer
        }
    }
}