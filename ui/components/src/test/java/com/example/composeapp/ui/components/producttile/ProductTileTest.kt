package com.example.composeapp.ui.components.producttile

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import com.example.composeapp.product.data.product.ProductDetails
import com.example.composeapp.test.compose.ComposeAppPaparazzi
import com.example.composeapp.test.compose.composeAppThemeSnapshot
import org.junit.Rule
import org.junit.Test

class ProductTileTest {
    @get:Rule
    val paparazzi = ComposeAppPaparazzi.forComponents()

    @Test
    fun `ProductTile - should render correctly`() {
        paparazzi.composeAppThemeSnapshot {
            CompositionLocalProvider(value = LocalInspectionMode provides true) {
                ProductTile(
                    product = ProductDetails.createMock(),
                    onProductClick = { /* unused */ },
                )
            }
        }
    }
}
