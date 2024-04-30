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
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composeapp.product.data.product.ProductDetails
import com.example.composeapp.product.feature.components.PlpProductTile
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@ProductNavGraph(start = true)
@Destination
@Composable
fun PlpScreen(
    navigator: DestinationsNavigator,
    viewModel: PlpViewModel = hiltViewModel(),
    onProductClick: (ProductDetails) -> Unit,
) {
}

@Composable
private fun PlpScreen(
    uiState: PlpUiState,
    searchTerm: String,
    onProductClick: (ProductDetails) -> Unit,
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
                is PlpUiState.LoadingPlpUiState -> PlpLoadingContent()
                is PlpUiState.LoadedEmptyPlpUiState -> PlpLoadedEmptyContent(searchTerm = searchTerm)
                is PlpUiState.LoadedPlpUiState -> PlpLoadedContent(
                    uiState = uiState,
                    onProductClick = onProductClick,
                )
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
private fun PlpLoadedEmptyContent(searchTerm: String) {
    Column {
    }
}

@Composable
private fun PlpLoadedContent(
    uiState: PlpUiState.LoadedPlpUiState,
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
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            itemsIndexed(
                items = uiState.plpList
            ) { index, product ->
                PlpProductTile(
                    product = product,
                    onProductClick = onProductClick,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PlpScreenLoadedWithProductsPreview() {
    PlpScreen(
        uiState =
            PlpUiState.LoadedPlpUiState(
                plpList =
                    listOf(
                        ProductDetails.createMock(),
                        ProductDetails.createMock(),
                        ProductDetails.createMock(),
                    ),
            ),
        searchTerm = "Leggings",
        onProductClick = { /* unused */ }
    )
}

@Preview(showBackground = true)
@Composable
private fun PlpScreenLoadingPreview() {
    PlpScreen(
        uiState =
            PlpUiState.LoadingPlpUiState,
        searchTerm = "Leggings",
        onProductClick = { /* unused */ }
    )
}
