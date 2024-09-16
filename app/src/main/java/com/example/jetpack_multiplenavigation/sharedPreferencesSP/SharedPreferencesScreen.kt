package com.example.jetpack_multiplenavigation.sharedPreferencesSP

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.jetpack_multiplenavigation.R

@SuppressLint("CommitPrefEdits")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SharedPreferencesScreen(navController: NavHostController) {
    val context = (LocalContext.current as Activity)
    val keyBoardController = LocalSoftwareKeyboardController.current
    val sharedPref = context.getSharedPreferences(context.resources.getString(R.string.preference_file_key),  Context.MODE_PRIVATE)
    val editor: SharedPreferences.Editor = sharedPref.edit()
    val key = context.resources.getString(R.string.preference_file_key)
    var textMessage by remember {
        mutableStateOf("")
    }
    var savedText by remember {
        mutableStateOf("")
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Shared Preferences",
                        style = MaterialTheme.typography.headlineSmall,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
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
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            OutlinedTextField(
                value = textMessage,
                onValueChange = {
                    textMessage = it
                },
                placeholder = {
                    Text(
                        text = "Enter text",
                        fontFamily = FontFamily.Serif
                    )
                },
                maxLines = 1,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Button(onClick = {
                    keyBoardController?.hide()
                    editor.putString(key, textMessage)
                    editor.apply()
                }) {
                    Text(
                        text = "Save text",
                        fontFamily = FontFamily.Serif
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(onClick = {
                    keyBoardController?.hide()
                    savedText = sharedPref.getString(key, "").toString()
                }) {
                    Text(
                        text = "Show text",
                        fontFamily = FontFamily.Serif
                    )
                }
            }
            Spacer(modifier = Modifier.height(25.dp))
            Text(
                text = savedText,
                style = MaterialTheme.typography.headlineMedium,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}