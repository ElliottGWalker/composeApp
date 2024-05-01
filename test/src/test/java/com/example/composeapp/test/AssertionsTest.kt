package com.example.composeapp.test

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Test

private open class SuperType

private class SubType : SuperType()

class AssertionsTest {
    @Test
    fun `assertTypeEquals() - when the object matches the supplied type - should pass assertion`() {
        assertTypeEquals<SuperType>(SuperType())
    }

    @Test
    fun `assertTypeEquals() - when the object is a subtype of the supplied type - should pass assertion`() {
        assertTypeEquals<SuperType>(SubType())
    }

    @Test
    fun `assertTypeEquals() - when the object is a supertype of the supplied type - should fail assertion`() {
        var error: AssertionError? = null
        try {
            assertTypeEquals<SubType>(SuperType())
        } catch (e: AssertionError) {
            error = e
        }

        assertNotNull(error)
        assertEquals(
            "Expected a type of class com.example.composeapp.test.SubType, but was class com.example.composeapp.test.SuperType",
            error?.message,
        )
    }

    @Test
    fun `assertTypeEquals() - when the object is unrelated to the supplied type - should fail assertion`() {
        var error: AssertionError? = null
        try {
            assertTypeEquals<SuperType>("test")
        } catch (e: AssertionError) {
            error = e
        }

        assertNotNull(error)
        assertEquals(
            "Expected a type of class com.example.composeapp.test.SuperType, but was class kotlin.String",
            error?.message,
        )
    }

    @Test
    fun `assertTypeEquals() - when the object is null - should fail assertion`() {
        var error: IllegalStateException? = null
        try {
            assertTypeEquals<SuperType>(null)
        } catch (e: IllegalStateException) {
            error = e
        }

        assertNotNull(error)
        assertEquals(
            "Actual cannot be null when asserting type equality",
            error?.message,
        )
    }
}
