package com.sherry.universitycatalogapp.data.remote

import com.sherry.universitycatalogapp.data.model.UniversityResponse
import com.sherry.universitycatalogapp.data.service.ApiInterface
import com.sherry.universitycatalogapp.utils.getResponse
import javax.inject.Inject

class UniversityRemoteDataSourceImpl @Inject constructor(
    private val apiInterface: ApiInterface,
) : UniversityRemoteDataSource {
    override suspend fun getUniversityData(): List<UniversityResponse>? =
        apiInterface.getAllUniversity().getResponse()
}