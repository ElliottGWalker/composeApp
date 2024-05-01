package com.example.composeapp.product.data

import com.example.composeapp.core.network.Response
import com.example.composeapp.core.network.Response.Success
import com.example.composeapp.core.network.failureHandlingForServices
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
        override suspend fun getProducts(): Response<List<ProductDetails>> {
            return try {
                val response = productService.getProducts()
                Success(data = response.products)
            } catch (e: Exception) {
                failureHandlingForServices(e)
            }
        }
    }
