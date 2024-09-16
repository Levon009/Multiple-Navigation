package com.example.jetpack_multiplenavigation.intents

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.result.PickVisualMediaRequest
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.jetpack_multiplenavigation.pickSaveImage.ImageSelection
import com.example.jetpack_multiplenavigation.pickSaveImage.ImageViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IntentsScreen(
    uriStr: String,
    navController: NavHostController
) {
    val context = LocalContext.current
    val imageViewModel = koinViewModel<ImageViewModel>()
    var isImageSelected by remember {
        mutableStateOf(false)
    }
    val launcher = ImageSelection(imageViewModel)
    if (!isImageSelected) {
        val uri = Uri.parse(uriStr)
        imageViewModel.updateUri(uri)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Intents",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        imageViewModel.updateIsReceive(false)
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
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (isImageSelected) {
                ImageSelection(imageViewModel = imageViewModel)
            }
            imageViewModel.uri?.let {
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(it)
                        .transformations(CircleCropTransformation())
                        .crossfade(true)
                        .crossfade(2000)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier.size(300.dp)
                )
            }.also {
                if (imageViewModel.uri == null) {
                    CircularProgressIndicator()
                }
            }
            Button(onClick = {
                Intent(Intent.ACTION_MAIN).also {
                    it.`package` = "com.google.android.youtube"
                    try {
                        context.startActivity(it)
                    } catch (e: ActivityNotFoundException) {
                        e.printStackTrace()
                    }
                }
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
                Text(
                    text = "Open Youtuber",
                    fontFamily = FontFamily.Serif
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = {
                (context as Activity).openAppSettings(context)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
                Text(
                    text = "Go To App Settings",
                    fontFamily = FontFamily.Serif
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = {
                mailWithText(
                    context,
                    emailReceiver = arrayOf("levonhakobyan9@gmail.com", "levonhakobyan1989@gmail.com"),
                    title = "Email from app",
                    message = "Hello World!!!"
                )
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
                Text(
                    text = "Send Email",
                    fontFamily = FontFamily.Serif
                )
            }
            Button(onClick = {
                imageViewModel.uri?.let {
                    mailWithFile(
                        context,
                        emailReceiver = arrayOf("levonhakobyan9@gmail.com", "levonhakobyan1989@gmail.com"),
                        title = "Email with file",
                        message = "Email with Text and Image",
                        uri = it
                    )
                }
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
                Text(
                    text = "Send Email With File",
                    fontFamily = FontFamily.Serif
                )
            }
            Button(onClick = {
                launcher.launch(
                    PickVisualMediaRequest()
                )
                isImageSelected = !isImageSelected
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
                Text(
                    text = "Select Image",
                    fontFamily = FontFamily.Serif
                )
            }
        }
    }
}

private fun mailWithText(
    context: Context,
    emailReceiver: Array<String>,
    title: String,
    message: String
) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain" // " image/* "
        putExtra(Intent.EXTRA_EMAIL, emailReceiver)
        putExtra(Intent.EXTRA_SUBJECT, title)
        putExtra(Intent.EXTRA_TEXT, message)
    }
    if (intent.resolveActivity(context.applicationContext.packageManager) != null) {
        context.startActivity(intent)
    }
}

private fun mailWithFile(
    context: Context,
    emailReceiver: Array<String>,
    title: String,
    message: String,
    uri: Uri
) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "*/*"
        putExtra(Intent.EXTRA_EMAIL, emailReceiver)
        putExtra(Intent.EXTRA_SUBJECT, title)
        putExtra(Intent.EXTRA_TEXT, message)
        putExtra(Intent.EXTRA_STREAM, uri)
    }
    if (intent.resolveActivity(context.applicationContext.packageManager) != null) {
        context.startActivity(Intent.createChooser(intent, "Choose email app"))
    }
}

private fun Activity.openAppSettings(context: Context) {
    Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", context.applicationContext.packageName, null)
    ).also {
        context.startActivity(it)
    }
}