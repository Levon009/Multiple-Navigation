package com.example.jetpack_multiplenavigation.listCarsFull.dialogs

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CarsDialogViewModel : ViewModel() {
    private val _isAlertDialogShown = MutableStateFlow(false)
    val isAlertDialogShown = _isAlertDialogShown.asStateFlow()

    private val _isCustomDialogShown = MutableStateFlow(false)
    val isCustomDialogShown = _isCustomDialogShown.asStateFlow()

    private val _isBottomSheetShown = MutableStateFlow(false)
    val isBottomSheetShown = _isBottomSheetShown.asStateFlow()

    fun onAlertDialogClick() {
        _isAlertDialogShown.update { true }
    }

    fun onAlertDialogDismissRequest() {
        _isAlertDialogShown.update { false }
    }

    fun onCustomDialogClick() {
        _isCustomDialogShown.update { true }
    }

    fun onCustomDialogDismissRequest() {
        _isCustomDialogShown.update { false }
    }

    fun onBottomSheetClick() {
        _isBottomSheetShown.update { true }
    }

    fun onBottomSheetDismissRequest() {
        _isBottomSheetShown.update { false }
    }
}