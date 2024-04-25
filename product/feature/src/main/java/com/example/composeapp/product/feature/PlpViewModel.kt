package com.example.composeapp.product.feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeapp.product.data.ProductRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlpViewModel
    @Inject
    constructor(
        private val productRepo: ProductRepo,
    ) : ViewModel() {
        fun searchProducts() {
            viewModelScope.launch {
                val response = productRepo.getProducts()
                when {
                    response.isSuccess -> {}
                    response.isFailure -> {}
                }
            }
        }
    }
