package com.example.composeapp.product.feature.pdp

import androidx.lifecycle.SavedStateHandle
import com.example.composeapp.product.data.product.ProductDetails
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

internal class PdpViewModelTest {
    private val product = ProductDetails.createMock()
    private lateinit var pdpViewModel: PdpViewModel

    @Before
    fun setup() {
        pdpViewModel =
            PdpViewModel(
                SavedStateHandle(
                    mapOf(
                        "product" to product,
                    ),
                ),
            )
    }

    @Test
    fun `uiState - should have the correct initial state`() {
        val result = pdpViewModel.uiState.value

        assert(result is PdpUiState.LoadedPdpState)
        result as PdpUiState.LoadedPdpState
        assertEquals(product, result.product)
    }

    @Test
    fun `setPdpTitle() - should use`() {
        val pdpScreenNavArgs = PdpScreenNavArgs(product)

        pdpViewModel.setPdpTitle(pdpScreenNavArgs)

        assertEquals("title", pdpViewModel.pdpTitle.value)
    }
}
