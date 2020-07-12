package com.sg.snapshop.view.card

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sg.core.CoreApplication
import com.sg.core.model.ProductVariant
import com.sg.core.param.AddProductParam
import com.sg.core.param.ProductVariantParam
import com.sg.core.util.ObjectHandler
import com.sg.core.vo.Result
import com.sg.snapshop.R
import com.sg.snapshop.databinding.ItemShoppingCardBinding
import com.sg.snapshop.ext.*

class ShoppingCardAdapter(val listener: ((Any?) -> Unit)? = null): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val productList = arrayListOf<ProductVariant>()

    private val isLogin = ObjectHandler.isLogin()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val dataBinding = DataBindingUtil.inflate<ItemShoppingCardBinding>(LayoutInflater.from(parent.context), R.layout.item_shopping_card, parent, false)
        return ItemViewHolder(dataBinding)
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        ( holder as? ItemViewHolder)?.bind(productList[position])
    }

    fun addProduct(list: ArrayList<ProductVariant>){
        this.productList.apply {
            clear()
            addAll(list.sortedBy {
                it.product_variant.product?.id
            })
            notifyDataSetChanged()
        }
    }

    // just for not login yet
    fun removeItem(id: Long){
        val index = productList.indexOfFirst { it.product_variant.id == id }
        if (index != -1){
            productList.removeAt(index)
            notifyItemRemoved(index)
        }
    }

    inner class ItemViewHolder(val viewBinding: ItemShoppingCardBinding): RecyclerView.ViewHolder(viewBinding.root){

        fun bind(item: ProductVariant){
            viewBinding.data = item

            item.product_variant.apply {
                val locale = CoreApplication.instance.currency.getLocale()!!
                viewBinding.tvPrice.text = price_after_tax?.roundPrice(locale)
                viewBinding.tvDiscountPrice.text = price_after_tax?.roundPrice(locale)

                viewBinding.tvOriginPrice.apply {
                    text = compare_at_price_after_tax?.roundPrice(locale)
                    paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                }

            }

            viewBinding.root.setOnClickListener {
                listener?.invoke(item.product_variant)
            }

            viewBinding.ivClose.setOnClickListener {
                listener?.invoke(item.product_variant.id)
            }

            setUpBtnQuantity(item)

            setUpBtnSize(item)

            setUpBtnColor(item)

            viewBinding.tvQuantity.setOnClickListener {
                viewBinding.btnQuantity.performClick()
            }

            Glide.with(viewBinding.root.context)
                .load(item.product_variant.product?.image?.get(0)?.src)
                .into(viewBinding.ivProduct)

        }

        private fun setUpBtnQuantity(item: ProductVariant){
            var firstSetup = true
            viewBinding.btnQuantity.apply {
                val size = if(item.product_variant.inventory_quantity ?: 0 > 50)
                    50
                else item.product_variant.inventory_quantity ?: 0
                val option = (1..size).toMutableList().map { it.toString() }

                adapter = ArrayAdapter(context, R.layout.item_spinner, option)

                viewBinding.tvQuantity.text = context.getString(R.string.quantity_spinner, item.quantity.toString())

                if (item.quantity <= size)
                    setSelection(item.quantity - 1)

                onItemSelectedListener = object  : AdapterView.OnItemSelectedListener{
                    override fun onNothingSelected(p0: AdapterView<*>?) {

                    }

                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, id: Long) {
                        if (firstSetup){
                            firstSetup = false
                            return
                        }
                        val quantity = getItemAtPosition(position).toString().toInt()

                        viewBinding.tvQuantity.text = context.getString(R.string.quantity_spinner, getItemAtPosition(position) as String)
                        if (quantity != item.quantity) {
                            if (isLogin.not()){
                                listener?.invoke(
                                    Pair(
                                        ProductVariant(
                                            product_variant = item.product_variant,
                                            quantity = quantity,
                                            applied_discount = item.applied_discount
                                        ),
                                        -1L
                                    )
                                )
                                return
                            }
                            listener?.invoke(
                                AddProductParam(
                                    arrayListOf(
                                        ProductVariantParam(
                                            product_variant = item.product_variant.id ?: 0,
                                            quantity = quantity
                                        )
                                    )
                                )
                            )
                        }
                    }
                }
            }
        }

        private fun setUpBtnSize(item: ProductVariant){
            viewBinding.btnSize.apply {
                val option = item.product_variant.product?.options?.sizeOption()

                val selectedOption = item.product_variant.options?.sizeOption()

                if (selectedOption == null){
                    gone()
                    return@apply
                }

                adapter = ArrayAdapter<String>(
                    context,
                    R.layout.item_spinner,
                    option?.values ?: listOf()
                )

                setSelection(checkIndex(selectedOption.value, option?.values))

                onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                    override fun onNothingSelected(p0: AdapterView<*>?) { }

                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        val newSize = getItemAtPosition(p2).toString()
                        if ( newSize != selectedOption.value ){
                            val previousIndex = checkIndex(selectedOption.value, option?.values)
                            val variants = item.product_variant.product?.variants
                            val color = item.product_variant.options?.colorOption()?.value
                            val variant = findVariant(Pair(newSize, color), variants)
                            if (variant != null){
                                // at the moment when change size option
                                // will reset quantity option
                                if (variant.inventory_quantity ?: 0 > 0) {
                                    if (isLogin.not()){
                                        listener?.invoke(
                                            Pair(
                                                ProductVariant(
                                                    product_variant = variant.copyFrom(item.product_variant),
                                                    quantity = 1,
                                                    applied_discount = null
                                                ),
                                                item.product_variant.id
                                            )
                                        )
                                        return
                                    }
                                    listener?.invoke(
                                        Pair(
                                            AddProductParam(
                                                arrayListOf(
                                                    ProductVariantParam(
                                                        quantity = 1,
                                                        product_variant = variant.id ?: 0
                                                    )
                                                )
                                            ),
                                            item.product_variant.id
                                        )
                                    )
                                    setUpBtnQuantity(ProductVariant(variant, 1, null))
                                }else{
                                    setSelection(previousIndex)
                                    listener?.invoke(Result.Error(context.getString(R.string.item_selected_no_quantity),null))
                                }
                            }else{
                                setSelection(previousIndex)
                            }
                        }
                    }

                }
            }
        }

        private fun setUpBtnColor(item: ProductVariant){
            viewBinding.btnColor.apply {
                val option = item.product_variant.product?.options?.colorOption()

                val selectedOption = item.product_variant.options?.colorOption()

                if (selectedOption == null){
                    gone()
                    return@apply
                }

                adapter = ArrayAdapter<String>(
                    context,
                    R.layout.item_spinner,
                    option?.values ?: listOf()
                )

                setSelection(checkIndex(selectedOption.value, option?.values))

                onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                    override fun onNothingSelected(p0: AdapterView<*>?) {}

                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        val newColor = getItemAtPosition(p2).toString()
                        if ( newColor != selectedOption.value ){
                            val previousIndex = checkIndex(selectedOption.value, option?.values)
                            val variants = item.product_variant.product?.variants
                            val size = item.product_variant.options?.sizeOption()?.value
                            val variant = findVariant(Pair(size, newColor), variants)
                            if (variant != null){
                                // at the moment when change color option
                                // will reset quantity option
                                if (variant.inventory_quantity ?: 0 > 0) {
                                    if (isLogin.not()){
                                        listener?.invoke(
                                            Pair(
                                                ProductVariant(
                                                    product_variant = variant.copyFrom(item.product_variant),
                                                    quantity = 1,
                                                    applied_discount = null
                                                ),
                                                item.product_variant.id
                                            )
                                        )
                                        return
                                    }
                                    listener?.invoke(
                                        Pair(
                                            AddProductParam(
                                                arrayListOf(
                                                    ProductVariantParam(
                                                        quantity = 1,
                                                        product_variant = variant.id ?: 0
                                                    )
                                                )
                                            ),
                                            item.product_variant.id
                                        )
                                    )
                                    setUpBtnQuantity(ProductVariant(variant, 1, null))
                                }else{
                                    setSelection(previousIndex)
                                    listener?.invoke(Result.Error(context.getString(R.string.item_selected_no_quantity),null))
                                }
                            }else{
                                setSelection(previousIndex)
                            }
                        }
                    }

                }
            }
        }

        private fun checkIndex(option: String?, listOp: ArrayList<String>?): Int{
            return listOp?.indexOfFirst {
                it == option
            } ?: 0
        }
    }
}