package com.sg.core.repository

import androidx.lifecycle.LiveData
import com.sg.core.model.Account
import com.sg.core.model.User
import com.sg.core.model.wish.ObjectResponse
import com.sg.core.param.ChangePassParam
import com.sg.core.param.EditAccountParam
import com.sg.core.param.UpdateAddressParam
import com.sg.core.vo.Result


interface UserRepository {
    suspend fun updateProfile(param: EditAccountParam): LiveData<Result<ObjectResponse<Account>>>
    suspend fun getProfile(): LiveData<Result<User>>
    suspend fun changePassword(param: ChangePassParam): LiveData<Result<ObjectResponse<Account>>>
    suspend fun updateBookAddress(param: UpdateAddressParam): LiveData<Result<User>>
}