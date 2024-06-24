package com.sherry.universitycatalogapp.data.repository

import com.sherry.universitycatalogapp.data.ApiResult
import com.sherry.universitycatalogapp.data.model.University
import com.sherry.universitycatalogapp.data.model.UniversityResponse
import com.sherry.universitycatalogapp.data.model.toUniversityData
import com.sherry.universitycatalogapp.data.remote.UniversityRemoteDataSource
import com.sherry.universitycatalogapp.utils.getApiResult
import com.sumup.challenge.toastcatalog.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UniversityRepositoryImpl @Inject constructor(
    private val universityRemoteDataSource: UniversityRemoteDataSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : UniversityRepository {
    override suspend fun getAllUniversity(): ApiResult<List<University>?> = withContext(dispatcher) {
        getApiResult {
            universityRemoteDataSource.getUniversityData()?.map { data ->
             data.toUniversityData()
            }
        }
    }
}
