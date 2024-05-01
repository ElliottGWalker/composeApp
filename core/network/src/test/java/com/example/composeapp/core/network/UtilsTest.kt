package com.example.composeapp.core.network

import com.example.composeapp.test.assertTypeEquals
import junit.framework.TestCase.assertEquals
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test
import retrofit2.HttpException
import java.io.IOException
import retrofit2.Response as RetrofitResponse

class UtilsTest {
    @Test
    fun `failureHandlingForServices - when HttpException - should return Exception with correct message`() {
        val errorBody = "".toResponseBody("text/plain".toMediaTypeOrNull())
        val httpException =
            HttpException(
                RetrofitResponse.error<ResponseBody>(
                    401,
                    errorBody,
                ),
            )

        val result = failureHandlingForServices<ResponseBody>(httpException)
        assertTypeEquals<Exception>(result.exception)
        assertEquals("Sorry, something went wrong, please try again later", result.exception.message)
    }

    @Test
    fun `failureHandlingForServices - when IOException - should return IOException with correct message`() {
        val ioException = IOException("test")

        val result = failureHandlingForServices<ResponseBody>(ioException)
        assertTypeEquals<IOException>(result.exception)
        assertEquals("Connection failed, please check your internet connection and try again", result.exception.message)
    }

    @Test
    fun `failureHandlingForServices - when Exception - should return Generic with correct message`() {
        val exception = IllegalStateException("test")

        val result = failureHandlingForServices<ResponseBody>(exception)
        assertTypeEquals<Exception>(result.exception)
        assertEquals("Sorry, something went wrong, please try again later", result.exception.message)
    }

    @Test
    fun `getGenericException() - should return an exception with a generic message and the provided cause`() {
        val cause = IllegalStateException("Uh oh!")

        val result = getGenericException(cause)

        assertEquals("Sorry, something went wrong, please try again later", result.message)
        assertEquals(cause, result.cause)
    }

    @Test
    fun `getIOException() - should return an IOException with a network message and the provided cause`() {
        val cause = IllegalStateException("Uh oh!")

        val result = getIOException(cause)

        assertEquals("Connection failed, please check your internet connection and try again", result.message)
        assertEquals(cause, result.cause)
    }
}
