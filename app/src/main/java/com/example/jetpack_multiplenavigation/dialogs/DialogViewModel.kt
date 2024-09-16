package com.example.jetpack_multiplenavigation.dialogs

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class DialogViewModel : ViewModel() {
    var isCustomDialogShown by mutableStateOf(false)
        private set
    var isAlertDialogShown by mutableStateOf(false)
        private set
    var isBottomSheetOpen by mutableStateOf(false)
        private set

    fun onCustomDialogClick() {
        isCustomDialogShown = true
    }

    fun onCustomDialogDismiss() {
        isCustomDialogShown = false
    }

    fun onAlertDialogClick() {
        isAlertDialogShown = true
    }

    fun onAlertDialogDismiss() {
        isAlertDialogShown = false
    }

    fun onBottomSheetClick() {
        isBottomSheetOpen = true
    }

    fun onBottomSheetDismissRequest() {
        isBottomSheetOpen = false
    }
}