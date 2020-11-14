package com.ptithcm.core.repository

import androidx.lifecycle.LiveData
import com.ptithcm.core.model.Checkout
import com.ptithcm.core.model.ShippingRate
import com.ptithcm.core.model.TaxCheckout
import com.ptithcm.core.model.wish.ObjectResponse
import com.ptithcm.core.param.CheckoutParam
import com.ptithcm.core.param.DiscountParam
import com.ptithcm.core.param.RequestCheckoutParam
import com.ptithcm.core.param.ShippingRateParam
import com.ptithcm.core.vo.Result

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