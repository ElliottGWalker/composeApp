package com.example.composeapp.product.feature.pdp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import com.example.composeapp.product.data.product.ProductDetails
import com.example.composeapp.product.feature.R
import com.example.composeapp.ui.theming.TextSize
import com.google.android.material.textview.MaterialTextView

@Composable
fun ProductDescription(description: String) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = stringResource(R.string.about_this_product),
            fontSize = TextSize.Large,
        )
        Html(html = description)
    }
}

@Composable
private fun Html(html: String) {
    val spannedText = HtmlCompat.fromHtml(html, 0)
    Column {
        AndroidView(
            factory = { MaterialTextView(it) },
            update = { it.text = spannedText }
        )
    }
}

@Preview
@Composable
private fun ProductDescriptionPreview() {
    ProductDescription(description = ProductDetails.createMock().description)
}
