package com.example.jetpack_multiplenavigation.snackBarSB.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.navigation.NavHostController
import com.example.jetpack_multiplenavigation.snackBarSB.presentation.SnackBarViewModel
import com.example.jetpack_multiplenavigation.snackBarSB.snackBar.SnackBarController
import com.example.jetpack_multiplenavigation.snackBarSB.snackBar.SnackBarEvent
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SnackBarScreen(navController: NavHostController, ) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val snackBarViewModel = koinViewModel<SnackBarViewModel>()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Arrow back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black
                )
            )
        },
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = Color.Black
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Button(onClick = {
                    scope.launch {
                        SnackBarController.sendEvent(
                            event = SnackBarEvent(
                                message = "Snack Bar"
                            )
                        )
                    }
                }) {
                    Text(
                        text = "Show SnackBar",
                        fontFamily = FontFamily.Serif
                    )
                }
                Button(onClick = {
                    snackBarViewModel.showSnackBar(
                        message = "SnackBar ViewModel",
                        action = {
                            Toast.makeText(context, "SnackBar ViewModel", Toast.LENGTH_SHORT).show()
                        }
                    )
                }) {
                    Text(
                        text = "Show SnackBar ViewModel",
                        fontFamily = FontFamily.Serif
                    )
                }
            }
        }
    }
}