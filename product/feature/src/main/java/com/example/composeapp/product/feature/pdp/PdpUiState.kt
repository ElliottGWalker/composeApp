package com.example.composeapp.product.feature.pdp

import com.example.composeapp.product.data.product.ProductDetails

sealed class PdpUiState {
    class LoadedPdpState(val product: ProductDetails) : PdpUiState()
}