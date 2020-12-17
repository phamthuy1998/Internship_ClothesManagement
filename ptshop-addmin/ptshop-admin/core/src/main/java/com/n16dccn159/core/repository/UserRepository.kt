package com.n16dccn159.core.repository

import androidx.lifecycle.LiveData
import com.n16dccn159.core.model.Account
import com.n16dccn159.core.model.InvoiceDetail
import com.n16dccn159.core.model.ShoppingAddress
import com.n16dccn159.core.model.User
import com.n16dccn159.core.model.wish.ObjectResponse
import com.n16dccn159.core.param.ChangePassParam
import com.n16dccn159.core.param.EditAccountParam
import com.n16dccn159.core.param.UpdateAddressParam
import com.n16dccn159.core.vo.ItemViewModel
import com.n16dccn159.core.vo.Listing
import com.n16dccn159.core.vo.Result


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

    suspend fun getInvoiceDetail(invoiceId: Int?): LiveData<Result<ObjectResponse<InvoiceDetail>>>
}