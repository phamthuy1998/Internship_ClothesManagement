package com.n16dccn159.core.param

import com.n16dccn159.core.model.Filter

data class ProductsOfProviderRequestParam(
    var providerID: Int?,
    var pageSize: Int? = 20,
    var pageNumber: Int? = 1,
    var accountId: Int?,
    var filter: Filter? = Filter()
)