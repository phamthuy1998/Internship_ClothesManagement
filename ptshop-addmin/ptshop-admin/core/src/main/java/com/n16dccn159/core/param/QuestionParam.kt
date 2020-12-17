package com.n16dccn159.core.param

import com.n16dccn159.core.model.Filter

data class QuestionParam (
    var pageSize: Int? = 20,
    var pageNumber: Int? = 1,
    var accountId: Int?,
    var filter: Filter? = Filter()
)