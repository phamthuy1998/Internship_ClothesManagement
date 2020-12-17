package com.n16dccn159.core.repository.impl

import androidx.lifecycle.LiveData
import com.n16dccn159.core.api.ApiService
import com.n16dccn159.core.data.remote.NetworkBoundResource
import com.n16dccn159.core.model.Basket
import com.n16dccn159.core.model.CreditCard
import com.n16dccn159.core.model.ListLocation
import com.n16dccn159.core.model.Location
import com.n16dccn159.core.param.PaymentMethodParam
import com.n16dccn159.core.param.UpdateAddressParam
import com.n16dccn159.core.param.UpdatePaymentMethodParam
import com.n16dccn159.core.repository.PaymentRepository
import com.n16dccn159.core.vo.ApiResponse
import com.n16dccn159.core.vo.Result
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