package com.example.jetpack_multiplenavigation.daggerCustom.authentication.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.jetpack_multiplenavigation.BaseApplication
import com.example.jetpack_multiplenavigation.daggerCustom.authentication.data.model.LoginRequest
import com.example.jetpack_multiplenavigation.daggerCustom.authentication.presentation.AuthViewModel
import com.example.jetpack_multiplenavigation.daggerCustom.authentication.presentation.viewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDIScreen(navController: NavHostController) {
    val authViewModel = viewModel<AuthViewModel>(
        factory = viewModelFactory {
            AuthViewModel(BaseApplication.appModule.authRepository)
        }
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Custom DI",
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
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Arrow back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            TextButton(onClick = {
                authViewModel.login(
                    LoginRequest(
                        email = "levonhakobyan9@gmail.com",
                        password = "1234567"
                    )
                )
            }) {
                Text(
                    text = "Login",
                    style = MaterialTheme.typography.titleMedium,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}