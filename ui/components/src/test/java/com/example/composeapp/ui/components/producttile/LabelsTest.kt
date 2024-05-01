package com.example.composeapp.ui.components.producttile

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import com.example.composeapp.test.compose.ComposeAppPaparazzi
import com.example.composeapp.test.compose.composeAppThemeSnapshot
import org.junit.Rule
import org.junit.Test

class LabelsTest {
    @get:Rule
    val paparazzi = ComposeAppPaparazzi.forComponents()

    @Test
    fun `Labels - single label - should render correctly`() {
        paparazzi.composeAppThemeSnapshot {
            Labels(
                items = listOf("new"),
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }

    @Test
    fun `Labels - multiple labels - should render correctly`() {
        paparazzi.composeAppThemeSnapshot {
            Labels(
                items =
                    listOf(
                        "new",
                        "going-fast",
                        "limited-edition",
                        "popular",
                        "recycled-nylon",
                        "recycled-polyester",
                        "test-label",
                    ),
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}
