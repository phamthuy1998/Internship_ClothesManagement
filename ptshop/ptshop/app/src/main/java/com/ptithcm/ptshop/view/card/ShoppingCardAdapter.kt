package com.ptithcm.ptshop.view.card

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ptithcm.core.model.ProductClothesDetail
import com.ptithcm.core.util.ObjectHandler
import com.ptithcm.core.vo.Result
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.databinding.ItemShoppingCardBinding
import com.ptithcm.ptshop.ext.clone
import com.ptithcm.ptshop.ext.startAnimationError

class ShoppingCardAdapter(val listener: ((Int, Any?) -> Unit)? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var productList = arrayListOf<ProductClothesDetail>()

    private val isLogin = ObjectHandler.isLogin()

    var curProduct: ProductClothesDetail? = null

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
        productList = list
        notifyDataSetChanged()
    }

    // just for not login yet
    fun removeItem(product: ProductClothesDetail?) {
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
                curProduct = item
                listener?.invoke(GO_TO_PRODUCT_DETAIL, item)
            }

            viewBinding.ivClose.setOnClickListener {
                curProduct = item
                listener?.invoke(DELETE_PRODUCT, item)
            }

            setUpBtnQuantity(item)
            setUpBtnColor(item)
            setUpBtnSize(item)

            viewBinding.tvQuantity.setOnClickListener {
                curProduct = item
                viewBinding.btnQuantity.performClick()
            }

            if (item.isError)
                viewBinding.root.startAnimationError()

            viewBinding.executePendingBindings()
        }

        private fun setUpBtnQuantity(item: ProductClothesDetail) {
            val quantityInStock = item.getSizeAndColorById(
                sizeId = item.selectedSize?.id,
                colorId = item.selectedColor?.id
            )?.quantity ?: 0
            val realQuantity = quantityInStock.coerceAtMost(50)

            val quantityInCart = item.quantityInCart?.quantity?.coerceAtMost(realQuantity) ?: 0

            viewBinding.tvQuantity.text =
                context.getString(R.string.quantity_spinner, quantityInCart.toString())

            val option = (1..realQuantity).toMutableList().map { it.toString() }
            viewBinding.btnQuantity.adapter = ArrayAdapter(context, R.layout.item_spinner, option)
            viewBinding.btnQuantity.setSelection(quantityInCart - 1, true)
            viewBinding.btnQuantity.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(p0: AdapterView<*>?) {}

                    override fun onItemSelected(
                        p0: AdapterView<*>?,
                        p1: View?,
                        position: Int,
                        id: Long
                    ) {
                        curProduct = item
                        val quantity =
                            viewBinding.btnQuantity.getItemAtPosition(position).toString().toInt()

                        viewBinding.tvQuantity.text = context.getString(
                            R.string.quantity_spinner,
                            viewBinding.btnQuantity.getItemAtPosition(position) as String
                        )
                        if (quantity != item.quantityInCart?.quantity) {
                            item.quantityInCart?.quantity = quantity
                            listener?.invoke(UPDATE_QUANTITY, item)
                        }
                    }
                }
        }

        private fun setUpBtnSize(item: ProductClothesDetail) {
            val sizeOptionsStr = item.sizes?.map { it.sizeName } ?: listOf()
            viewBinding.btnSize.adapter =
                ArrayAdapter<String>(context, R.layout.item_spinner, sizeOptionsStr)
            viewBinding.btnSize.setSelection(
                checkIndex(
                    item.selectedSize?.sizeName,
                    sizeOptionsStr
                )
            )

            viewBinding.btnSize.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(p0: AdapterView<*>?) {}

                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        curProduct = item
                        val newSizeOption = item.sizes?.getOrNull(p2)

                        if (newSizeOption?.id != item.selectedSize?.id) {
                            val previousIndex =
                                checkIndex(item.selectedSize?.sizeName, sizeOptionsStr)

                            val newSizeColor = item.getSizeAndColorById(
                                sizeId = newSizeOption?.id,
                                colorId = item.selectedColor?.id
                            )

                            if (newSizeColor?.quantity ?: 0 != 0) {
                                item.selectedSize = newSizeOption
                                item.quantityInCart = clone(newSizeColor)?.apply {
                                    quantity = item.quantityInCart?.quantity?.coerceAtMost(
                                        newSizeColor?.quantity ?: 0
                                    )
                                }

                                curProduct = item
                                listener?.invoke(UPDATE_PRODUCT, item)

                                setUpBtnQuantity(item)
                            } else {
                                viewBinding.btnSize.setSelection(previousIndex)
                                viewBinding.root.startAnimationError()
                                listener?.invoke(
                                    ERROR,
                                    Result.Error(
                                        context.getString(R.string.item_selected_no_quantity),
                                        null
                                    )
                                )
                            }
                        }
                    }

                }
        }

        private fun setUpBtnColor(item: ProductClothesDetail) {
            val colorOptionsStr = item.colors?.map { it.colorName } ?: listOf()
            viewBinding.btnColor.adapter = ArrayAdapter<String>(
                context,
                R.layout.item_spinner,
                colorOptionsStr
            )

            viewBinding.btnColor.setSelection(
                checkIndex(
                    item.selectedColor?.colorName,
                    colorOptionsStr
                )
            )

            viewBinding.btnColor.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(p0: AdapterView<*>?) {}

                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        val newColorOption = item.colors?.getOrNull(p2)
                        if (newColorOption?.id != item.selectedColor?.id) {
                            val previousIndex =
                                checkIndex(item.selectedColor?.colorName, colorOptionsStr)

                            val newSizeColor = item.getSizeAndColorById(
                                sizeId = item.selectedSize?.id,
                                colorId = newColorOption?.id
                            )

                            if (newSizeColor?.quantity ?: 0 != 0) {
                                item.selectedColor = newColorOption
                                item.quantityInCart = clone(newSizeColor)?.apply {
                                    quantity = item.quantityInCart?.quantity?.coerceAtMost(
                                        newSizeColor?.quantity ?: 0
                                    )
                                }

                                curProduct = item
                                listener?.invoke(UPDATE_PRODUCT, item)

                                setUpBtnQuantity(item)
                            } else {
                                viewBinding.btnColor.setSelection(previousIndex)
                                viewBinding.root.startAnimationError()
                                listener?.invoke(
                                    ERROR,
                                    Result.Error(
                                        context.getString(R.string.item_selected_no_quantity),
                                        null
                                    )
                                )
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

    companion object {
        const val GO_TO_PRODUCT_DETAIL = 0
        const val DELETE_PRODUCT = 1
        const val UPDATE_QUANTITY = 2
        const val UPDATE_PRODUCT = 3
        const val ERROR = 4
    }
}