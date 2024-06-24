package com.sherry.universitycatalogapp.data.service

import com.sherry.universitycatalogapp.data.model.UniversityResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.QueryName


interface ApiInterface {
    @GET("/search?country=Germany")
    suspend fun getAllUniversity(): Response<List<UniversityResponse>>
}

