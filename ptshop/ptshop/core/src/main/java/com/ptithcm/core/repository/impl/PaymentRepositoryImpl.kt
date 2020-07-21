package com.ptithcm.core.repository.impl

import androidx.lifecycle.LiveData
import com.ptithcm.core.api.ApiService
import com.ptithcm.core.data.remote.NetworkBoundResource
import com.ptithcm.core.model.Basket
import com.ptithcm.core.model.CreditCard
import com.ptithcm.core.model.ListLocation
import com.ptithcm.core.model.Location
import com.ptithcm.core.param.PaymentMethodParam
import com.ptithcm.core.param.UpdateAddressParam
import com.ptithcm.core.param.UpdatePaymentMethodParam
import com.ptithcm.core.repository.PaymentRepository
import com.ptithcm.core.vo.ApiResponse
import com.ptithcm.core.vo.Result
import retrofit2.Response

class PaymentRepositoryImpl(val api: ApiService) : PaymentRepository {
    override suspend fun updatePaymentMethod(cardId: String,param: UpdatePaymentMethodParam): LiveData<Result<CreditCard>> {
        return object : NetworkBoundResource<CreditCard, CreditCard>() {
            override fun processResponse(response: CreditCard) = response
            override suspend fun createCall(): Response<CreditCard> = api.updatePaymentMethod(cardId,param)
        }.build().asLiveData()
    }

    override suspend fun addPaymentMethod(param: PaymentMethodParam): LiveData<Result<String>> {
        return object : NetworkBoundResource<ApiResponse<String>, String>() {
            override fun processResponse(response: ApiResponse<String>): String?  = response.detail
            override suspend fun createCall(): Response<ApiResponse<String>> = api.postPaymentMethod(param)
        }.build().asLiveData()
    }

    override suspend fun deletePaymentMethod(cardId: String): LiveData<Result<Void>> {
        return object : NetworkBoundResource<Void, Void>() {
            override fun processResponse(response: Void): Void?  = null

            override suspend fun createCall() = api.deletePaymentMethod(cardId)
        }.build().asLiveData()
    }

    override suspend fun getPaymentMethods(): LiveData<Result<ArrayList<CreditCard?>>> {
        return object : NetworkBoundResource<ArrayList<CreditCard?>, ArrayList<CreditCard?>>() {
            override fun processResponse(response: ArrayList<CreditCard?>) = response
            override suspend fun createCall(): Response<ArrayList<CreditCard?>> =
                api.getPaymentMethods()
        }.build().asLiveData()
    }

    override suspend fun updateBookAddressPayment(param: UpdateAddressParam): LiveData<Result<Basket>> {
        return object : NetworkBoundResource<Basket, Basket>() {
            override fun processResponse(response: Basket) = response
            override suspend fun createCall(): Response<Basket> =
                api.updateBookAddressPayment(param)

        }.build().asLiveData()
    }

    override suspend fun getLocation(id: Int): LiveData<Result<Location>> {
        return object : NetworkBoundResource<Location, Location>() {
            override fun processResponse(response: Location) = response
            override suspend fun createCall(): Response<Location> = api.getLocation(id)
        }.build().asLiveData()
    }

    override suspend fun getListLocation(): LiveData<Result<ListLocation>> {
        return object : NetworkBoundResource<ListLocation, ListLocation>() {
            override fun processResponse(response: ListLocation) = response
            override suspend fun createCall(): Response<ListLocation> = api.getListLocation()
        }.build().asLiveData()
    }

}