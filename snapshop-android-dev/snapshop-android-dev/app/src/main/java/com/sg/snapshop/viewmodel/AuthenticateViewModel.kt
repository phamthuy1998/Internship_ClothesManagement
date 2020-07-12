package com.sg.snapshop.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sg.core.model.Profile
import com.sg.core.param.ForgotPasswordParam
import com.sg.core.param.LogInParam
import com.sg.core.param.RegisterParam
import com.sg.core.repository.AuthRepository
import com.sg.core.vo.Result
import kotlinx.coroutines.launch

class AuthenticateViewModel(private val repository: AuthRepository) : ViewModel() {
    val logInLiveData = MediatorLiveData<Profile>()
    val signUpLiveData = MediatorLiveData<Profile>()
    val logOutLiveData = MediatorLiveData<String>()
    val forgotPasswordLiveData = MediatorLiveData<Any>()
    val error = MutableLiveData<Pair<String, Int?>>()

    fun logIn(param: LogInParam) {
        viewModelScope.launch {
            logInLiveData.addSource(repository.postLogIn(param)) {
                when (it) {
                    is Result.Error -> {
                        error.value = Pair(it.message, it.code)
                    }
                    is Result.Success -> {
                        logInLiveData.value = it.data
                    }
                }
            }
        }
    }

    fun signUp(param: RegisterParam) {
        viewModelScope.launch {
            signUpLiveData.addSource(repository.postSignUp(param)) {
                when (it) {
                    is Result.Error -> {
                        error.value = Pair(it.message, it.code)
                    }
                    is Result.Success -> {
                        signUpLiveData.value = it.data
                    }
                }
            }
        }
    }

    fun logOut() {
        viewModelScope.launch {
            logOutLiveData.addSource(repository.logOut()) {
                when (it) {
                    is Result.Error -> {
                        error.value = Pair(it.message, it.code)
                    }
                    is Result.Success -> {
                        logOutLiveData.value = it.message
                    }
                }
            }
        }
    }

    fun forgotPassword(param: ForgotPasswordParam) {
        viewModelScope.launch {
            forgotPasswordLiveData.addSource(repository.postForgotPassword(param)) {
                when (it) {
                    is Result.Error -> {
                        error.value = Pair(it.message, it.code)
                    }
                    is Result.Success -> {
                        forgotPasswordLiveData.value = it.data
                    }
                }
            }
        }
    }
}