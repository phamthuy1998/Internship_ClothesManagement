package com.ptithcm.core.repository

import androidx.lifecycle.LiveData
import com.ptithcm.core.model.Account
import com.ptithcm.core.model.ShoppingAddress
import com.ptithcm.core.model.User
import com.ptithcm.core.model.wish.ObjectResponse
import com.ptithcm.core.param.ChangePassParam
import com.ptithcm.core.param.EditAccountParam
import com.ptithcm.core.param.UpdateAddressParam
import com.ptithcm.core.vo.ItemViewModel
import com.ptithcm.core.vo.Listing
import com.ptithcm.core.vo.Result


interface UserRepository {
    suspend fun updateProfile(param: EditAccountParam): LiveData<Result<ObjectResponse<Account>>>
    suspend fun getProfile(): LiveData<Result<User>>
    suspend fun changePassword(param: ChangePassParam): LiveData<Result<ObjectResponse<Account>>>
    suspend fun updateBookAddress(param: UpdateAddressParam): LiveData<Result<User>>
    suspend fun getAllAddress(): LiveData<Result<ArrayList<ShoppingAddress>>>
    suspend fun addAddress(param: ShoppingAddress): LiveData<Result<ObjectResponse<Int>>>
    suspend fun updateAddress(param: ShoppingAddress): LiveData<Result<ObjectResponse<Int>>>
    suspend fun deleteAddress(addressId: Int?): LiveData<Result<ObjectResponse<Int>>>
    suspend fun getPagingAllInvoices(
        pageSize: Int,
        pageNumber: Int,
        statusId: Int
    ): Listing<ItemViewModel>
}