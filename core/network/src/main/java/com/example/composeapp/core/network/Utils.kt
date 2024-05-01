package com.example.composeapp.core.network

import retrofit2.HttpException
import java.io.IOException

fun <T> failureHandlingForServices(e: Exception): Response.Failure<T> {
    return Response.Failure(
        when (e) {
            is HttpException -> {
                // Would normally add 401 logic in here to check for user session expiry (no need for this scenario)
                getGenericException(e)
            }
            is IOException -> getIOException(e)
            else -> getGenericException(e)
        },
    )
}

fun getGenericException(cause: Throwable) = Exception("Sorry, something went wrong, please try again later", cause)

fun getIOException(cause: Throwable) = IOException("Connection failed, please check your internet connection and try again", cause)
