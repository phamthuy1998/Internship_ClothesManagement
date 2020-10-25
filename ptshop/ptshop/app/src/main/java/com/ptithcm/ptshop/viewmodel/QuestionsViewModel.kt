package com.ptithcm.ptshop.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ptithcm.core.model.Question
import com.ptithcm.core.model.wish.ObjectResponse
import com.ptithcm.core.repository.QuestionRepository
import com.ptithcm.core.vo.Result
import kotlinx.coroutines.launch

class QuestionsViewModel(private val repository: QuestionRepository) : ViewModel() {

    val error = MutableLiveData<Pair<String, Int?>>()
    val networkState = MutableLiveData<Boolean>()

    val listQuestion = MediatorLiveData<ArrayList<Question>>()
    val addQuestionResult = MediatorLiveData<ObjectResponse<Int>>()
    val updateQuestionResult = MediatorLiveData<ObjectResponse<Int>>()
    val delQuestionResult = MediatorLiveData<ObjectResponse<Int>>()

    fun getQuestion(productID: Int) {
        viewModelScope.launch {
            listQuestion.addSource(repository.getQuestions(productID)) {
                when (it) {
                    is Result.Loading -> {
                        networkState.value = true
                    }
                    is Result.Error -> {
                        networkState.value = false
                        error.value = Pair(it.message, it.code)
                    }
                    is Result.Success -> {
                        networkState.value = false
                        listQuestion.value = it.data?.results
                    }
                }
            }
        }
    }

    fun addQuestion(question: Question) {
        viewModelScope.launch {
            listQuestion.addSource(repository.addQuestion(question)) {
                when (it) {
                    is Result.Loading -> {
                        networkState.value = true
                    }
                    is Result.Error -> {
                        networkState.value = false
                        error.value = Pair(it.message, it.code)
                    }
                    is Result.Success -> {
                        networkState.value = false
                        addQuestionResult.value = it.data
                    }
                }
            }
        }
    }

    fun updateQuestion(question: Question) {
        viewModelScope.launch {
            listQuestion.addSource(repository.updateQuestion(question)) {
                when (it) {
                    is Result.Loading -> {
                        networkState.value = true
                    }
                    is Result.Error -> {
                        networkState.value = false
                        error.value = Pair(it.message, it.code)
                    }
                    is Result.Success -> {
                        networkState.value = false
                        updateQuestionResult.value = it.data
                    }
                }
            }
        }
    }

    fun delQuestion(questionID: Int, isSubQuestion: Int) {
        viewModelScope.launch {
            listQuestion.addSource(repository.delQuestion(questionID,isSubQuestion)) {
                when (it) {
                    is Result.Loading -> {
                        networkState.value = true
                    }
                    is Result.Error -> {
                        networkState.value = false
                        error.value = Pair(it.message, it.code)
                    }
                    is Result.Success -> {
                        networkState.value = false
                        delQuestionResult.value = it.data
                    }
                }
            }
        }
    }
}