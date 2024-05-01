package com.example.composeapp.core.network

import com.example.composeapp.core.network.Response.Failure
import com.example.composeapp.core.network.Response.Success
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ResponseTest {
    @Test
    fun `Success - should store the provided data in a field`() {
        val data = "test"

        val result = Success(data = data)

        assertEquals("test", result.data)
    }

    @Test
    fun `Failure = should store the provided exception in a field`() {
        val exception = Exception("oops")

        val result = Failure<String>(exception = exception)

        assertEquals(exception, result.exception)
    }
}
