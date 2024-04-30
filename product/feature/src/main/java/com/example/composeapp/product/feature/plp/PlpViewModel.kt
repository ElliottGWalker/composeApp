package com.example.composeapp.product.feature.plp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeapp.core.network.Response
import com.example.composeapp.product.data.ProductRepo
import com.example.composeapp.product.data.product.ProductDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlpViewModel
    @Inject
    constructor(
        private val productRepo: ProductRepo,
    ) : ViewModel() {
        private val _uiState: MutableStateFlow<PlpUiState> =
            MutableStateFlow(
                PlpUiState.LoadingPlpUiState,
            )
        val uiState: StateFlow<PlpUiState>
            get() = _uiState

        init {
            getProducts()
        }

        fun getProducts() {
            _uiState.value = PlpUiState.LoadingPlpUiState
            viewModelScope.launch {
                when (val response = productRepo.getProducts()) {
                    is Response.Success -> {
                        onGetProductsResponse(response.data)
                    }
                    is Response.Failure -> {
                        onGetProductsError()
                    }
                }
            }
        }

        private fun onGetProductsResponse(productDetails: List<ProductDetails>) {
            _uiState.value =
                when {
                    productDetails.isEmpty() -> PlpUiState.LoadedEmptyPlpUiState
                    else -> PlpUiState.LoadedPlpUiState(productDetails)
                }
        }

        private fun onGetProductsError() {
            _uiState.value = PlpUiState.LoadedErrorPlpUiState
        }
    }
