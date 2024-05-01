package com.example.composeapp.product.feature.plp

import app.cash.turbine.test
import com.example.composeapp.core.network.Response
import com.example.composeapp.product.data.ProductRepo
import com.example.composeapp.product.data.product.ProductDetails
import com.example.composeapp.product.feature.plp.PlpUiState.LoadingPlpUiState
import com.example.composeapp.test.TestDispatcherRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.spyk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class PlpViewModelTest {
    private val products =
        listOf(
            ProductDetails.createMock(),
            ProductDetails.createMock(),
        )

    @MockK
    lateinit var productRepo: ProductRepo

    private lateinit var plpViewModel: PlpViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val rule = TestDispatcherRule(UnconfinedTestDispatcher())

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        plpViewModel =
            spyk(
                PlpViewModel(productRepo = productRepo),
            )
    }

    @Test
    fun `PlpViewModel uiState - when init - should be LoadingPlpUiState`() {
        assertEquals(LoadingPlpUiState, plpViewModel.uiState.value)
    }

    @Test
    fun `PlpViewModel uiState - when getProducts returns a list of productsDetails - should be LoadedPlpUiState with correct products`() {
        runBlocking {
            coEvery { productRepo.getProducts() } returns Response.Success(products)

            plpViewModel.getProducts()

            plpViewModel.uiState.test {
                val result = awaitItem()
                assert(result is PlpUiState.LoadedPlpUiState)
                val loadedState = plpViewModel.uiState.value as PlpUiState.LoadedPlpUiState

                assertEquals(2, loadedState.plpList.size)
                assertEquals(products, loadedState.plpList)
            }
        }
    }

    @Test
    fun `PlpViewModel uiState - when getProducts returns an empty list - should be LoadedEmptyPlpUiState`() {
        runBlocking {
            coEvery { productRepo.getProducts() } returns Response.Success(listOf())

            plpViewModel.getProducts()

            plpViewModel.uiState.test {
                val result = awaitItem()
                assert(result is PlpUiState.LoadedEmptyPlpUiState)
            }
        }
    }

    @Test
    fun `PlpViewModel uiState - when getProducts throw Exception - should be LoadedErrorPlpUiState`() {
        runBlocking {
            coEvery { productRepo.getProducts() } returns Response.Failure(Exception("oops"))

            plpViewModel.getProducts()

            plpViewModel.uiState.test {
                val result = awaitItem()
                assert(result is PlpUiState.LoadedErrorPlpUiState)
            }
        }
    }
}
