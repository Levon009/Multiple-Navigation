package com.example.jetpack_multiplenavigation.cipherManager.screens

import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.jetpack_multiplenavigation.cipherManager.CipherManager
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EncryptDecryptScree(navController: NavHostController) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val cipherManager = CipherManager()
    var encryptedText by remember {
        mutableStateOf("")
    }
    var decryptedText by remember {
        mutableStateOf("")
    }
    var isEncrypted by remember {
        mutableStateOf(false)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Encrypt_Decrypt Text",
                        color = Color.White,
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
                            contentDescription = "Arrow back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black
                )
            )
        }
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(bottom = 75.dp)
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.Black)
        ) {
            OutlinedTextField(
                value = encryptedText,
                onValueChange = {
                    encryptedText = it
                },
                label = {
                    Text(
                        text = "Enter your text",
                        fontFamily = FontFamily.Serif
                    )
                },
                maxLines = 1,
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                ElevatedButton(
                    onClick = {
                        keyboardController?.hide()
                        if (!isEncrypted) {
                            val bytes = encryptedText.encodeToByteArray()
                            val file = File(context.applicationContext.filesDir, "LionSecret.txt")
                            if (!file.exists()) {
                                file.createNewFile()
                            }
                            val fos = FileOutputStream(file)
                            decryptedText = cipherManager.encrypt(
                                bytes,
                                fos
                            ).decodeToString()
                            isEncrypted = true
                        } else {
                            Toast.makeText(context, "Security exception!!!", Toast.LENGTH_SHORT).show()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add"
                    )
                    Text(
                        text = "Encrypt",
                        fontFamily = FontFamily.Serif
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                ElevatedButton(
                    onClick = {
                        keyboardController?.hide()
                        val file = File(context.applicationContext.filesDir, "LionSecret.txt")
                        encryptedText = cipherManager
                            .decrypt(FileInputStream(file))
                            .decodeToString()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add",
                    )
                    Text(
                        text = "Encrypt",
                        fontFamily = FontFamily.Serif
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = decryptedText,
                color = Color.White,
                style = MaterialTheme.typography.titleMedium,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
        }
    }
}