package com.example.composeapp.test.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams
import com.example.composeapp.ui.theming.ComposeAppTheme

object ComposeAppPaparazzi {
    fun forComponents() =
        Paparazzi(
            deviceConfig = DeviceConfig.PIXEL_6_PRO,
            maxPercentDifference = 0.0,
            renderingMode = SessionParams.RenderingMode.SHRINK,
            showSystemUi = false,
        )

    fun forScreens() =
        Paparazzi(
            deviceConfig = DeviceConfig.PIXEL_6_PRO,
            maxPercentDifference = 0.0,
            renderingMode = SessionParams.RenderingMode.FULL_EXPAND,
        )
}

fun Paparazzi.composeAppThemeSnapshot(content: @Composable () -> Unit) {
    snapshot {
        ComposeAppTheme {
            Box(modifier = Modifier.background(Color.White)) {
                content()
            }
        }
    }
}

fun Paparazzi.composeAppThemeSnapshotForScreen(content: @Composable () -> Unit) {
    snapshot {
        ComposeAppTheme {
            Box(modifier = Modifier.background(Color.White)) {
                val config = LocalConfiguration.current
                Box(
                    modifier =
                        Modifier
                            .width(config.screenWidthDp.dp)
                            .height(config.screenHeightDp.dp),
                ) {
                    content()
                }
            }
        }
    }
}
