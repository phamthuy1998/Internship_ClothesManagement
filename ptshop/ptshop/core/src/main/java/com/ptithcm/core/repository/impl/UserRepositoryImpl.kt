package com.ptithcm.core.repository.impl

import androidx.lifecycle.LiveData
import com.ptithcm.core.api.ApiClothesService
import com.ptithcm.core.api.ApiService
import com.ptithcm.core.data.remote.NetworkBoundResource
import com.ptithcm.core.model.Account
import com.ptithcm.core.model.User
import com.ptithcm.core.model.wish.ObjectResponse
import com.ptithcm.core.param.ChangePassParam
import com.ptithcm.core.param.EditAccountParam
import com.ptithcm.core.param.UpdateAddressParam
import com.ptithcm.core.repository.UserRepository
import com.ptithcm.core.vo.Result
import retrofit2.Response

class UserRepositoryImpl (val api: ApiService, val apiClothes: ApiClothesService): UserRepository {
    override suspend fun updateBookAddress(param: UpdateAddressParam): LiveData<Result<User>> {
        return object : NetworkBoundResource<User, User>(){
            override fun processResponse(response: User) = response
            override suspend fun createCall(): Response<User> = api.updateBookAddress(param)

        }.build().asLiveData()
    }

    override suspend fun changePassword(param: ChangePassParam): LiveData<Result<ObjectResponse<Account>>> {
        return object : NetworkBoundResource<ObjectResponse<Account>, ObjectResponse<Account>>(){
            override fun processResponse(response: ObjectResponse<Account>) = response
            override suspend fun createCall(): Response<ObjectResponse<Account>> = apiClothes.changePassword(param)

        }.build().asLiveData()
    }

    override suspend fun updateProfile(param: EditAccountParam): LiveData<Result<ObjectResponse<Account>>> {
        return object : NetworkBoundResource<ObjectResponse<Account>, ObjectResponse<Account>>(){
            override fun processResponse(response: ObjectResponse<Account>) = response
            override suspend fun createCall(): Response<ObjectResponse<Account>> = apiClothes.updateProfile(param)

        }.build().asLiveData()
    }

    override suspend fun getProfile(): LiveData<Result<User>> {
        return object : NetworkBoundResource<User, User>(){
            override fun processResponse(response: User) = response
            override suspend fun createCall(): Response<User> = api.getProfile()

        }.build().asLiveData()
    }
}