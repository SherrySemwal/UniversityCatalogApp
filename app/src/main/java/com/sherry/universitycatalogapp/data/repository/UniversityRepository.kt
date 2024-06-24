package com.sherry.universitycatalogapp.data.repository

import com.sherry.universitycatalogapp.data.ApiResult
import com.sherry.universitycatalogapp.data.model.University

interface UniversityRepository {
    suspend fun getAllUniversity(): ApiResult<List<University>?>
}