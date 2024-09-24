package com.example.jetpack_multiplenavigation.permissions.screens

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.jetpack_multiplenavigation.permissions.PermissionTextProviderImpl
import com.example.jetpack_multiplenavigation.permissions.Permissions
import com.example.jetpack_multiplenavigation.permissions.PermissionsViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PermissionsScreen(navController: NavHostController) {
    val context = (LocalContext.current as Activity)
    val permissionsViewModel = viewModel<PermissionsViewModel>()
    val permissionsEnqueue = permissionsViewModel.visiblePermissionsRequestQueue
    val singlePermissionRequestLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        permissionsViewModel.onPermissionResult(
            permission = Permissions.permissionsList[0],
            isGranted = isGranted
        )
    }
    val multiplePermissionsRequestLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { perms ->
        Permissions.permissionsList.forEach { permission ->
            permissionsViewModel.onPermissionResult(
                permission = permission,
                isGranted = perms[permission] == true
            )
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Permissions",
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
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Favorite"
                        )
                    }
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Refresh"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    actionIconContentColor = Color.Red
                )
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Button(onClick = {
                    singlePermissionRequestLauncher.launch(
                        Permissions.permissionsList[0]
                    )
                }) {
                    Text(
                        text = "Request one permission",
                        fontFamily = FontFamily.Serif
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    multiplePermissionsRequestLauncher.launch(
                        Permissions.permissionsList
                    )
                }) {
                    Text(
                        text = "Request multiple permissions",
                        fontFamily = FontFamily.Serif
                    )
                }
            }

            permissionsEnqueue.forEach { permission ->
                PermissionDialog(
                    permissionTextProvider = when (permission) {
                        Manifest.permission.CAMERA -> PermissionTextProviderImpl("Camera")
                        Manifest.permission.RECORD_AUDIO -> PermissionTextProviderImpl("Record audio")
                        Manifest.permission.CALL_PHONE -> PermissionTextProviderImpl("Call phone")
                        else -> return@forEach
                    },
                    isPermanentlyDeclined = !context.shouldShowRequestPermissionRationale(
                        permission
                    ),
                    onDismissClick = permissionsViewModel::dismissPermission,
                    onOKClick = {
                        permissionsViewModel.dismissPermission()
                        multiplePermissionsRequestLauncher.launch(
                            Permissions.permissionsList
                        )
                    },
                    onGoToAppSettingsClick = {
                        context.goToAppSettings(context)
                    }
                )
            }
        }
    }
}

private fun Activity.goToAppSettings(context: Context) {
    Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", context.applicationContext.packageName, null)
    ).also {
        startActivity(it)
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SinglePermissionRequest(permissionsViewModel: PermissionsViewModel) {
    val scope = rememberCoroutineScope()
    val singlePermissionRequestLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        permissionsViewModel.onPermissionResult(
            permission = Permissions.permissionsList[0],
            isGranted = isGranted
        )
    }

    scope.launch {
        delay(1000)
        singlePermissionRequestLauncher.launch(
            Permissions.permissionsList[0]
        )
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MultiplePermissionRequest(permissionsViewModel: PermissionsViewModel) {
    val scope = rememberCoroutineScope()
    val multiplePermissionsRequestLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { perms ->
        Permissions.permissionsList.forEach { permission ->
            permissionsViewModel.onPermissionResult(
                permission = permission,
                isGranted = perms[permission] == true
            )
        }
    }

    scope.launch {
        delay(1000)
        multiplePermissionsRequestLauncher.launch(
            Permissions.permissionsList
        )
    }
}