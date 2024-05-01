package com.example.composeapp.test

import io.mockk.Ordering
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Test
import org.junit.runners.model.Statement

@OptIn(ExperimentalCoroutinesApi::class)
class TestDispatcherRuleTest {
    @Test
    fun `TestDispatcherRule - should set the main dispatcher before executing test, and reset it afterwards`() {
        mockkStatic(Dispatchers::setMain)
        mockkStatic(Dispatchers::resetMain)
        every { Dispatchers.setMain(any()) } returns Unit
        every { Dispatchers.resetMain() } returns Unit
        val rule = TestDispatcherRule()
        val statement =
            mockk<Statement> {
                every { evaluate() } returns Unit
            }

        rule.apply(statement, mockk()).evaluate()

        verify(ordering = Ordering.SEQUENCE) {
            Dispatchers.setMain(any())
            statement.evaluate()
            Dispatchers.resetMain()
        }
    }
}
