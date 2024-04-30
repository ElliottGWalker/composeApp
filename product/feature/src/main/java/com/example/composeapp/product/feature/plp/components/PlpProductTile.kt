package com.example.composeapp.product.feature.plp.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.composeapp.product.data.product.ProductDetails
import com.example.composeapp.ui.components.producttile.ProductTile

@Composable
internal fun PlpProductTile(
    product: ProductDetails,
    onProductClick: (ProductDetails) -> Unit,
) {
    ProductTile(
        product = product,
        onProductClick = onProductClick,
    )
}

@Preview
@Composable
private fun PlpProductTilePreview() {
    PlpProductTile(
        product = ProductDetails.createMock(),
        onProductClick = { /* unused */ }
    )
}
