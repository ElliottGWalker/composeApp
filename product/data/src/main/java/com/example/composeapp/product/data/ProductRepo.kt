package com.example.composeapp.product.data

import com.example.composeapp.product.data.product.ProductDetails

interface ProductRepo {
    suspend fun getProducts(): Result<List<ProductDetails>>
}
