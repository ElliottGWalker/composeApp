package com.example.composeapp.product.feature.pdp.components

import com.example.composeapp.product.data.product.ProductDetails
import com.example.composeapp.test.compose.ComposeAppPaparazzi
import com.example.composeapp.test.compose.composeAppThemeSnapshot
import org.junit.Rule
import org.junit.Test

class ProductDescriptionTest {
    @get:Rule
    val paparazzi = ComposeAppPaparazzi.forComponents()

    @Test
    fun `ProductDescription - should render correctly`() {
        paparazzi.composeAppThemeSnapshot {
            ProductDescription(description = ProductDetails.createMock().description)
        }
    }
}
