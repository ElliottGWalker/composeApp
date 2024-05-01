package com.example.composeapp.product.feature.pdp

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import com.example.composeapp.product.data.product.ProductDetails
import com.example.composeapp.test.compose.ComposeAppPaparazzi
import com.example.composeapp.test.compose.composeAppThemeSnapshotForScreen
import org.junit.Rule
import org.junit.Test

class PdpScreenTest {
    @get:Rule
    val paparazzi = ComposeAppPaparazzi.forScreens()

    @Test
    fun `PdpScreen - LoadedPdpState - should render correctly`() {
        paparazzi.composeAppThemeSnapshotForScreen {
            CompositionLocalProvider(value = LocalInspectionMode provides true) {
                PdpScreen(
                    uiState =
                        PdpUiState.LoadedPdpState(
                            product = ProductDetails.createMock(),
                        ),
                    pdpTitle = "Title",
                    onBackClick = { /* unused */ },
                )
            }
        }
    }
}
