package com.example.jetpack_multiplenavigation.uploadFileRetrofit.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.navigation.NavHostController
import com.example.jetpack_multiplenavigation.uploadFileRetrofit.presentation.FileUploadViewModel
import org.koin.compose.viewmodel.koinViewModel
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FileUploadScreen(navController: NavHostController) {
    val context = LocalContext.current
    val fileUploadViewModel = koinViewModel<FileUploadViewModel>()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { /*TODO*/ },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
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
            Button(onClick = {
                val file = File(context.cacheDir, "LionImg.jpg")
                file.createNewFile()
                file.outputStream().use { output->
                    context.assets.open("lion.jpg").copyTo(output)
                }
                fileUploadViewModel.uploadFile(file)
            }) {
                Text(
                    text = "Upload File",
                    fontFamily = FontFamily.Serif
                )
            }
        }
    }
}