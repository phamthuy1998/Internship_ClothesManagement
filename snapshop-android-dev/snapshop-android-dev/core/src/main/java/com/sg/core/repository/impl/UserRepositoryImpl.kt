package com.sg.core.repository.impl

import androidx.lifecycle.LiveData
import com.sg.core.api.ApiService
import com.sg.core.data.remote.NetworkBoundResource
import com.sg.core.model.User
import com.sg.core.param.ChangePasswordParam
import com.sg.core.param.UpdateAddressParam
import com.sg.core.param.UpdateDetailParam
import com.sg.core.repository.UserRepository
import com.sg.core.vo.Result
import retrofit2.Response

class UserRepositoryImpl (val api: ApiService): UserRepository {
    override suspend fun updateBookAddress(param: UpdateAddressParam): LiveData<Result<User>> {
        return object : NetworkBoundResource<User, User>(){
            override fun processResponse(response: User) = response
            override suspend fun createCall(): Response<User> = api.updateBookAddress(param)

        }.build().asLiveData()
    }

    override suspend fun changePassword(param: ChangePasswordParam): LiveData<Result<User>> {
        return object : NetworkBoundResource<User, User>(){
            override fun processResponse(response: User) = response
            override suspend fun createCall(): Response<User> = api.changePassword(param)

        }.build().asLiveData()
    }

    override suspend fun updateProfile(param: UpdateDetailParam): LiveData<Result<User>> {
        return object : NetworkBoundResource<User, User>(){
            override fun processResponse(response: User) = response
            override suspend fun createCall(): Response<User> = api.updateProfile(param)

        }.build().asLiveData()
    }

    override suspend fun getProfile(): LiveData<Result<User>> {
        return object : NetworkBoundResource<User, User>(){
            override fun processResponse(response: User) = response
            override suspend fun createCall(): Response<User> = api.getProfile()

        }.build().asLiveData()
    }
}