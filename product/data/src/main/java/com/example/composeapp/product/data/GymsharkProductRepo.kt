package com.example.composeapp.product.data

import com.example.composeapp.product.data.product.ProductDetails
import com.example.composeapp.product.data.product.ProductService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class GymsharkProductRepo
    @Inject
    constructor(
        private val productService: ProductService,
    ) : ProductRepo {
        override suspend fun getProducts(): Result<List<ProductDetails>> {
            return try {
                val responses = productService.getProducts()
                Result.success(value = responses.products)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
