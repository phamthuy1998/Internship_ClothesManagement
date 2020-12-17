package com.n16dccn159.admin.view.payment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.n16dccn159.admin.R
import com.n16dccn159.admin.databinding.ItemPaymentMethodBinding
import com.n16dccn159.admin.ext.gone
import com.n16dccn159.admin.util.CheckPaymentMethodType
import com.n16dccn159.admin.util.isType
import com.n16dccn159.core.model.CreditCard
import com.stripe.android.model.Card


class PaymentMethodsAdapter(private var creditCards: ArrayList<CreditCard?>?,private val listener: (creditCard: CreditCard?) -> Unit,
                            private val isFromCheckout: Boolean = false,
                            private val selectedCardId: String? = "") :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun setData(data: ArrayList<CreditCard?>?) {
        this.creditCards = data
        notifyDataSetChanged()
    }
    fun removeItem(position: Int) {
        creditCards?.removeAt(position)
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == R.layout.item_payment_method) {
            val dataBinding = DataBindingUtil.inflate<ItemPaymentMethodBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_payment_method,
                parent,
                false
            )
            PaymentMethodViewHolder(dataBinding)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_add_payment_method, parent, false)
            AddViewHolder(view)
        }
    }

    override fun getItemCount(): Int = creditCards?.size ?: 0

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val type = isType(creditCards?.get(position)?.brand ?: "")
        when (holder) {
            is PaymentMethodViewHolder -> {
                holder.bind(type, creditCards?.get(position))
                holder.viewBinding.container.setOnClickListener {
                    listener.invoke(creditCards?.get(position))
                }
            }
            is AddViewHolder->{
                holder.itemView.findViewById<ConstraintLayout>(R.id.container).setOnClickListener {
                    listener.invoke(null)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (creditCards?.get(position)!=null) {
            R.layout.item_payment_method
        } else {
            1
        }

    }

    fun getItem(position:Int) : CreditCard? {
        return creditCards?.get(position)
    }

    inner class PaymentMethodViewHolder(val viewBinding: ItemPaymentMethodBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(type: CheckPaymentMethodType?, creditCard: CreditCard?) {
            viewBinding.type = type
            viewBinding.brand = creditCard?.brand
            viewBinding.last4digits = creditCard?.last4
            viewBinding.imageCard.setImageResource(Card.getBrandIcon(creditCard?.brand))
            if (isFromCheckout){
                when {
                    creditCard?.id == selectedCardId -> viewBinding.ivNext.setImageResource(R.drawable.ic_check_black_24dp)
//                    creditCard?.default_card == true && selectedCardId?.isEmpty() == true -> viewBinding.ivNext.setImageResource(R.drawable.ic_check_black_24dp)
                    else -> viewBinding.ivNext.gone()
                }
            }
        }
    }

    inner class AddViewHolder(view: View) : RecyclerView.ViewHolder(view)
}