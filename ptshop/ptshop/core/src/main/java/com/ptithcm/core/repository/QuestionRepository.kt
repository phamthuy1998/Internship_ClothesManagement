package com.ptithcm.core.repository

import androidx.lifecycle.LiveData
import com.ptithcm.core.model.Product
import com.ptithcm.core.model.Provider
import com.ptithcm.core.model.Question
import com.ptithcm.core.model.ShoppingAddress
import com.ptithcm.core.model.wish.ObjectResponse
import com.ptithcm.core.vo.ItemViewModel
import com.ptithcm.core.vo.ListResponse
import com.ptithcm.core.vo.Listing
import com.ptithcm.core.vo.Result

interface QuestionRepository {
    suspend fun getQuestions(productID: Int): LiveData<Result<ListResponse<Question>>>
    suspend fun addQuestion(question: Question): LiveData<Result<ObjectResponse<Int>>>
    suspend fun updateQuestion(question: Question): LiveData<Result<ObjectResponse<Int>>>
    suspend fun delQuestion(questionID: Int, isSubQuestion: Int): LiveData<Result<ObjectResponse<Int>>>
    suspend fun getQuestionCount(questionID: Int): LiveData<Result<ObjectResponse<Int>>>
}