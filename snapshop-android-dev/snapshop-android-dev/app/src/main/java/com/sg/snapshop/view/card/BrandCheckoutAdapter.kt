package com.sg.snapshop.view.card

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.text.HtmlCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sg.core.CoreApplication
import com.sg.core.model.CheckoutBrand
import com.sg.core.model.ProductVariant
import com.sg.snapshop.R
import com.sg.snapshop.constant.KEY_EMPTY
import com.sg.snapshop.databinding.ItemBrandCheckoutBinding
import com.sg.snapshop.ext.roundPrice
import java.util.*

/*Note for logic show discount info
* There are 2 type of discount(at this moment):
* A: discount from snap shop store - based on object Discount when call checkout api
* B: discount from merchant store - has 2 type
*   first one: 1 product variant discount - based on ProductVariant/applied_discount
*   second one: brand discount - based on CheckoutBrand/applied_discount
* */
class BrandCheckoutAdapter(val listenerId: ((Int?) -> Unit)? = null,
                           val listenerPair: ((Pair<Int, String>) -> Unit)? = null): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listItem = arrayListOf<CheckoutBrand>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<ItemBrandCheckoutBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_brand_checkout,
            parent,
            false
        )
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int = listItem.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ItemViewHolder)?.bind(listItem[position])
    }

    fun addList(list: ArrayList<CheckoutBrand>){
        this.listItem.apply {
            clear()
            addAll(list.sortedBy {
                it.product_variants?.size
            }.reversed())
            notifyDataSetChanged()
        }
    }

    inner class ItemViewHolder(val binding: ItemBrandCheckoutBinding): RecyclerView.ViewHolder(binding.root){

        private val productCheckoutAdapter = ProductCheckoutAdapter()

        fun bind(item: CheckoutBrand){
            val locale = CoreApplication.instance.currency.getLocale()!!
            binding.apply {
                title = item.brand?.brand_name
                appliedDiscount = item.applied_discount
                sellerDesc.text = HtmlCompat.fromHtml(root.context.getString(R.string.seller_condition), HtmlCompat.FROM_HTML_MODE_COMPACT)
                rvProductCheckout.adapter = productCheckoutAdapter
                productCheckoutAdapter.addToList(item.product_variants as? ArrayList<ProductVariant> ?: arrayListOf())
                sellerDesc.setOnClickListener {
                    listenerId?.invoke(item.brand?.id)
                }
                spinnerDelivery.apply {
                    var arr = item.rates?.rates?.map { rate ->
                        "${rate.price?.roundPrice(locale)} - ${rate.title}".toUpperCase(Locale.getDefault())
                    }
                    if (arr == null || arr.isEmpty()){
                        isEnabled = false
                        arr = arrayListOf(context.getString(R.string.select_delivery_method).toUpperCase(
                            Locale.getDefault()
                        ))
                    }
                    adapter = ArrayAdapter<String>(context, R.layout.item_spinner, arr)
                    onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                        override fun onNothingSelected(p0: AdapterView<*>?) {}

                        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                            val pair = Pair(position, item.checkout_identifier ?: KEY_EMPTY)
                            listenerPair?.invoke(pair)
                        }
                    }
                }
            }
        }
    }
}