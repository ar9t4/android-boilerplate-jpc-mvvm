package com.android.boilerplate.core.base

import android.content.Context
import com.android.boilerplate.R
import com.android.boilerplate.core.di.ActivityContextProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

/**
 * Created by Abdul Rahman on 21/08/2024
 */
open class BaseRepository(
    private val activityContextProvider: ActivityContextProvider,
) {
    private val context: Context get() = activityContextProvider.getActivityContext()

    protected suspend fun <T> execute(call: suspend () -> Response<T>): Flow<Resource<T>> {
        return flow {
            emit(value = Resource.Loading(isLoading = true))
            try {
                val response = call()
                if (response.isSuccessful) {
                    val parsedResponse = response.body()
                    emit(value = Resource.Success(data = parsedResponse))
                } else {
                    emit(value = handleError(response = response))
                }
            } catch (exception: Exception) {
                emit(value = handleException(exception = exception))
            }
        }
    }

    private fun <T> handleError(response: Response<T>): Resource<T> {
        return when (val statusCode = response.code()) {
            400 -> throwError(statusCode, context.getString(R.string.bad_request_error))
            401 -> throwError(statusCode, context.getString(R.string.unauthorized_error))
            500, 502 -> throwError(statusCode, context.getString(R.string.unreachable_error))
            else -> {
                val errorBody = response.errorBody()
                var errorMessage = context.getString(R.string.something_went_wrong_error)
                if (errorBody != null) {
                    val errorBodyString = errorBody.toString()
                    if (errorBodyString.isNotEmpty()) {
                        val errorResponse = JSONObject(errorBodyString)
                        if (errorResponse.has("message")) {
                            val message: String? = errorResponse.getString("message")
                            if (!message.isNullOrEmpty()) {
                                errorMessage = message
                            }
                        }
                    }
                }
                throwError(statusCode = statusCode, message = errorMessage)
            }
        }
    }

    private fun <T> handleException(exception: Exception): Resource<T> {
        return when (exception) {
            is UnknownHostException ->
                throwError(-1, context.getString(R.string.no_internet_error))

            is TimeoutException, is SocketTimeoutException ->
                // 408: http status code for request timeout
                throwError(408, context.getString(R.string.server_error))

            else ->
                throwError(-1, exception.message ?: exception.toString())
        }
    }

    private fun <T> throwError(statusCode: Int, message: String): Resource<T> {
        return Resource.Error(statusCode = statusCode, message = message)
    }
}