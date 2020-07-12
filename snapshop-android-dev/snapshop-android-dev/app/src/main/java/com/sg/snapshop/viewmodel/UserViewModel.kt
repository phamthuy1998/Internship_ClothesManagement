package com.sg.snapshop.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sg.core.model.User
import com.sg.core.param.ChangePasswordParam
import com.sg.core.param.UpdateAddressParam
import com.sg.core.param.UpdateDetailParam
import com.sg.core.repository.UserRepository
import com.sg.core.vo.Result
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {
    val updateDetailLiveData = MediatorLiveData<User>()
    val changePasswordLiveData = MediatorLiveData<User>()
    val updateAddressBookLiveData = MediatorLiveData<User>()
    val getProfileLiveData = MediatorLiveData<User>()
    val error = MutableLiveData<Pair<String, Int?>>()

    fun updateProfile(param: UpdateDetailParam) {
        viewModelScope.launch {
            updateDetailLiveData.addSource(repository.updateProfile(param)) {
                when (it) {
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

    fun changePassword(param: ChangePasswordParam) {
        viewModelScope.launch {
            changePasswordLiveData.addSource(repository.changePassword(param)) {
                when (it) {
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
    fun getProfile(){
        viewModelScope.launch {
            getProfileLiveData.addSource(repository.getProfile()) {
                when (it) {
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
}