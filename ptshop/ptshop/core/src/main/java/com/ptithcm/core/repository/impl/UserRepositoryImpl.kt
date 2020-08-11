package com.ptithcm.core.repository.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.toLiveData
import com.ptithcm.core.api.ApiClothesService
import com.ptithcm.core.api.ApiService
import com.ptithcm.core.data.remote.BaseDataSourceFactory
import com.ptithcm.core.data.remote.NetworkBoundResource
import com.ptithcm.core.model.Account
import com.ptithcm.core.model.Invoice
import com.ptithcm.core.model.ShoppingAddress
import com.ptithcm.core.model.User
import com.ptithcm.core.model.wish.ObjectResponse
import com.ptithcm.core.param.ChangePassParam
import com.ptithcm.core.param.EditAccountParam
import com.ptithcm.core.param.UpdateAddressParam
import com.ptithcm.core.repository.UserRepository
import com.ptithcm.core.util.PAGE_SIZE
import com.ptithcm.core.vo.ItemViewModel
import com.ptithcm.core.vo.ListResponse
import com.ptithcm.core.vo.Listing
import com.ptithcm.core.vo.Result
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
        statusId: Int,
        accountId: Int
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
}