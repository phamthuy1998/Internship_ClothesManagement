package com.ptithcm.core.model

data class Question(
    var accountID: Int? = null,
    var dateComment: String? = null,
    var dateEdit: Any? = null,
    var parentQuestionID: Int? = null,
    var question: String? = null,
    var questionID: Int? = null,
    var roleId: Int? = null,
    var subQuestions: ArrayList<Question>? = null,
    var username: String? = null,
    var productID: Int? = null,

    @Transient
    var showReplies: Boolean = false,
    @Transient
    var showTextShowReply: Boolean = subQuestions?.size?:0>0 && !showReplies
){
    fun isHideQuestions() = showReplies && (subQuestions?.size?:0)>0
}