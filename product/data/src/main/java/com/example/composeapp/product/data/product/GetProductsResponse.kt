package com.example.composeapp.product.data.product

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class GetProductsResponse(
    @Json(name = "hits") val products: List<ProductDetails>,
) {
    companion object {
        fun createMock(numOfProducts: Int) =
            GetProductsResponse(
                products = List(numOfProducts) { ProductDetails.createMock() },
            )
    }
}
