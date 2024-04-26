package com.example.composeapp.product.feature

import com.example.composeapp.product.data.product.ProductDetails

sealed class PlpUiState {
    data object LoadingPlpUiState : PlpUiState()

    data object LoadedErrorPlpUiState : PlpUiState()

    data object LoadedEmptyPlpUiState : PlpUiState()

    class LoadedPlpUiState(
        val plpList: List<ProductDetails>,
    ) : PlpUiState()
}
