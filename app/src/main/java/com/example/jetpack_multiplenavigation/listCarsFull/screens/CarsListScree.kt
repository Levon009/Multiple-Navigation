package com.example.jetpack_multiplenavigation.listCarsFull.screens

import android.app.Activity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.draf.personsListFull.dragDropListItem.move
import com.example.jetpack_multiplenavigation.listCarsFull.data.carList
import com.example.jetpack_multiplenavigation.listCarsFull.dialogs.CallCarsDialogs
import com.example.jetpack_multiplenavigation.listCarsFull.dialogs.CarsDialogViewModel
import com.example.jetpack_multiplenavigation.ui.theme.Beige3
import com.example.jetpack_multiplenavigation.ui.theme.BlueViolet1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarsListScreen(navController: NavHostController) {
    val context = (LocalContext.current as? Activity)
    val cars = carList
    val carsDialogViewModel = viewModel<CarsDialogViewModel>()
    val sheetState = rememberModalBottomSheetState()
    val isAlertDialogShown = carsDialogViewModel.isAlertDialogShown.collectAsState().value
    val isCustomDialogShown = carsDialogViewModel.isCustomDialogShown.collectAsState().value
    val isBottomSheetShown = carsDialogViewModel.isBottomSheetShown.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Cars List",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.5.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Arrow back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        carsDialogViewModel.onBottomSheetClick()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Bottom Sheet",
                            tint = Color.Red
                        )
                    }
                    IconButton(onClick = {
                        carsDialogViewModel.onAlertDialogClick()
                    }) {
                        Icon(
                            imageVector = Icons.Default.AccountBox,
                            contentDescription = "Alert Dialog",
                            tint = BlueViolet1
                        )
                    }
                    IconButton(onClick = {
                        carsDialogViewModel.onCustomDialogClick()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "Custom Dialog",
                            tint = Beige3
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = MaterialTheme.colorScheme.background
        ) {
            CarsList(
                supportingText = "Cars List",
                cars = cars
            ) { fromIndex, toIndex ->
                cars.move(fromIndex, toIndex)
            }
        }
        if (isAlertDialogShown || isCustomDialogShown || isBottomSheetShown) {
            CallCarsDialogs(
                carsDialogViewModel = carsDialogViewModel,
                sheetState = sheetState
            )
        }
    }
}