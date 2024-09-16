package com.example.jetpack_multiplenavigation.emailPreferences

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

@Composable
fun EmailScreen(navController: NavHostController) {
    val context = LocalContext.current
    val keyboard = LocalSoftwareKeyboardController.current
    val scope = rememberCoroutineScope()
    val dataStore = EmailPreferences(context)
    val email = dataStore.getEmail.collectAsState(initial = "").value
    var emailTextValue by remember {
        mutableStateOf("")
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier.padding(25.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home"
                )
            }
        }
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(top = 16.dp)
        ) {
            OutlinedTextField(
                value = emailTextValue,
                onValueChange = {
                    emailTextValue = it
                },
                placeholder = {
                    Text(
                        text = "Email",
                        fontFamily = FontFamily.Serif
                    )
                },
                label = {
                    Text(
                        text = "Email",
                        fontFamily = FontFamily.Serif
                    )
                },
                maxLines = 1
            )

            Button(onClick = {
                keyboard?.hide()
                scope.launch {
                    dataStore.saveEmail(emailTextValue)
                }
            }) {
                Text(
                    text = "Save",
                    fontFamily = FontFamily.Serif,
                    textAlign = TextAlign.Center
                )
            }

            Text(
                text = email!!,
                style = MaterialTheme.typography.headlineMedium,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}