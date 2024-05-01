package com.example.composeapp.product.data

import com.example.composeapp.core.network.Response.Failure
import com.example.composeapp.core.network.Response.Success
import com.example.composeapp.product.data.product.GetProductsResponse
import com.example.composeapp.product.data.product.ProductDetails
import com.example.composeapp.product.data.product.ProductService
import com.example.composeapp.test.assertTypeEquals
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class GymsharkProductRepoTest {
    @MockK
    lateinit var productService: ProductService

    private lateinit var gymsharkProductRepo: GymsharkProductRepo

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        gymsharkProductRepo =
            GymsharkProductRepo(
                productService = productService,
            )
    }

    @Test
    fun `getProducts - should call the product service with the correct arguments and return a Success object`() {
        val response = GetProductsResponse.createMock(3)
        coEvery { productService.getProducts() } returns response

        val result = runBlocking { gymsharkProductRepo.getProducts() }

        coVerify(exactly = 1) { productService.getProducts() }
        assertTypeEquals<Success<List<ProductDetails>>>(result)
        assertEquals(3, result.data.size)
        assertEquals(response.products, result.data)
    }

    @Test
    fun `getProducts - when network request fails - should record an exception and return a Failure object`() {
        coEvery { productService.getProducts() } throws Exception("test")

        val result = runBlocking { gymsharkProductRepo.getProducts() }

        assertTypeEquals<Failure<GetProductsResponse>>(result)
        assertEquals("Sorry, something went wrong, please try again later", result.exception.message)
    }
}
