package com.example.composeapp.ui.components.producttile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeapp.ui.theming.ComposeAppTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Labels(
    items: List<String>,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier,
    ) {
        for (label in items) {
            Text(
                text = getLabelText(label),
                fontWeight = FontWeight.Bold,
                modifier =
                    Modifier
                        .background(color = Color.LightGray, shape = RoundedCornerShape(4.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp),
            )
        }
    }
}

private fun getLabelText(label: String): String {
    return when (label) {
        "new" -> "New"
        "going-fast" -> "Going fast"
        "popular" -> "Popular"
        "limited-edition" -> "Limited edition"
        "recycled-nylon" -> "Recycled nylon"
        "recycled-polyester" -> "Recycled polyester"
        else -> label
    }
}

@Preview(showBackground = true)
@Composable
private fun LabelsShortListPreview() {
    ComposeAppTheme {
        Labels(
            items = listOf("new", "going-fast"),
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LabelsLongListPreview() {
    ComposeAppTheme {
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
