package com.android.boilerplate.core.base

/**
 * Created by Abdul Rahman on 21/08/2024
 */
sealed class Resource<T>(
    val isLoading: Boolean? = null,
    val statusCode: Int? = null,
    val message: String? = null,
    val data: T? = null
) {
    class Loading<T>(isLoading: Boolean? = false) : Resource<T>(isLoading = isLoading)

    class Error<T>(statusCode: Int?, message: String?) :
        Resource<T>(statusCode = statusCode, message = message)

    class Success<T>(data: T?) : Resource<T>(data = data)
}