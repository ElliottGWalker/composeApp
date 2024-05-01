package com.example.composeapp.product.feature.pdp.components

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import com.example.composeapp.test.compose.ComposeAppPaparazzi
import com.example.composeapp.test.compose.composeAppThemeSnapshot
import org.junit.Rule
import org.junit.Test

class ProductImageCarouselTest {
    @get:Rule
    val paparazzi = ComposeAppPaparazzi.forComponents()

    @Test
    fun `ProductImageCarousel - should render correctly`() {
        paparazzi.composeAppThemeSnapshot {
            CompositionLocalProvider(value = LocalInspectionMode provides true) {
                val listState = rememberLazyListState()
                ProductImageCarousel(
                    listState = listState,
                    imageUrls = listOf("", ""),
                )
            }
        }
    }
}
