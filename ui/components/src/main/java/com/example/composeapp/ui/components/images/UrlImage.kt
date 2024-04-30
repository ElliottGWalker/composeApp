package com.example.composeapp.ui.components.images

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImagePainter
import coil.compose.AsyncImagePainter.*
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.composeapp.ui.components.R

val showingError = mutableStateOf(false)

@Composable
fun UrlImage(
    url: String,
    contentDescription: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
) {
    val asyncImagePainter =
        rememberAsyncImagePainter(
            model =
                ImageRequest.Builder(LocalContext.current)
                    .data(url)
                    .placeholder(placeholderDrawable(context = LocalContext.current))
                    .crossfade(true)
                    .build(),
        )

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.background(color = Color.White),
    ) {
        ImageContent(
            isError = asyncImagePainter.state is State.Error,
            asyncImagePainter = asyncImagePainter,
            contentDescription = contentDescription,
            contentScale = contentScale,
        )

        if (asyncImagePainter.state is State.Loading &&
            !(showingError.value) && // When error, coil will keep retrying periodically
            !LocalInspectionMode.current
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
internal fun ImageContent(
    isError: Boolean,
    asyncImagePainter: AsyncImagePainter,
    contentDescription: String,
    contentScale: ContentScale,
) {
    if (isError) {
        showingError.value = true
        Image(
            painter = painterResource(id = R.drawable.ic_image_not_found),
            contentDescription = contentDescription,
            modifier = Modifier.fillMaxSize()
        )
    } else {
        showingError.value = false
        Image(
            painter = asyncImagePainter,
            contentDescription = contentDescription,
            contentScale = contentScale,
            modifier = Modifier.fillMaxSize(),
        )
    }
}

@Preview
@Composable
private fun UrlImagePreview() {
    UrlImage(
        url = "https://picsum.photos/200/300",
        contentDescription = "",
        contentScale = ContentScale.Fit,
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun ImageContentErrorPreview() {
    ImageContent(
        isError = true,
        asyncImagePainter = rememberAsyncImagePainter(model = ImageRequest.Builder(LocalContext.current).build()),
        contentDescription = "",
        contentScale = ContentScale.Fit,
    )
}
