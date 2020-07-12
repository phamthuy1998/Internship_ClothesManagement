package com.sg.core.repository.impl

import androidx.lifecycle.LiveData
import com.sg.core.api.ApiClothesService
import com.sg.core.api.ApiService
import com.sg.core.data.remote.NetworkBoundResource
import com.sg.core.model.Account
import com.sg.core.model.Profile
import com.sg.core.model.wish.ObjectResponse
import com.sg.core.param.ForgotPasswordParam
import com.sg.core.param.LogInParam
import com.sg.core.param.RegisterParam
import com.sg.core.repository.AuthRepository
import com.sg.core.vo.ApiResponse
import com.sg.core.vo.Result
import retrofit2.Response


class AuthRepositoryImpl(val api: ApiService, val apiClothes: ApiClothesService) : AuthRepository {
    override suspend fun postForgotPassword(param: ForgotPasswordParam): LiveData<Result<String>> {
        return object : NetworkBoundResource<ApiResponse<String>, String>() {
            override fun processResponse(response: ApiResponse<String>): String? = response.detail
            override suspend fun createCall(): Response<ApiResponse<String>> = api.forgotPassword(param)
        }.build().asLiveData()
    }

    override suspend fun logOut(): LiveData<Result<Void>> {
        return object : NetworkBoundResource<Void, Void>() {

            override fun processResponse(response: Void): Void? {
                return null
            }

            override suspend fun createCall(): Response<Void>  = api.logOut()

        }.build().asLiveData()
    }


    override suspend fun postSignUp(param: RegisterParam): LiveData<Result<Profile>> {
        return object : NetworkBoundResource<Profile, Profile>() {
            override fun processResponse(response: Profile): Profile? = response
            override suspend fun createCall(): Response<Profile> = api.signUp(param)

        }.build().asLiveData()
    }

    override suspend fun postLogIn(param: LogInParam): LiveData<Result<ObjectResponse<Account>>> {
        return object : NetworkBoundResource<ObjectResponse<Account>, ObjectResponse<Account>>() {
            override fun processResponse(response: ObjectResponse<Account>): ObjectResponse<Account>? = response

            override suspend fun createCall(): Response<ObjectResponse<Account>> = apiClothes.signIn(param)

        }.build().asLiveData()
    }


}