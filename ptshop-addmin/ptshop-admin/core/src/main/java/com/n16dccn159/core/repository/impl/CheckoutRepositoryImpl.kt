package com.n16dccn159.core.repository.impl

import androidx.lifecycle.LiveData
import com.n16dccn159.core.api.ApiClothesService
import com.n16dccn159.core.api.ApiService
import com.n16dccn159.core.data.remote.NetworkBoundResource
import com.n16dccn159.core.model.Checkout
import com.n16dccn159.core.model.ShippingRate
import com.n16dccn159.core.model.TaxCheckout
import com.n16dccn159.core.model.wish.ObjectResponse
import com.n16dccn159.core.param.CheckoutParam
import com.n16dccn159.core.param.DiscountParam
import com.n16dccn159.core.param.RequestCheckoutParam
import com.n16dccn159.core.param.ShippingRateParam
import com.n16dccn159.core.repository.CheckoutRepository
import com.n16dccn159.core.vo.Result
import retrofit2.Response

class CheckoutRepositoryImpl(val api: ApiService, val apiClothes: ApiClothesService) :
    CheckoutRepository {

    override suspend fun getShippingRates(
        id: Int?,
        identifier: String?
    ): LiveData<Result<ShippingRate>> {
        return object : NetworkBoundResource<ShippingRate, ShippingRate>() {
            override fun processResponse(response: ShippingRate) = response
            override suspend fun createCall(): Response<ShippingRate> =
                api.getShippingRates(id, identifier)
        }.build().asLiveData()
    }

    override suspend fun getCheckout(): LiveData<Result<Checkout>> {
        return object : NetworkBoundResource<Checkout, Checkout>() {
            override fun processResponse(response: Checkout) = response
            override suspend fun createCall(): Response<Checkout> = api.getCheckout()
        }.build().asLiveData()
    }

    override suspend fun addDiscountCode(param: DiscountParam): LiveData<Result<Checkout>> {
        return object : NetworkBoundResource<Checkout, Checkout>(){
            override fun processResponse(response: Checkout) = response
            override suspend fun createCall(): Response<Checkout> = api.addDiscountCode(param)
        }.build().asLiveData()
    }

    override suspend fun getTaxCheckout(id: Int?, identifier: String?): LiveData<Result<TaxCheckout>> {
        return object : NetworkBoundResource<TaxCheckout, TaxCheckout>(){
            override fun processResponse(response: TaxCheckout) = response
            override suspend fun createCall(): Response<TaxCheckout> = api.getTaxeCheckout(id, identifier)
        }.build().asLiveData()
    }

    override suspend fun shippingRatesPreOrder(id: Int?, identifier: String?, shippingRateParam: ShippingRateParam): LiveData<Result<Checkout>> {
        return object : NetworkBoundResource<Checkout, Checkout>(){
            override fun processResponse(response: Checkout) = response
            override suspend fun createCall(): Response<Checkout> =
                api.checkShippingRate(id, identifier, shippingRateParam)
        }.build().asLiveData()
    }

    override suspend fun checkoutPlaceOrder(checkoutParam: CheckoutParam): LiveData<Result<Checkout>> {
        return object : NetworkBoundResource<Checkout, Checkout>() {
            override fun processResponse(response: Checkout) = response
            override suspend fun createCall(): Response<Checkout> = api.checkout(checkoutParam)
        }.build().asLiveData()
    }

    override suspend fun requestCheckout(requestCheckoutParam: RequestCheckoutParam): LiveData<Result<ObjectResponse<Any>>> {
        return object : NetworkBoundResource<ObjectResponse<Any>, ObjectResponse<Any>>() {
            override fun processResponse(response: ObjectResponse<Any>) = response
            override suspend fun createCall(): Response<ObjectResponse<Any>> =
                apiClothes.requestCheckout(requestCheckoutParam)
        }.build().asLiveData()
    }
}