package com.example.composeapp.product.feature

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composeapp.product.data.product.ProductDetails

@Composable
fun PlpScreen(
    viewModel: PlpViewModel = hiltViewModel(),
    onProductClick: (ProductDetails) -> Unit,
) {
}

@Composable
private fun PlpScreen(uiState: PlpUiState) {
    Column {
        Text("Test")
    }
}

@Preview
@Composable
private fun PlpScreenLoadedWithProductsPreview() {
    PlpScreen(
        uiState =
            PlpUiState.LoadedPlpUiState(
                plpList =
                    listOf(
                        ProductDetails.createMock(),
                        ProductDetails.createMock(),
                        ProductDetails.createMock(),
                    ),
            ),
    )
}
