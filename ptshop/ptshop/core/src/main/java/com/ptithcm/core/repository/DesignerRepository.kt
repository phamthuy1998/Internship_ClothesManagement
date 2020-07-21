package com.ptithcm.core.repository

import androidx.lifecycle.LiveData
import com.ptithcm.core.model.Brand
import com.ptithcm.core.param.BrandParam
import com.ptithcm.core.vo.ListResponse
import com.ptithcm.core.vo.Listing
import com.ptithcm.core.vo.Result

interface DesignerRepository {

    suspend fun getPopularBrandsPaging(param: BrandParam): Listing<Brand>

    suspend fun getPoupularBrands(param: BrandParam): LiveData<Result<ListResponse<Brand>>>
}