package com.n16dccn159.core.repository.impl

import androidx.lifecycle.LiveData
import com.n16dccn159.core.api.ApiClothesService
import com.n16dccn159.core.api.ApiService
import com.n16dccn159.core.data.remote.NetworkBoundResource
import com.n16dccn159.core.model.Account
import com.n16dccn159.core.model.wish.ObjectResponse
import com.n16dccn159.core.param.LogInParam
import com.n16dccn159.core.repository.AuthRepository
import com.n16dccn159.core.vo.Result
import retrofit2.Response


class AuthRepositoryImpl(val api: ApiService, val apiClothes: ApiClothesService) : AuthRepository {
    override suspend fun requestForgotPassword(param: String): LiveData<Result<ObjectResponse<String>>> {
        return object : NetworkBoundResource<ObjectResponse<String>, ObjectResponse<String>>() {
            override fun processResponse(response: ObjectResponse<String>): ObjectResponse<String>? =
                response

            override suspend fun createCall(): Response<ObjectResponse<String>> =
                apiClothes.forgotPassword(param)
        }.build().asLiveData()
    }

    override suspend fun logOut(): LiveData<Result<Void>> {
        return object : NetworkBoundResource<Void, Void>() {

            override fun processResponse(response: Void): Void? {
                return null
            }

            override suspend fun createCall(): Response<Void> = apiClothes.logOut()

        }.build().asLiveData()
    }


    override suspend fun postSignUp(param: Account): LiveData<Result<ObjectResponse<Account>>> {
        return object : NetworkBoundResource<ObjectResponse<Account>, ObjectResponse<Account>>() {
            override fun processResponse(response: ObjectResponse<Account>): ObjectResponse<Account>? = response

            override suspend fun createCall(): Response<ObjectResponse<Account>> = apiClothes.signUp(param)
        }.build().asLiveData()
    }

    override suspend fun postLogIn(param: LogInParam): LiveData<Result<ObjectResponse<Account>>> {
        return object : NetworkBoundResource<ObjectResponse<Account>, ObjectResponse<Account>>() {
            override fun processResponse(response: ObjectResponse<Account>): ObjectResponse<Account>? =
                response

            override suspend fun createCall(): Response<ObjectResponse<Account>> =
                apiClothes.signIn(param)

        }.build().asLiveData()
    }


}