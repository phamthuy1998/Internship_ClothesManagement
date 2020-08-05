package com.ptithcm.ptshop.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ptithcm.core.model.Account
import com.ptithcm.core.model.ShoppingAddress
import com.ptithcm.core.model.User
import com.ptithcm.core.model.wish.ObjectResponse
import com.ptithcm.core.param.ChangePassParam
import com.ptithcm.core.param.EditAccountParam
import com.ptithcm.core.param.UpdateAddressParam
import com.ptithcm.core.repository.UserRepository
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
                    Result.Loading -> {
                        isLoading.value = true
                    }
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

    fun updateAddress(param: ShoppingAddress) {
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
}