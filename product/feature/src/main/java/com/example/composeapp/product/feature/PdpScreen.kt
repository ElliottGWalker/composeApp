package com.example.composeapp.product.feature

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ramcosta.composedestinations.annotation.Destination

@ProductNavGraph
@Destination
@Composable
fun PdpScreen() {
    Column {
        Text("Test PDP")
    }
}

@Preview
@Composable
fun PdpScreenPreview() {
    PdpScreen()
}
