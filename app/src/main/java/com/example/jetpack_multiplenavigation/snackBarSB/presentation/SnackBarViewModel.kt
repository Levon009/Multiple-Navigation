package com.example.jetpack_multiplenavigation.snackBarSB.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack_multiplenavigation.snackBarSB.snackBar.SnackBarAction
import com.example.jetpack_multiplenavigation.snackBarSB.snackBar.SnackBarController
import com.example.jetpack_multiplenavigation.snackBarSB.snackBar.SnackBarEvent
import kotlinx.coroutines.launch

class SnackBarViewModel : ViewModel() {
    fun showSnackBar(
        message: String,
        action: () -> Unit
    ) {
        viewModelScope.launch {
            SnackBarController.sendEvent(
                event = SnackBarEvent(
                    message = message,
                    action = SnackBarAction(
                        name = "Action",
                        action = action
                    )
                )
            )
        }
    }
}