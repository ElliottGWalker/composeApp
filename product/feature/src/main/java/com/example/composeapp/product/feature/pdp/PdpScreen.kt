package com.example.composeapp.product.feature.pdp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composeapp.product.data.product.ProductDetails
import com.example.composeapp.product.feature.PlpNavGraph
import com.example.composeapp.product.feature.R
import com.example.composeapp.product.feature.pdp.PdpUiState.LoadedPdpState
import com.example.composeapp.product.feature.pdp.components.ProductDescription
import com.example.composeapp.product.feature.pdp.components.ProductImageCarousel
import com.example.composeapp.ui.components.producttile.Labels
import com.example.composeapp.ui.theming.TextSize
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

class PdpScreenNavArgs(
    val product: ProductDetails,
)

@PlpNavGraph
@Destination(navArgsDelegate = PdpScreenNavArgs::class)
@Composable
fun PdpScreen(
    navigator: DestinationsNavigator,
    viewModel: PdpViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val pdpTitle by viewModel.pdpTitle.collectAsState()

    PdpScreen(
        uiState = uiState,
        pdpTitle = pdpTitle,
        onBackClick = { navigator.navigateUp() },
    )
}

@Composable
internal fun PdpScreen(
    uiState: PdpUiState,
    pdpTitle: String,
    onBackClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            PdpTopAppBar(
                title = pdpTitle,
                onBackClick = onBackClick,
            )
        },
    ) { padding ->
        Box(
            modifier =
                Modifier
                    .padding(padding)
                    .fillMaxSize(),
        ) {
            when (uiState) {
                is LoadedPdpState -> PdpLoadedContent(uiState)
            }
        }
    }
}

@Composable
private fun PdpTopAppBar(
    title: String,
    onBackClick: () -> Unit,
) {
    TopAppBar(
        title = { Text(text = title) },
        backgroundColor = MaterialTheme.colors.surface,
        navigationIcon = { BackIconButton(onBackClick) },
    )
}

@Composable
private fun BackIconButton(onBackClick: () -> Unit) {
    IconButton(onClick = onBackClick) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = stringResource(R.string.back),
        )
    }
}

@Composable
private fun PdpLoadedContent(uiState: LoadedPdpState) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        val listState = rememberLazyListState()
        ProductImageCarousel(
            listState = listState,
            imageUrls = uiState.product.imageUrls,
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(horizontal = 16.dp),
        ) {
            val labels = uiState.product.labels
            if (labels != null) {
                Labels(
                    items = labels,
                    modifier =
                    Modifier.fillMaxWidth(),
                )
            }

            Row {
                Text(
                    text = uiState.product.title,
                    fontSize = TextSize.Large,
                    modifier = Modifier.weight(1f),
                )
                Text(
                    text = uiState.product.price.toString(),
                    fontSize = TextSize.Large,
                    fontWeight = FontWeight.Bold,
                )
            }

            val fit = uiState.product.fit
            if (fit != null) {
                Text(
                    text = fit,
                    fontSize = TextSize.Normal,
                )
            }
            Text(
                text = uiState.product.colour,
                fontSize = TextSize.Small,
            )
        }

        ProductDescription(uiState.product.description)
    }
}

@Preview
@Composable
fun PdpScreenPreview() {
    PdpScreen(
        uiState =
            LoadedPdpState(
                product = ProductDetails.createMock(),
            ),
        pdpTitle = "Title",
        onBackClick = { /* unused */ },
    )
}
