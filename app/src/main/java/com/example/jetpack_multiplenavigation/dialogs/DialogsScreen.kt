package com.example.jetpack_multiplenavigation.dialogs

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.jetpack_multiplenavigation.dialogs.alertDialog.CallAlertDialog
import com.example.jetpack_multiplenavigation.dialogs.customDialog.CustomDialog
import com.example.jetpack_multiplenavigation.dialogs.modalBottomSheet.ModalBottomSheetContent
import com.example.jetpack_multiplenavigation.ui.theme.Beige3
import com.example.jetpack_multiplenavigation.ui.theme.TextWhite

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DialogsScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val dialogViewModel = viewModel<DialogViewModel>()
    val sheetState = rememberModalBottomSheetState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Dialogs",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Arrow back"
                        )
                    }
                },
                actions = {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Favorite",
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .clickable { }
                    )
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Refresh",
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .clickable { }
                    )
                }
            )
        }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier.fillMaxSize()
        ) {
            DialogButtons(dialogViewModel = dialogViewModel)
            CallDialogs(
                dialogViewModel = dialogViewModel,
                sheetState = sheetState
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CallDialogs(
    dialogViewModel: DialogViewModel,
    sheetState: SheetState
) {
    val context = LocalContext.current
    when {
        dialogViewModel.isCustomDialogShown -> {
            CustomDialog(onDismissRequest = dialogViewModel::onCustomDialogDismiss) {
                Toast.makeText(context, "Custom Dialog", Toast.LENGTH_SHORT).show()
                dialogViewModel.onCustomDialogDismiss()
            }
        }
        dialogViewModel.isAlertDialogShown -> {
            CallAlertDialog(
                title = "Alert Dialog",
                text = "This is an example of an alert dialog with buttons.",
                icon = Icons.Default.Info,
                onDismissRequest = dialogViewModel::onAlertDialogDismiss
            ) {
                Toast.makeText(context, "Alert Dialog", Toast.LENGTH_SHORT).show()
                dialogViewModel.onAlertDialogDismiss()
            }
        }
        dialogViewModel.isBottomSheetOpen -> {
            ModalBottomSheet(
                sheetState = sheetState,
                onDismissRequest = {
                    dialogViewModel.onBottomSheetDismissRequest()
                },
                tonalElevation = 5.dp,
                contentColor = Color.White,
                containerColor = Color.White
            ) {
                ModalBottomSheetContent()
            }
        }
    }
}

@Composable
fun DialogButtons(dialogViewModel: DialogViewModel) {
    Column(
        verticalArrangement = Arrangement.spacedBy(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        DialogButton(text = "Custom Dialog") {
            dialogViewModel.onCustomDialogClick()
        }
        DialogButton(text = "Alert Dialog") {
            dialogViewModel.onAlertDialogClick()
        }
        DialogButton(text = "Bottom Sheet") {
            dialogViewModel.onBottomSheetClick()
        }
    }
}

@Composable
fun DialogButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            contentColor = TextWhite,
            containerColor = Beige3
        ),
        shape = CircleShape,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Serif
        )
    }
}