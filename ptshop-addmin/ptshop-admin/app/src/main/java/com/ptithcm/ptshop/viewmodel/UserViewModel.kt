package com.ptithcm.ptshop.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.ptithcm.core.model.Account
import com.ptithcm.core.model.InvoiceDetail
import com.ptithcm.core.model.ShoppingAddress
import com.ptithcm.core.model.User
import com.ptithcm.core.model.wish.ObjectResponse
import com.ptithcm.core.param.ChangePassParam
import com.ptithcm.core.param.EditAccountParam
import com.ptithcm.core.param.UpdateAddressParam
import com.ptithcm.core.repository.UserRepository
import com.ptithcm.core.util.INIT_PAGE
import com.ptithcm.core.util.PAGE_SIZE
import com.ptithcm.core.vo.ItemViewModel
import com.ptithcm.core.vo.Result
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {
    val updateDetailLiveData = MediatorLiveData<ObjectResponse<Account>>()
    val changePasswordLiveData = MediatorLiveData<ObjectResponse<Account>>()
    val updateAddressBookLiveData = MediatorLiveData<User>()

    val allAddressLiveData = MediatorLiveData<ArrayList<ShoppingAddress>>()
    val updateAddressResultLiveData = MediatorLiveData<String>()

    val getProfileLiveData = MediatorLiveData<User>()
    val error = MutableLiveData<Pair<String, Int?>>()
    val isLoading = MutableLiveData<Boolean>()

    fun updateProfile(param: EditAccountParam) {
        viewModelScope.launch {
            updateDetailLiveData.addSource(repository.updateProfile(param)) {
                when (it) {
                    Result.Loading -> {
                        isLoading.value = true
                    }
                    is Result.Error -> {
                        error.value = Pair(it.message, it.code)
                    }
                    is Result.Success -> {
                        updateDetailLiveData.value = it.data
                    }
                }
            }
        }

    }

    fun updateBookAddress(param: UpdateAddressParam) {
        viewModelScope.launch {
            updateAddressBookLiveData.addSource(repository.updateBookAddress(param)) {
                when (it) {
                    Result.Loading -> {
                        isLoading.value = true
                    }
                    is Result.Error -> {
                        error.value = Pair(it.message, it.code)
                    }
                    is Result.Success -> {
                        updateAddressBookLiveData.value = it.data
                    }
                }
            }
        }
    }

    fun changePassword(param: ChangePassParam) {
        viewModelScope.launch {
            changePasswordLiveData.addSource(repository.changePassword(param)) {
                when (it) {
                    Result.Loading -> {
                        isLoading.value = true
                    }
                    is Result.Error -> {
                        error.value = Pair(it.message, it.code)
                    }
                    is Result.Success -> {
                        changePasswordLiveData.value = it.data
                    }
                }
            }
        }

    }

    fun getProfile() {
        viewModelScope.launch {
            getProfileLiveData.addSource(repository.getProfile()) {
                when (it) {
                    Result.Loading -> {
                        isLoading.value = true
                    }
                    is Result.Error -> {
                        error.value = Pair(it.message, it.code)
                    }
                    is Result.Success -> {
                        getProfileLiveData.value = it.data
                    }
                }
            }
        }
    }

    fun getAllAddress() {
        viewModelScope.launch {
            allAddressLiveData.addSource(repository.getAllAddress()) {
                when (it) {
                    is Result.Error -> {
                        isLoading.value = false
                        error.value = Pair(it.message, it.code)
                    }
                    is Result.Success -> {
                        isLoading.value = false
                        allAddressLiveData.value = it.data
                    }
                }
            }
        }
    }

    fun addAddress(param: ShoppingAddress) {
        viewModelScope.launch {
            updateAddressResultLiveData.addSource(repository.addAddress(param)) {
                when (it) {
                    is Result.Error -> {
                        error.value = Pair(it.message, it.code)
                    }
                    is Result.Success -> {
                        updateAddressResultLiveData.value = it.data?.message
                    }
                }
            }
        }
    }

    fun updateAddress(param: ShoppingAddress?) {
        param ?: return
        viewModelScope.launch {
            updateAddressResultLiveData.addSource(repository.updateAddress(param)) {
                when (it) {
                    is Result.Error -> {
                        error.value = Pair(it.message, it.code)
                    }
                    is Result.Success -> {
                        updateAddressResultLiveData.value = it.data?.message
                    }
                }
            }
        }
    }

    fun deleteAddress(addressId: Int?) {
        viewModelScope.launch {
            updateAddressResultLiveData.addSource(repository.deleteAddress(addressId)) {
                when (it) {
                    is Result.Error -> {
                        error.value = Pair(it.message, it.code)
                    }
                    is Result.Success -> {
                        updateAddressResultLiveData.value = it.data?.message
                    }
                }
            }
        }
    }

    val invoicesLiveData = MediatorLiveData<PagedList<ItemViewModel>>()
    val invoiceLoadStatusX = MutableLiveData<Result<ItemViewModel>>()
    val networkState = MutableLiveData<Boolean>()

    fun getPagingInvoices(
            statusId: Int = 1,
            pageSize: Int = PAGE_SIZE,
            pageNumber: Int = INIT_PAGE
    ) {
        viewModelScope.launch {
            val request =
                    repository.getPagingAllInvoices(pageSize, pageNumber, statusId)
            invoicesLiveData.addSource(request.result) {
                invoicesLiveData.value = it
            }
            invoicesLiveData.addSource(request.status) {
                invoiceLoadStatusX.value = it
                when (it) {
                    is Result.Loading -> {
                        networkState.value = true
                    }
                    is Result.Error -> {
                        networkState.value = false
                    }
                    is Result.Success -> {
                        networkState.value = false
                    }
                }
            }
        }
    }

    val invoiceDetailLiveData = MediatorLiveData<InvoiceDetail>()

    fun getInvoiceDetail(invoiceId: Int?) {
        viewModelScope.launch {
            invoiceDetailLiveData.addSource(repository.getInvoiceDetail(invoiceId)) {
                when (it) {
                    is Result.Error -> {
                        error.value = Pair(it.message, it.code)
                    }
                    is Result.Success -> {
                        invoiceDetailLiveData.value = it.data?.data
                    }
                }
            }
        }
    }
}