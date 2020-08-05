package com.ptithcm.core.repository

import androidx.lifecycle.LiveData
import com.ptithcm.core.model.Account
import com.ptithcm.core.model.wish.ObjectResponse
import com.ptithcm.core.param.LogInParam
import com.ptithcm.core.vo.Result

interface AuthRepository {
    suspend fun postLogIn(param: LogInParam): LiveData<Result<ObjectResponse<Account>>>
    suspend fun postSignUp(param: Account): LiveData<Result<ObjectResponse<Account>>>
    suspend fun logOut(): LiveData<Result<Void>>
    suspend fun requestForgotPassword(param: String): LiveData<Result<ObjectResponse<String>>>
}