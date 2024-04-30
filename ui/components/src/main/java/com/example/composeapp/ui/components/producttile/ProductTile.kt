package com.example.composeapp.ui.components.producttile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.composeapp.product.data.product.ProductDetails
import com.example.composeapp.ui.components.R
import com.example.composeapp.ui.components.images.UrlImage

@Composable
fun ProductTile(
    product: ProductDetails,
    onProductClick: (ProductDetails) -> Unit,
) {
    Card(
        modifier =
            Modifier
                .background(color = Color.White)
                .clickable { onProductClick(product) },
    ) {
        Column {
            Box {
                UrlImage(
                    url = product.featuredMedia.src,
                    contentDescription = stringResource(R.string.image_of, product.title),
                )
            }
        }
    }
}

@Preview
@Composable
private fun ProductTilePreview() {
    ProductTile(
        product = ProductDetails.createMock(),
        onProductClick = { /* unused */ },
    )
}
