package com.example.composeapp.ui.components.text

import com.example.composeapp.test.compose.ComposeAppPaparazzi
import com.example.composeapp.test.compose.composeAppThemeSnapshot
import org.junit.Rule
import org.junit.Test

class CurrencyTextTest {
    @get:Rule
    val paparazzi = ComposeAppPaparazzi.forComponents()

    @Test
    fun `CurrencyText - should render correctly`() {
        paparazzi.composeAppThemeSnapshot {
            CurrencyText(
                price = 10.0,
            )
        }
    }
}
