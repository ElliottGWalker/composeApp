package com.example.composeapp.product.data

import com.example.composeapp.product.data.product.ProductService
import com.example.composeapp.test.assertTypeEquals
import org.junit.Test
import retrofit2.Retrofit

class ProductModuleTest {
    @Test
    fun `provideProductService - should return a ProductService instance`() {
        val retrofit = Retrofit.Builder().baseUrl("http://example.com/").build()

        val result = ProductModule.provideProductService(retrofit)

        assertTypeEquals<ProductService>(result)
    }
}
