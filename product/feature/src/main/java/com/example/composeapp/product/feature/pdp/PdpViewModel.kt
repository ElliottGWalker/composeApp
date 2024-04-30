package com.example.composeapp.product.feature.pdp

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.composeapp.product.feature.navArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PdpViewModel
    @Inject
    constructor(savedStateHandle: SavedStateHandle) : ViewModel() {
        private val navArgs = savedStateHandle.navArgs<PdpScreenNavArgs>()
        private val _uiState: MutableStateFlow<PdpUiState> =
            MutableStateFlow(
                PdpUiState.LoadedPdpState(
                    product = navArgs.product,
                ),
            )
        val uiState: StateFlow<PdpUiState>
            get() = _uiState

        private val _pdpTitle = MutableStateFlow("")
        val pdpTitle: StateFlow<String>
            get() = _pdpTitle

        init {
            setPdpTitle(navArgs)
        }

        private fun setPdpTitle(pdpScreenNavArgs: PdpScreenNavArgs) {
            _pdpTitle.value = pdpScreenNavArgs.product.title
        }
    }
