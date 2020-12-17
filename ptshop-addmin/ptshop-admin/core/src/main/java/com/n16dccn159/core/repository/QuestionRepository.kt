package com.n16dccn159.core.repository

import androidx.lifecycle.LiveData
import com.n16dccn159.core.model.Product
import com.n16dccn159.core.model.Provider
import com.n16dccn159.core.model.Question
import com.n16dccn159.core.model.ShoppingAddress
import com.n16dccn159.core.model.wish.ObjectResponse
import com.n16dccn159.core.vo.ItemViewModel
import com.n16dccn159.core.vo.ListResponse
import com.n16dccn159.core.vo.Listing
import com.n16dccn159.core.vo.Result

interface QuestionRepository {
    suspend fun getQuestions(productID: Int): LiveData<Result<ListResponse<Question>>>
    suspend fun addQuestion(question: Question): LiveData<Result<ObjectResponse<Int>>>
    suspend fun updateQuestion(question: Question): LiveData<Result<ObjectResponse<Int>>>
    suspend fun delQuestion(questionID: Int, isSubQuestion: Int): LiveData<Result<ObjectResponse<Int>>>
    suspend fun getQuestionCount(questionID: Int): LiveData<Result<ObjectResponse<Int>>>
}