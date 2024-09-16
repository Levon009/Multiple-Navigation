package com.example.jetpack_multiplenavigation.pickSaveImage

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.jetpack_multiplenavigation.navigation.Routes
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PickSaveImage(navController: NavHostController) {
    val context = LocalContext.current
    val imageViewModel = koinViewModel<ImageViewModel>()
    val singleMediaLauncher = ImageSelection(imageViewModel = imageViewModel)
    val multipleImagesLauncher = ImagesSelection(imageViewModel = imageViewModel)
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Pick Save Image",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily.Serif,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Arrow Back"
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
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState())
                            .padding(15.dp)
                    ) {
                        Button(onClick = {
                            singleMediaLauncher.launch(
                                PickVisualMediaRequest()
                            )
                        }) {
                            Text(
                                text = "Pick Image",
                                fontFamily = FontFamily.Serif
                            )
                        }
                        Spacer(modifier = Modifier.width(5.dp))
                        Button(onClick = {
                            imageViewModel.uri?.let {
                                saveImageToGallery(
                                    context = context,
                                    uri = it
                                )
                            }
                        }) {
                            Text(
                                text = "Save Image",
                                fontFamily = FontFamily.Serif
                            )
                        }
                        Spacer(modifier = Modifier.width(5.dp))
                        Button(onClick = {
                            multipleImagesLauncher.launch(
                                PickVisualMediaRequest()
                            )
                        }) {
                            Text(
                                text = "Pick Images",
                                fontFamily = FontFamily.Serif
                            )
                        }
                        Spacer(modifier = Modifier.width(5.dp))
                        Button(onClick = {
                            navController.navigate(Routes.PhotoCompressionWork(
                                uriString = imageViewModel.uri.toString()
                            ))
                        }) {
                            Text(
                                text = "Compress Image",
                                fontFamily = FontFamily.Serif
                            )
                        }
                        Spacer(modifier = Modifier.width(5.dp))
                    }
                }

                item { 
                    imageViewModel.uri?.let {
                        AsyncImage(
                            model = ImageRequest.Builder(context)
                                .data(it)
                                .crossfade(true)
                                .crossfade(2000)
                                .transformations(CircleCropTransformation())
                                .build(),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(250.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(25.dp))
                }
                
                items(imageViewModel.uris) { uri ->
                    AsyncImage(
                        model = ImageRequest.Builder(context)
                            .data(uri)
                            .crossfade(true)
                            .crossfade(2000)
                            .transformations(CircleCropTransformation())
                            .build(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(250.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}

@Composable
fun ImageSelection(imageViewModel: ImageViewModel) : ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?> {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri: Uri? ->
            uri?.let {
                imageViewModel.updateUri(uri)
                imageViewModel.updateIsReceive(true)
            }
        }
    )

    return launcher
}

@Composable
fun ImagesSelection(imageViewModel: ImageViewModel) : ManagedActivityResultLauncher<PickVisualMediaRequest, List<@JvmSuppressWildcards Uri>> {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia()
    ) { uris: List<Uri> ->
        imageViewModel.updateUris(uris)
        imageViewModel.updateIsReceive(true)
    }

    return launcher
}

private fun saveImageToGallery(context: Context, uri: Uri) {
    val inputStream = context.contentResolver.openInputStream(uri)
    val outputStream = context.openFileOutput("LionImage.jpg", Context.MODE_PRIVATE)
    inputStream?.use { input ->
        outputStream.use { output ->
            input.copyTo(output)
            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
        }
    }
}