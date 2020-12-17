package com.n16dccn159.core.repository

import androidx.lifecycle.LiveData
import com.n16dccn159.core.model.Checkout
import com.n16dccn159.core.model.ShippingRate
import com.n16dccn159.core.model.TaxCheckout
import com.n16dccn159.core.model.wish.ObjectResponse
import com.n16dccn159.core.param.CheckoutParam
import com.n16dccn159.core.param.DiscountParam
import com.n16dccn159.core.param.RequestCheckoutParam
import com.n16dccn159.core.param.ShippingRateParam
import com.n16dccn159.core.vo.Result

interface CheckoutRepository {

    suspend fun getShippingRates(id: Int?, identifier: String?): LiveData<Result<ShippingRate>>

    suspend fun getCheckout(): LiveData<Result<Checkout>>

    suspend fun addDiscountCode(param: DiscountParam): LiveData<Result<Checkout>>

    suspend fun getTaxCheckout(id: Int?, identifier: String?): LiveData<Result<TaxCheckout>>

    suspend fun shippingRatesPreOrder(
        id: Int?,
        identifier: String?,
        shippingRateParam: ShippingRateParam
    ): LiveData<Result<Checkout>>

    suspend fun checkoutPlaceOrder(checkoutParam: CheckoutParam): LiveData<Result<Checkout>>

    suspend fun requestCheckout(requestCheckoutParam: RequestCheckoutParam): LiveData<Result<ObjectResponse<Any>>>
}