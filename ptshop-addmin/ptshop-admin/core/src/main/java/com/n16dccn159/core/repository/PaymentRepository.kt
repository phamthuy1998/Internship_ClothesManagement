package com.n16dccn159.core.repository

import androidx.lifecycle.LiveData
import com.n16dccn159.core.model.Basket
import com.n16dccn159.core.model.CreditCard
import com.n16dccn159.core.model.ListLocation
import com.n16dccn159.core.model.Location
import com.n16dccn159.core.param.PaymentMethodParam
import com.n16dccn159.core.param.UpdateAddressParam
import com.n16dccn159.core.param.UpdatePaymentMethodParam
import com.n16dccn159.core.vo.Result

interface PaymentRepository {
    suspend fun getListLocation(): LiveData<Result<ListLocation>>
    suspend fun getLocation(id: Int): LiveData<Result<Location>>
    suspend fun updateBookAddressPayment(param: UpdateAddressParam): LiveData<Result<Basket>>
    suspend fun getPaymentMethods(): LiveData<Result<ArrayList<CreditCard?>>>
    suspend fun deletePaymentMethod(cardId: String): LiveData<Result<Void>>
    suspend fun addPaymentMethod(param: PaymentMethodParam): LiveData<Result<String>>
    suspend fun updatePaymentMethod(
        cardId: String,
        param: UpdatePaymentMethodParam
    ): LiveData<Result<CreditCard>>
}