package com.makescreenshot.deletethis.data.network

import com.google.gson.Gson
import com.google.gson.JsonParseException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException

suspend fun <T> safeResultCall(call: suspend () -> T): Result<T> {
    return try {
        val callResult = call.invoke()
        Result.success(callResult)
    } catch (e: Throwable) {
        val defaultErrorMessage = "Oops...something wrong"
        val appException = when (e) {
            is ConnectException,
            is UnknownHostException,
            -> AppException.NetworkException(cause = e)
            is HttpException -> {
                when (e.code()) {
                    in 400..499 -> {
                        val errorBody = e.response()?.errorBody()?.string()
                        val errorMessage = if (errorBody != null) {
                            try {
                                val errorResponse =
                                    Gson().fromJson(errorBody, ErrorResponse::class.java)
                                errorResponse.message
                            } catch (ex: JsonParseException) {
                                defaultErrorMessage
                            }
                        } else {
                            defaultErrorMessage
                        }
                        AppException.HttpException.ClientException(errorMessage, e.code(), e)

                    }
                    in 500..599 -> AppException.HttpException.ServerException(
                        errorCode = e.code(),
                        cause = e
                    )
                    else -> AppException.HttpException(errorCode = e.code(), cause = e)
                }
            }
            else -> AppException.SomethingBadHappenedException(
                message = defaultErrorMessage,
                cause = e
            )
        }
        Result.failure(appException)
    }
}

data class ErrorResponse(val message: String, val context: String)

sealed class AppException(
    override val message: String?,
    cause: Throwable? = null,
) : Exception(message, cause) {


    class NetworkException(
        message: String? = null,
        cause: Throwable? = null,
    ) : AppException(message, cause)


    open class HttpException(
        message: String? = null,
        val errorCode: Int,
        cause: Throwable? = null,
    ) : AppException(message, cause) {

        class ClientException(
            message: String? = null,
            errorCode: Int,
            cause: Throwable? = null,
        ) : HttpException(message, errorCode, cause)

        class ServerException(
            message: String? = null,
            errorCode: Int,
            cause: Throwable? = null,
        ) : HttpException(message, errorCode, cause)
    }

    class SomethingBadHappenedException(
        message: String? = null,
        cause: Throwable? = null,
    ) : AppException(message, cause)
}