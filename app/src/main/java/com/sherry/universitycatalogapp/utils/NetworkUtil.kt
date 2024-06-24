package com.sherry.universitycatalogapp.utils

import com.sherry.universitycatalogapp.data.ApiResult
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

suspend fun <T> getApiResult(response: suspend () -> T): ApiResult<T> {
    return try {
        ApiResult.OnSuccess(response())
    } catch (exception: Exception) {
        when (exception) {
            is IOException -> ApiResult.NetworkError
            else -> ApiResult.OnFailure(exception.message)
        }
    }
}

fun <T> Response<T>.getResponse(): T? {
    if (!isSuccessful) throw HttpException(this)
    return body()
}