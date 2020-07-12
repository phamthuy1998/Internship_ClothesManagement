package com.sg.core.repository

import androidx.lifecycle.LiveData
import com.sg.core.model.Brand
import com.sg.core.param.BrandParam
import com.sg.core.vo.ListResponse
import com.sg.core.vo.Listing
import com.sg.core.vo.Result

interface DesignerRepository {

    suspend fun getPopularBrandsPaging(param: BrandParam): Listing<Brand>

    suspend fun getPoupularBrands(param: BrandParam): LiveData<Result<ListResponse<Brand>>>
}