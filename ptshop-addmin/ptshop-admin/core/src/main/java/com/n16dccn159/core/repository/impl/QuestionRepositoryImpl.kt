package com.n16dccn159.core.repository.impl

import androidx.lifecycle.LiveData
import com.n16dccn159.core.api.ApiClothesService
import com.n16dccn159.core.data.remote.NetworkBoundResource
import com.n16dccn159.core.model.Question
import com.n16dccn159.core.model.ShoppingAddress
import com.n16dccn159.core.model.wish.ObjectResponse
import com.n16dccn159.core.repository.QuestionRepository
import com.n16dccn159.core.vo.ListResponse
import com.n16dccn159.core.vo.Result
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

    override suspend fun delQuestion(questionID: Int,isSubQuestion: Int): LiveData<Result<ObjectResponse<Int>>> {
        return object : NetworkBoundResource<ObjectResponse<Int>, ObjectResponse<Int>>() {
            override fun processResponse(response: ObjectResponse<Int>) = response
            override suspend fun createCall(): Response<ObjectResponse<Int>> =
                apiClothes.delQuestion(questionID,isSubQuestion)
        }.build().asLiveData()
    }

    override suspend fun getQuestionCount(questionID: Int): LiveData<Result<ObjectResponse<Int>>> {
        return object : NetworkBoundResource<ObjectResponse<Int>, ObjectResponse<Int>>() {
            override fun processResponse(response: ObjectResponse<Int>) = response
            override suspend fun createCall(): Response<ObjectResponse<Int>> =
                apiClothes.getQuestionCount(questionID)
        }.build().asLiveData()
    }

}