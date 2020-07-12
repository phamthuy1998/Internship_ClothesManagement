package com.sg.core.repository

import androidx.lifecycle.LiveData
import com.sg.core.model.Basket
import com.sg.core.model.CreditCard
import com.sg.core.model.ListLocation
import com.sg.core.model.Location
import com.sg.core.param.PaymentMethodParam
import com.sg.core.param.UpdateAddressParam
import com.sg.core.param.UpdatePaymentMethodParam
import com.sg.core.vo.Result

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