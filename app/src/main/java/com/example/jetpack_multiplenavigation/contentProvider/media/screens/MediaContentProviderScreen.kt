package com.example.jetpack_multiplenavigation.contentProvider.media.screens

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.jetpack_multiplenavigation.contentProvider.media.mediaContentProvider
import com.example.jetpack_multiplenavigation.contentProvider.media.presentation.MediaContentViewModel
import com.example.jetpack_multiplenavigation.navigation.Routes
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediaContentProviderScreen(navController: NavHostController) {
    val context = LocalContext.current
    val mediaContentViewModel = viewModel<MediaContentViewModel>()
    val alpha = remember {
        Animatable(0f)
    }

    var isPermissionGranted by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        )
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        isPermissionGranted = isGranted
    }
    LaunchedEffect(key1 = isPermissionGranted) {
        delay(1000)
        if (!isPermissionGranted) {
            launcher.launch(
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        } else {
            alpha.animateTo(
                targetValue = 1f,
                tween(500)
            )
            mediaContentProvider(
                mediaContentViewModel = mediaContentViewModel,
                context = context
            )
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Routes.ContactsContents)
            }) {
                Icon(
                    imageVector = Icons.Default.Contacts,
                    contentDescription = "Contacts"
                )
            }
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Gallery",
                        color = Color.White,
                        style = MaterialTheme.typography.headlineMedium,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 10.dp)
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
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = Color.Black
        ) {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                items(mediaContentViewModel.images) { image ->
                    AsyncImage(
                        model = ImageRequest.Builder(context)
                            .data(image.uri)
                            .crossfade(true)
                            .crossfade(2000)
                            .transformations(CircleCropTransformation())
                            .build(),
                        alpha = alpha.value,
                        contentDescription = image.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .border(1.dp, Color.White, CircleShape)
                            .padding(3.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}