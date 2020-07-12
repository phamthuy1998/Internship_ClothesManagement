package com.sg.core.repository

import androidx.lifecycle.LiveData
import com.sg.core.model.Checkout
import com.sg.core.model.ShippingRate
import com.sg.core.model.TaxCheckout
import com.sg.core.param.CheckoutParam
import com.sg.core.param.DiscountParam
import com.sg.core.param.ShippingRateParam
import com.sg.core.vo.Result

interface CheckoutRepository {

    suspend fun getShippingRates(id: Int?, identifier: String?): LiveData<Result<ShippingRate>>

    suspend fun getCheckout(): LiveData<Result<Checkout>>

    suspend fun addDiscountCode(param: DiscountParam): LiveData<Result<Checkout>>

    suspend fun getTaxCheckout(id: Int?, identifier: String?): LiveData<Result<TaxCheckout>>

    suspend fun shippingRatesPreOrder(id: Int?, identifier: String?, shippingRateParam: ShippingRateParam): LiveData<Result<Checkout>>

    suspend fun checkoutPlaceOrder(checkoutParam: CheckoutParam): LiveData<Result<Checkout>>
}