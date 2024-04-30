package com.example.composeapp.ui.components.producttile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeapp.product.data.product.ProductDetails
import com.example.composeapp.ui.components.R
import com.example.composeapp.ui.components.images.UrlImage
import com.example.composeapp.ui.theming.TextSize

@Composable
fun ProductTile(
    product: ProductDetails,
    onProductClick: (ProductDetails) -> Unit,
) {
    Card(
        elevation = 0.dp,
        modifier = Modifier
            .background(color = Color.White)
            .clickable { onProductClick(product) },
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth(),
        ) {
            Box {
                UrlImage(
                    url = product.featuredMedia.src,
                    contentDescription = stringResource(R.string.image_of, product.title),
                    modifier =
                        Modifier
                            .height(200.dp)
                            .align(Alignment.TopCenter),
                )

                val labels = product.labels
                if (labels != null) {
                    Labels(
                        items = labels,
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .align(Alignment.BottomStart)
                                .padding(4.dp),
                    )
                }
            }
            Text(
                text = product.title,
                fontSize = TextSize.Large,
            )
            val fit = product.fit
            if (fit != null) {
                Text(
                    text = fit,
                    fontSize = TextSize.Normal,
                )
            }
            Text(
                text = product.colour,
                fontSize = TextSize.Small,
            )
            Text(
                text = product.price.toString(),
                fontSize = TextSize.Large,
                fontWeight = FontWeight.Bold,
            )
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
