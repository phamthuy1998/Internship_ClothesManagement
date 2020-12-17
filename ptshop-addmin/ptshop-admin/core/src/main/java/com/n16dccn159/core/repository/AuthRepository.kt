package com.n16dccn159.core.repository

import androidx.lifecycle.LiveData
import com.n16dccn159.core.model.Account
import com.n16dccn159.core.model.wish.ObjectResponse
import com.n16dccn159.core.param.LogInParam
import com.n16dccn159.core.vo.Result

interface AuthRepository {
    suspend fun postLogIn(param: LogInParam): LiveData<Result<ObjectResponse<Account>>>
    suspend fun postSignUp(param: Account): LiveData<Result<ObjectResponse<Account>>>
    suspend fun logOut(): LiveData<Result<Void>>
    suspend fun requestForgotPassword(param: String): LiveData<Result<ObjectResponse<String>>>
}