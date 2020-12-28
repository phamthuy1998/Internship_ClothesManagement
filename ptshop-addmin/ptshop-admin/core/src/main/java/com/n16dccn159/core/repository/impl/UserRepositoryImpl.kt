package com.n16dccn159.core.repository.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.toLiveData
import com.n16dccn159.core.api.ApiClothesService
import com.n16dccn159.core.api.ApiService
import com.n16dccn159.core.data.remote.BaseDataSourceFactory
import com.n16dccn159.core.data.remote.NetworkBoundResource
import com.n16dccn159.core.model.*
import com.n16dccn159.core.model.wish.ObjectResponse
import com.n16dccn159.core.param.ChangePassParam
import com.n16dccn159.core.param.EditAccountParam
import com.n16dccn159.core.param.UpdateAddressParam
import com.n16dccn159.core.repository.UserRepository
import com.n16dccn159.core.util.PAGE_SIZE
import com.n16dccn159.core.vo.ItemViewModel
import com.n16dccn159.core.vo.ListResponse
import com.n16dccn159.core.vo.Listing
import com.n16dccn159.core.vo.Result
import retrofit2.Response

class UserRepositoryImpl (val api: ApiService, val apiClothes: ApiClothesService): UserRepository {
    override suspend fun updateBookAddress(param: UpdateAddressParam): LiveData<Result<User>> {
        return object : NetworkBoundResource<User, User>(){
            override fun processResponse(response: User) = response
            override suspend fun createCall(): Response<User> = api.updateBookAddress(param)

        }.build().asLiveData()
    }

    override suspend fun changePassword(param: ChangePassParam): LiveData<Result<ObjectResponse<Account>>> {
        return object : NetworkBoundResource<ObjectResponse<Account>, ObjectResponse<Account>>(){
            override fun processResponse(response: ObjectResponse<Account>) = response
            override suspend fun createCall(): Response<ObjectResponse<Account>> = apiClothes.changePassword(param)

        }.build().asLiveData()
    }

    override suspend fun updateProfile(param: EditAccountParam): LiveData<Result<ObjectResponse<Account>>> {
        return object : NetworkBoundResource<ObjectResponse<Account>, ObjectResponse<Account>>(){
            override fun processResponse(response: ObjectResponse<Account>) = response
            override suspend fun createCall(): Response<ObjectResponse<Account>> =
                apiClothes.updateProfile(param)

        }.build().asLiveData()
    }

    override suspend fun getProfile(): LiveData<Result<User>> {
        return object : NetworkBoundResource<User, User>() {
            override fun processResponse(response: User) = response
            override suspend fun createCall(): Response<User> = api.getProfile()
        }.build().asLiveData()
    }


    override suspend fun getAllAddress(): LiveData<Result<ArrayList<ShoppingAddress>>> {
        return object :
            NetworkBoundResource<ArrayList<ShoppingAddress>, ArrayList<ShoppingAddress>>() {
            override fun processResponse(response: ArrayList<ShoppingAddress>) = response
            override suspend fun createCall(): Response<ArrayList<ShoppingAddress>> =
                apiClothes.getAllAddress()
        }.build().asLiveData()
    }

    override suspend fun addAddress(param: ShoppingAddress): LiveData<Result<ObjectResponse<Int>>> {
        return object : NetworkBoundResource<ObjectResponse<Int>, ObjectResponse<Int>>() {
            override fun processResponse(response: ObjectResponse<Int>) = response
            override suspend fun createCall(): Response<ObjectResponse<Int>> =
                apiClothes.addAddress(param)
        }.build().asLiveData()
    }

    override suspend fun updateAddress(param: ShoppingAddress): LiveData<Result<ObjectResponse<Int>>> {
        return object : NetworkBoundResource<ObjectResponse<Int>, ObjectResponse<Int>>() {
            override fun processResponse(response: ObjectResponse<Int>) = response
            override suspend fun createCall(): Response<ObjectResponse<Int>> =
                apiClothes.updateAddress(param)
        }.build().asLiveData()
    }

    override suspend fun deleteAddress(addressId: Int?): LiveData<Result<ObjectResponse<Int>>> {
        return object : NetworkBoundResource<ObjectResponse<Int>, ObjectResponse<Int>>() {
            override fun processResponse(response: ObjectResponse<Int>) = response
            override suspend fun createCall(): Response<ObjectResponse<Int>> =
                apiClothes.deleteAddress(addressId)
        }.build().asLiveData()
    }

    override suspend fun getPagingAllInvoices(
        pageSize: Int,
        pageNumber: Int,
        statusId: Int
    ): Listing<ItemViewModel> {
        val sourceFactory =
                object :
                        BaseDataSourceFactory<Invoice, ItemViewModel>(status = MutableLiveData()) {
                    override suspend fun createXCall(page: Int): Response<ListResponse<Invoice>> {
                        return apiClothes.getAllInvoices(pageSize, page, statusId)
                    }

                    override suspend fun handleXResponse(
                            items: ListResponse<Invoice>, firstLoad: Boolean
                    ): List<ItemViewModel> {
                        return items.results
                    }
                }

        val pagedLiveData = sourceFactory.toLiveData(pageSize = PAGE_SIZE)

        return Listing(
            result = pagedLiveData,
            status = sourceFactory.status,
            refresh = {
                sourceFactory.sourceLiveData.value?.invalidate()
            }
        )
    }

    override suspend fun getInvoiceDetail(invoiceId: Int?): LiveData<Result<ObjectResponse<InvoiceDetail>>> {
        return object :
            NetworkBoundResource<ObjectResponse<InvoiceDetail>, ObjectResponse<InvoiceDetail>>() {
            override fun processResponse(response: ObjectResponse<InvoiceDetail>) = response
            override suspend fun createCall(): Response<ObjectResponse<InvoiceDetail>> =
                apiClothes.getInvoiceDetail(invoiceId)
        }.build().asLiveData()
    }
}