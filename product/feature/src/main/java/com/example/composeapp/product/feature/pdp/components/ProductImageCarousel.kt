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
import com.example.composeapp.product.data.product.Media
import com.example.composeapp.product.data.product.ProductDetails
import com.example.composeapp.ui.components.images.UrlImage

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun ProductImageCarousel(
    listState: LazyListState,
    mediaList: List<Media>,
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
        items(mediaList) { media ->
            CarouselImage(media)
        }
    }
}

@Composable
private fun CarouselImage(media: Media) {
    Column {
        UrlImage(
            url = media.src,
            contentDescription = "",
        )
    }
}

@Preview
@Composable
private fun ProductImageCarouselPreview() {
    val listState = rememberLazyListState()
    ProductImageCarousel(
        listState = listState,
        mediaList = ProductDetails.createMock().media,
    )
}

@Preview
@Composable
private fun CarouselImagePreview() {
    CarouselImage(media = Media.createMock(1))
}
