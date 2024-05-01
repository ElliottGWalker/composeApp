package com.example.composeapp.ui.components.images

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.composeapp.test.compose.ComposeAppPaparazzi
import com.example.composeapp.test.compose.composeAppThemeSnapshot
import org.junit.Rule
import org.junit.Test

class UrlImageTest {
    @get:Rule
    val paparazzi = ComposeAppPaparazzi.forComponents()

    @Test
    fun `UrlImage - should render correctly`() {
        paparazzi.composeAppThemeSnapshot {
            UrlImage(
                url = "https://picsum.photos/200/300",
                contentDescription = "",
                contentScale = ContentScale.Fit,
            )
        }
    }

    @Test
    fun `ImageContent - should render error image correctly`() {
        paparazzi.composeAppThemeSnapshot {
            ImageContent(
                isError = true,
                asyncImagePainter =
                    rememberAsyncImagePainter(
                        model = ImageRequest.Builder(LocalContext.current).build(),
                    ),
                contentDescription = "",
                contentScale = ContentScale.Fit,
            )
        }
    }
}
