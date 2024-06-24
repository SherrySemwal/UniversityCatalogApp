package com.sherry.universitycatalogapp.data.model

import kotlinx.serialization.SerialName


data class UniversityResponse(
    @SerialName("name") var name:String,
    @SerialName("alpha_two_code") var alphaTwoCode:String,
    @SerialName("web_pages") var webPages: List<String>,
    @SerialName("country") var country:String,
    @SerialName("domains") var domains:List<String>,
    @SerialName("state-province") var stateProvince:String
)

fun UniversityResponse.toUniversityData() = University(
    name = name,
    country = country,
    webpageUrl = webPages[0]
)
