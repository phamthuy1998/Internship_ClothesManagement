package com.sg.core.repository

import androidx.lifecycle.LiveData
import com.sg.core.model.Account
import com.sg.core.model.Profile
import com.sg.core.model.wish.ObjectResponse
import com.sg.core.param.ForgotPasswordParam
import com.sg.core.param.LogInParam
import com.sg.core.param.RegisterParam
import com.sg.core.vo.Result

interface AuthRepository {
    suspend fun postLogIn(param: LogInParam): LiveData<Result<ObjectResponse<Account>>>
    suspend fun postSignUp(param: Account): LiveData<Result<ObjectResponse<Account>>>
    suspend fun logOut() : LiveData<Result<Void>>
    suspend fun postForgotPassword(param: ForgotPasswordParam):LiveData<Result<ObjectResponse<String>>>
}