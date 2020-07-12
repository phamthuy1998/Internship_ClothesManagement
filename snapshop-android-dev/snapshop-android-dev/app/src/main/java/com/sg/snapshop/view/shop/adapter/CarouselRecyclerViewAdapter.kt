package com.sg.snapshop.view.shop.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sg.core.model.Carousel
import com.sg.snapshop.R
import com.sg.snapshop.databinding.ItemCarouselBinding

class CarouselRecyclerViewAdapter(private var carousels: ArrayList<Carousel>?, private val listener : (carousel : Carousel?) -> Unit) :
    RecyclerView.Adapter<CarouselRecyclerViewAdapter.CarouselViewHolder>() {

    fun setCarousels(carousels: ArrayList<Carousel>?){
        this.carousels = carousels
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val viewBinding = DataBindingUtil.inflate<ItemCarouselBinding>(LayoutInflater.from(parent.context), R.layout.item_carousel, parent, false)
        return CarouselViewHolder(viewBinding)
    }

    override fun getItemCount(): Int = carousels?.size ?: 0

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        val carousel = carousels?.get(position)
        holder.bind(carousel)
        holder.itemView.setOnClickListener {
            listener.invoke(carousel)
        }
        holder.viewBinding.btnShopNow.setOnClickListener {
            listener.invoke(carousel)
        }
    }

    inner class CarouselViewHolder(val viewBinding: ItemCarouselBinding) : RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(carousel: Carousel?){
            viewBinding.data = carousel
        }
    }
}
