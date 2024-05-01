package com.example.composeapp.product.feature.pdp.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeapp.ui.components.images.UrlImage

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun ProductImageCarousel(
    listState: LazyListState,
    imageUrls: List<String>,
) {
    LazyRow(
        state = listState,
        flingBehavior =
            rememberSnapFlingBehavior(
                snapLayoutInfoProvider =
                    SnapLayoutInfoProvider(
                        lazyListState = listState,
                        positionInLayout = { _, _, _, _, _ -> 0 },
                    ),
            ),
        modifier =
        Modifier
            .fillMaxWidth()
            .height(400.dp),
    ) {
        items(imageUrls) { url ->
            CarouselImage(
                imageUrl = url,
                modifier = Modifier.fillParentMaxWidth()
            )
        }
    }
}

@Composable
private fun CarouselImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        UrlImage(url = imageUrl, contentDescription = "")
    }
}

@Preview
@Composable
private fun ProductImageCarouselPreview() {
    val listState = rememberLazyListState()
    ProductImageCarousel(
        listState = listState,
        imageUrls = listOf("", ""),
    )
}
