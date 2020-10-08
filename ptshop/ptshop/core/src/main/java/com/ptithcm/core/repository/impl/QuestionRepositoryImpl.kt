package com.ptithcm.core.repository.impl

import androidx.lifecycle.LiveData
import com.ptithcm.core.api.ApiClothesService
import com.ptithcm.core.data.remote.NetworkBoundResource
import com.ptithcm.core.model.Question
import com.ptithcm.core.model.ShoppingAddress
import com.ptithcm.core.model.wish.ObjectResponse
import com.ptithcm.core.repository.QuestionRepository
import com.ptithcm.core.vo.ListResponse
import com.ptithcm.core.vo.Result
import retrofit2.Response

class QuestionRepositoryImpl(val apiClothes: ApiClothesService) : QuestionRepository{
    override suspend fun getQuestions(productID:Int):LiveData<Result<ListResponse<Question>>>{
        return object :
            NetworkBoundResource<ListResponse<Question>, ListResponse<Question>>() {
            override fun processResponse(response: ListResponse<Question>) = response
            override suspend fun createCall(): Response<ListResponse<Question>> =
                apiClothes.getAllQuestion(productId = productID)
        }.build().asLiveData()
    }

    override suspend fun addQuestion(question: Question): LiveData<Result<ObjectResponse<Int>>> {
        return object : NetworkBoundResource<ObjectResponse<Int>, ObjectResponse<Int>>() {
            override fun processResponse(response: ObjectResponse<Int>) = response
            override suspend fun createCall(): Response<ObjectResponse<Int>> =
                apiClothes.addQuestion(question)
        }.build().asLiveData()
    }

    override suspend fun updateQuestion(question: Question): LiveData<Result<ObjectResponse<Int>>> {
        return object : NetworkBoundResource<ObjectResponse<Int>, ObjectResponse<Int>>() {
            override fun processResponse(response: ObjectResponse<Int>) = response
            override suspend fun createCall(): Response<ObjectResponse<Int>> =
                apiClothes.updateQuestion(question)
        }.build().asLiveData()
    }

    override suspend fun delQuestion(questionID: Int): LiveData<Result<ObjectResponse<Int>>> {
        return object : NetworkBoundResource<ObjectResponse<Int>, ObjectResponse<Int>>() {
            override fun processResponse(response: ObjectResponse<Int>) = response
            override suspend fun createCall(): Response<ObjectResponse<Int>> =
                apiClothes.delQuestion(questionID)
        }.build().asLiveData()
    }

}