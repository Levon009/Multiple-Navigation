package com.example.jetpack_multiplenavigation.loadInitialData

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoadInitialDataVM : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading
        .onStart { startLoading() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            false
        )

    private fun startLoading() {
        viewModelScope.launch {
            _isLoading.update { true }
            delay(3000L)
            _isLoading.update { false }
        }
    }
}