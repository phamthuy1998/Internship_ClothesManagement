package com.ptithcm.ptshop.view.card

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ptithcm.core.model.Color
import com.ptithcm.core.model.ProductClothesDetail
import com.ptithcm.core.model.ProductVariant
import com.ptithcm.core.model.Size
import com.ptithcm.core.param.AddProductParam
import com.ptithcm.core.param.ProductVariantParam
import com.ptithcm.core.util.ObjectHandler
import com.ptithcm.core.vo.Result
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.databinding.ItemShoppingCardBinding
import com.ptithcm.ptshop.ext.clone
import com.ptithcm.ptshop.ext.copyFrom
import com.ptithcm.ptshop.ext.findVariant
import com.ptithcm.ptshop.ext.gone

class ShoppingCardAdapter(val listener: ((Any?) -> Unit)? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val productList = arrayListOf<ProductClothesDetail>()

    private val isLogin = ObjectHandler.isLogin()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val dataBinding = DataBindingUtil.inflate<ItemShoppingCardBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_shopping_card,
            parent,
            false
        )
        return ItemViewHolder(dataBinding)
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ItemViewHolder)?.bind(productList[position])
    }

    fun submitList(list: ArrayList<ProductClothesDetail>) {
        productList.clear()
        productList.addAll(list.sortedBy { it.id })
        notifyDataSetChanged()
    }

    // just for not login yet
    fun removeItem(product: ProductClothesDetail) {
        val index = productList.indexOf(product)
        if (index != -1) {
            productList.removeAt(index)
            notifyItemRemoved(index)
        }
    }

    inner class ItemViewHolder(val viewBinding: ItemShoppingCardBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        val context: Context = viewBinding.root.context

        fun bind(item: ProductClothesDetail) {
            viewBinding.item = item

            viewBinding.root.setOnClickListener {
                listener?.invoke(item)
            }

            viewBinding.ivClose.setOnClickListener {
                listener?.invoke(item)
            }

            val selectedSizeOption =
                item.sizes?.firstOrNull { it.id == item.quantityInCart?.sizeId }
            val selectedColorOption =
                item.colors?.firstOrNull { it.id == item.quantityInCart?.colorID }

            setUpBtnQuantity(item, selectedSizeOption, selectedColorOption)
            setUpBtnColor(item, selectedColorOption)
            setUpBtnSize(item, selectedSizeOption)

            viewBinding.tvQuantity.setOnClickListener {
                viewBinding.btnQuantity.performClick()
            }
        }

        private fun setUpBtnQuantity(
            item: ProductClothesDetail,
            selectedSizeOption: Size?,
            selectedColorOption: Color?
        ) {
            var firstSetup = true
            val realQuantity =
                item.sizesColors?.firstOrNull { it.sizeId == selectedSizeOption?.id && it.colorID == selectedColorOption?.id }?.quantity
                    ?: 0
            val size = realQuantity.coerceAtMost(50)

            viewBinding.tvQuantity.text =
                context.getString(R.string.quantity_spinner, realQuantity.toString())

            val option = (1..size).toMutableList().map { it.toString() }
            viewBinding.btnQuantity.adapter = ArrayAdapter(context, R.layout.item_spinner, option)
            viewBinding.btnQuantity.setSelection(realQuantity - 1, true)
            viewBinding.btnQuantity.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(p0: AdapterView<*>?) {}

                    override fun onItemSelected(
                        p0: AdapterView<*>?,
                        p1: View?,
                        position: Int,
                        id: Long
                    ) {
                        if (firstSetup) {
                            firstSetup = false
                            return
                        }
                        val quantity =
                            viewBinding.btnQuantity.getItemAtPosition(position).toString().toInt()

                        viewBinding.tvQuantity.text = context.getString(
                            R.string.quantity_spinner,
                            viewBinding.btnQuantity.getItemAtPosition(position) as String
                        )
                        if (quantity != item.quantityInCart?.quantity) {
                            listener?.invoke(Pair(item, -1L))
                        }
                    }
                }
        }

        private fun setUpBtnSize(
            item: ProductClothesDetail,
            selectedSizeOption: Size?
        ) {
            val sizeOptionsStr = item.sizes?.map { it.sizeName } ?: listOf()
            viewBinding.btnSize.adapter =
                ArrayAdapter<String>(context, R.layout.item_spinner, sizeOptionsStr)
            viewBinding.btnSize.setSelection(
                checkIndex(
                    selectedSizeOption?.sizeName,
                    sizeOptionsStr
                )
            )

            viewBinding.btnSize.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(p0: AdapterView<*>?) {}

                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        val newSizeOption = item.sizes?.getOrNull(p2)

                        if (newSizeOption?.id != selectedSizeOption?.id) {
                            val previousIndex = checkIndex(selectedSizeOption?.sizeName, sizeOptionsStr)

                            val newSizeColor = item.findQuantityOfSizeAndColor(
                                sizeId = item.quantityInCart?.sizeId,
                                colorId = item.quantityInCart?.colorID
                            )
                            // at the moment when change size option
                            // will reset quantity option
                            if (newSizeColor?.quantity != 0) {
                                item.quantityInCart = clone(newSizeColor)?.apply { quantity = 1 }

                                listener?.invoke(item)

                                setUpBtnQuantity(
                                    item,
                                    newSizeOption,
                                    item
                                )
                            } else {
                                setSelection(previousIndex)
                                listener?.invoke(
                                    Result.Error(
                                        context.getString(R.string.item_selected_no_quantity),
                                        null
                                    )
                                )
                            }
                        }
                    }

                }

            viewBinding.btnSize.apply {
                val option = item.product_variant.product?.options?.sizeOption()

                val selectedOption = item.product_variant.options?.sizeOption()

                if (selectedOption == null) {
                    gone()
                    return@apply
                }

                adapter =

                    setSelection(checkIndex(selectedOption.value, option?.values))


            }
        }

        private fun setUpBtnColor(
            item: ProductClothesDetail,
            selectedColorOption: Color?
        ) {
            viewBinding.btnColor.apply {
                val option = item.product_variant.product?.options?.colorOption()

                val selectedOption = item.product_variant.options?.colorOption()

                if (selectedOption == null) {
                    gone()
                    return@apply
                }

                adapter = ArrayAdapter<String>(
                    context,
                    R.layout.item_spinner,
                    option?.values ?: listOf()
                )

                setSelection(checkIndex(selectedOption.value, option?.values))

                onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(p0: AdapterView<*>?) {}

                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        val newColor = getItemAtPosition(p2).toString()
                        if (newColor != selectedOption.value) {
                            val previousIndex = checkIndex(selectedOption.value, option?.values)
                            val variants = item.product_variant.product?.variants
                            val size = item.product_variant.options?.sizeOption()?.value
                            val variant = findVariant(Pair(size, newColor), variants)
                            if (variant != null) {
                                // at the moment when change color option
                                // will reset quantity option
                                if (variant.inventory_quantity ?: 0 > 0) {
                                    if (isLogin.not()) {
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
                                    setUpBtnQuantity(
                                        ProductVariant(variant, 1, null),
                                        selectedSizeOption,
                                        selectedColorOption
                                    )
                                } else {
                                    setSelection(previousIndex)
                                    listener?.invoke(
                                        Result.Error(
                                            context.getString(R.string.item_selected_no_quantity),
                                            null
                                        )
                                    )
                                }
                            } else {
                                setSelection(previousIndex)
                            }
                        }
                    }

                }
            }
        }

        private fun checkIndex(option: String?, listOp: List<String?>?): Int {
            return listOp?.indexOfFirst {
                it == option
            } ?: 0
        }
    }
}