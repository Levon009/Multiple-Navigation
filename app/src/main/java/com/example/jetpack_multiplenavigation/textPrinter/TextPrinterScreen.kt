package com.example.jetpack_multiplenavigation.textPrinter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextPrinterScree(navController: NavHostController) {
    val textPrinterViewModel = viewModel<TextPrinterViewModel>()
    val textMessage = "Hello my friend how are you? \n Wish you the best in this new year..."
    val counter = textMessage.toCharArray().size
    LaunchedEffect(key1 = textPrinterViewModel.textChannel) {
        textPrinterViewModel.textChannel.collectLatest { c ->
            textPrinterViewModel.updateCharacters(c)
        }
    }
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
                            contentDescription = "Arrow back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Button(onClick = {
                textPrinterViewModel.typeText(textMessage = textMessage,)
            }) {
                Text(
                    text = "Type text",
                    fontFamily = FontFamily.Serif
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = textPrinterViewModel.characters.toString(),
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.Serif,
                letterSpacing = 0.7.sp,
                lineHeight = 35.sp
            )
        }
    }
}