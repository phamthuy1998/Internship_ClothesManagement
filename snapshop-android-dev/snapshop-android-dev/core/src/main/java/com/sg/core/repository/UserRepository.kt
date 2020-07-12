package com.sg.core.repository

import androidx.lifecycle.LiveData
import com.sg.core.model.User
import com.sg.core.param.ChangePasswordParam
import com.sg.core.param.UpdateAddressParam
import com.sg.core.param.UpdateDetailParam
import com.sg.core.vo.Result


interface UserRepository {
    suspend fun updateProfile(param: UpdateDetailParam): LiveData<Result<User>>
    suspend fun getProfile(): LiveData<Result<User>>
    suspend fun changePassword(param: ChangePasswordParam): LiveData<Result<User>>
    suspend fun updateBookAddress(param: UpdateAddressParam): LiveData<Result<User>>
}