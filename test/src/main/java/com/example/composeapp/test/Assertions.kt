package com.example.composeapp.test

import junit.framework.TestCase.assertTrue
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
inline fun <reified T> assertTypeEquals(actual: Any?) {
    contract {
        returns() implies (actual != null && actual is T)
    }
    checkNotNull(actual) { "Actual cannot be null when asserting type equality" }
    assertTrue(
        "Expected a type of ${T::class}, but was ${actual::class}",
        actual is T,
    )
}
