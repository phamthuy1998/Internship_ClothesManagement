package com.n16dccn159.core.repository

import androidx.lifecycle.LiveData
import com.n16dccn159.core.model.Brand
import com.n16dccn159.core.param.BrandParam
import com.n16dccn159.core.vo.ListResponse
import com.n16dccn159.core.vo.Listing
import com.n16dccn159.core.vo.Result

interface DesignerRepository {

    suspend fun getPopularBrandsPaging(param: BrandParam): Listing<Brand>

    suspend fun getPoupularBrands(param: BrandParam): LiveData<Result<ListResponse<Brand>>>
}