package com.example.composeapp.product.feature.pdp.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.composeapp.product.data.product.ProductDetails

@Composable
fun ProductDescription(description: String) {
}

@Preview
@Composable
private fun ProductDescriptionPreview() {
    ProductDescription(description = ProductDetails.createMock().description)
}
