package com.example.jetpack_multiplenavigation.listCarsFull.dialogs

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.jetpack_multiplenavigation.listCarsFull.dialogs.carsAlertDialog.CarsAlertDialog
import com.example.jetpack_multiplenavigation.listCarsFull.dialogs.carsCustomDialog.CarsCustomDialog
import com.example.jetpack_multiplenavigation.listCarsFull.dialogs.carsModalBottomSheet.CarsModalBottomSheetContent

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CallCarsDialogs(
    carsDialogViewModel: CarsDialogViewModel,
    sheetState: SheetState
) {
    val context = LocalContext.current
    when {
        carsDialogViewModel.isAlertDialogShown.value -> {
            CarsAlertDialog(
                title = "Alert Dialog",
                text = "This is an example of an alert dialog with buttons.",
                icon = Icons.Default.Info,
                onConfirmation = carsDialogViewModel::onAlertDialogDismissRequest
            ) {
                Toast.makeText(context, "Alert Dialog", Toast.LENGTH_SHORT).show()
                carsDialogViewModel.onAlertDialogDismissRequest()
            }
        }
        carsDialogViewModel.isCustomDialogShown.value -> {
            CarsCustomDialog(onDismissRequest = carsDialogViewModel::onCustomDialogDismissRequest) {
                Toast.makeText(context, "Custom Dialog", Toast.LENGTH_SHORT).show()
                carsDialogViewModel.onCustomDialogDismissRequest()
            }
        }
        carsDialogViewModel.isBottomSheetShown.value -> {
            ModalBottomSheet(
                sheetState = sheetState,
                onDismissRequest = carsDialogViewModel::onBottomSheetDismissRequest,
                tonalElevation = 5.dp,
                containerColor = Color.White,
                contentColor = Color.White
            ) {
                CarsModalBottomSheetContent()
            }
        }
    }
}