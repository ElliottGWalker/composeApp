package com.example.composeapp.product.feature.pdp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import com.example.composeapp.product.data.product.ProductDetails
import com.google.android.material.textview.MaterialTextView

@Composable
fun ProductDescription(
    description: String,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier,
    ) {
        Html(html = description)
    }
}

@Composable
private fun Html(html: String) {
    val spannedText = HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_COMPACT)
    Column {
        AndroidView(
            factory = { MaterialTextView(it) },
            update = { it.text = spannedText },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductDescriptionPreview() {
    ProductDescription(description = ProductDetails.createMock().description)
}
