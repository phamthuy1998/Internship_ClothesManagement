package com.ptithcm.core.param

import com.ptithcm.core.model.Filter

data class ProductsOfCategoryRequestParam(
    var categoryID: Int?,
    var pageSize: Int? = 20,
    var pageNumber: Int? = 1,
    var accountId: Int?,
    var filter: Filter? = Filter()
)