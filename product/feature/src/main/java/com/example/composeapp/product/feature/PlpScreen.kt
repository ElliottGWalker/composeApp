package com.example.composeapp.product.feature

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composeapp.product.data.product.ProductDetails
import com.example.composeapp.product.feature.PlpUiState.LoadedEmptyPlpUiState
import com.example.composeapp.product.feature.PlpUiState.LoadedErrorPlpUiState
import com.example.composeapp.product.feature.PlpUiState.LoadedPlpUiState
import com.example.composeapp.product.feature.PlpUiState.LoadingPlpUiState
import com.example.composeapp.product.feature.components.PlpProductTile
import com.example.composeapp.product.feature.destinations.PdpScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@PlpNavGraph(start = true)
@Destination
@Composable
fun PlpScreen(
    navigator: DestinationsNavigator,
    viewModel: PlpViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    // This would be passed from the previous category/search screen
    val searchTerm = "Leggings"
    PlpScreen(
        uiState = uiState,
        searchTerm = searchTerm,
        onProductClick = { navigator.navigate(PdpScreenDestination()) },
        onRetryClick = { viewModel.getProducts() },
    )
}

@Composable
private fun PlpScreen(
    uiState: PlpUiState,
    searchTerm: String,
    onProductClick: (ProductDetails) -> Unit,
    onRetryClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            PlpTopAppBar(searchTerm)
        },
    ) { padding ->
        Box(
            modifier =
                Modifier
                    .padding(padding)
                    .fillMaxSize(),
        ) {
            when (uiState) {
                is LoadingPlpUiState -> PlpLoadingContent()
                is LoadedEmptyPlpUiState -> PlpLoadedEmptyContent(onRetryClick = onRetryClick)
                is LoadedPlpUiState ->
                    PlpLoadedContent(
                        uiState = uiState,
                        onProductClick = onProductClick,
                    )
                is LoadedErrorPlpUiState -> PlpLoadedErrorContent(onRetryClick = onRetryClick,)
            }
        }
    }
}

// This method would normally also have some back navigation handling
// to return to a category/search screen
@Composable
private fun PlpTopAppBar(plpTitle: String) {
    TopAppBar(
        title = { Text(text = plpTitle) },
        backgroundColor = MaterialTheme.colors.surface,
    )
}

@Composable
private fun BoxScope.PlpLoadingContent() {
    CircularProgressIndicator(
        modifier =
            Modifier
                .size(64.dp)
                .align(Alignment.Center),
    )
}

@Composable
private fun PlpLoadedEmptyContent(onRetryClick: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Text(
            text = stringResource(R.string.no_products_returned),
            textAlign = TextAlign.Center,
        )
        Button(
            onClick = onRetryClick,
            content = {
                Text(text = stringResource(R.string.retry))
            },
        )
    }
}

@Composable
private fun PlpLoadedContent(
    uiState: LoadedPlpUiState,
    onProductClick: (ProductDetails) -> Unit,
) {
    val gridState = rememberLazyGridState()
    val itemsInRow = 2
    Box(modifier = Modifier.fillMaxSize()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(itemsInRow),
            state = gridState,
            contentPadding = PaddingValues(4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(
                items = uiState.plpList,
            ) { product ->
                PlpProductTile(
                    product = product,
                    onProductClick = onProductClick,
                )
            }
        }
    }
}

@Composable
private fun PlpLoadedErrorContent(onRetryClick: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Text(
            text = stringResource(R.string.something_went_wrong_please_try_again),
            textAlign = TextAlign.Center,
        )
        Button(
            onClick = onRetryClick,
            content = {
                Text(text = stringResource(R.string.retry))
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PlpScreenLoadedWithProductsPreview() {
    PlpScreen(
        uiState =
            LoadedPlpUiState(
                plpList =
                    listOf(
                        ProductDetails.createMock(),
                        ProductDetails.createMock(),
                        ProductDetails.createMock(),
                        ProductDetails.createMock(),
                        ProductDetails.createMock(),
                        ProductDetails.createMock(),
                    ),
            ),
        searchTerm = "Leggings",
        onProductClick = { /* unused */ },
        onRetryClick = { /* unused */ },
    )
}

@Preview(showBackground = true)
@Composable
private fun PlpScreenLoadingPreview() {
    PlpScreen(
        uiState = LoadingPlpUiState,
        searchTerm = "Leggings",
        onProductClick = { /* unused */ },
        onRetryClick = { /* unused */ },
    )
}

@Preview(showBackground = true)
@Composable
private fun PlpScreenLoadedEmptyStatePreview() {
    PlpScreen(
        uiState = LoadedEmptyPlpUiState,
        searchTerm = "Leggings",
        onProductClick = { /* unused */ },
        onRetryClick = { /* unused */ },
    )
}

@Preview(showBackground = true)
@Composable
private fun PlpScreenLoadedErrorStatePreview() {
    PlpScreen(
        uiState = LoadedErrorPlpUiState,
        searchTerm = "Leggings",
        onProductClick = { /* unused */ },
        onRetryClick = { /* unused */ },
    )
}
