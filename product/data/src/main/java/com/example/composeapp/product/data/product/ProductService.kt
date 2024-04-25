package com.example.composeapp.product.data.product

import retrofit2.http.GET

interface ProductService {
    @GET("training/mock-product-responses/algolia-example-payload.json")
    suspend fun getProducts(): GetProductsResponse
}
