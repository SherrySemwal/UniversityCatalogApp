package com.sherry.universitycatalogapp.data.remote

import com.sherry.universitycatalogapp.data.model.UniversityResponse


interface UniversityRemoteDataSource {
    suspend fun getUniversityData(): List<UniversityResponse>?
}