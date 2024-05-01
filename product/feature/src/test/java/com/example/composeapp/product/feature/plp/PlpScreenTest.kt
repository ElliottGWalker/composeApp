package com.example.composeapp.product.feature.plp

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import com.example.composeapp.product.data.product.ProductDetails
import com.example.composeapp.product.feature.plp.PlpUiState.LoadedEmptyPlpUiState
import com.example.composeapp.product.feature.plp.PlpUiState.LoadedErrorPlpUiState
import com.example.composeapp.product.feature.plp.PlpUiState.LoadingPlpUiState
import com.example.composeapp.test.compose.ComposeAppPaparazzi
import com.example.composeapp.test.compose.composeAppThemeSnapshotForScreen
import org.junit.Rule
import org.junit.Test

class PlpScreenTest {
    @get:Rule
    val paparazzi = ComposeAppPaparazzi.forScreens()

    @Test
    fun `PdpScreen - LoadingPlpUiState - should render correctly`() {
        paparazzi.composeAppThemeSnapshotForScreen {
            CompositionLocalProvider(value = LocalInspectionMode provides true) {
                PlpScreen(
                    uiState = LoadingPlpUiState,
                    searchTerm = "Title",
                    onProductClick = { /* unused */ },
                    onRetryClick = { /* unused */ },
                )
            }
        }
    }

    @Test
    fun `PdpScreen - LoadedErrorPlpUiState - should render correctly`() {
        paparazzi.composeAppThemeSnapshotForScreen {
            CompositionLocalProvider(value = LocalInspectionMode provides true) {
                PlpScreen(
                    uiState = LoadedErrorPlpUiState,
                    searchTerm = "Title 2",
                    onProductClick = { /* unused */ },
                    onRetryClick = { /* unused */ },
                )
            }
        }
    }

    @Test
    fun `PdpScreen - LoadedEmptyPlpUiState - should render correctly`() {
        paparazzi.composeAppThemeSnapshotForScreen {
            CompositionLocalProvider(value = LocalInspectionMode provides true) {
                PlpScreen(
                    uiState = LoadedEmptyPlpUiState,
                    searchTerm = "Title 2",
                    onProductClick = { /* unused */ },
                    onRetryClick = { /* unused */ },
                )
            }
        }
    }

    @Test
    fun `PdpScreen - LoadedPlpUiState - should render correctly`() {
        paparazzi.composeAppThemeSnapshotForScreen {
            CompositionLocalProvider(value = LocalInspectionMode provides true) {
                PlpScreen(
                    uiState =
                        PlpUiState.LoadedPlpUiState(
                            plpList =
                                listOf(
                                    ProductDetails.createMock(),
                                    ProductDetails.createMock(),
                                    ProductDetails.createMock(),
                                    ProductDetails.createMock(),
                                    ProductDetails.createMock(),
                                ),
                        ),
                    searchTerm = "Title 2",
                    onProductClick = { /* unused */ },
                    onRetryClick = { /* unused */ },
                )
            }
        }
    }
}
