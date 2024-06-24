package com.sherry.universitycatalogapp.data

/**
 * Result state for response
 */
sealed interface ApiResult<out T> {
    data object NetworkError : ApiResult<Nothing>
    data class OnSuccess<T>(val data: T) : ApiResult<T>
    data class OnFailure(val error: String?) : ApiResult<Nothing>
}