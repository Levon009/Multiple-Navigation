package com.example.jetpack_multiplenavigation.drawerNavigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jetpack_multiplenavigation.R
import com.example.jetpack_multiplenavigation.navigation.Routes
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerNavigation(
    name: String,
    navController: NavHostController
) {
    val fontFamily = FontFamily(Font(R.font.tack_one, FontWeight.Thin))
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    ModalNavigationDrawer(
        modifier = Modifier,
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    DrawerNavigationTop(
                        name = name,
                        fontFamily = fontFamily
                    )
                    DrawerNavigationBottom(
                        fontFamily = fontFamily,
                        scope = scope,
                        drawerState = drawerState
                    ) { item ->
                        when(item.title) {
                            "Home" -> {
                                scope.launch { drawerState.close() }
                                navController.navigate(Routes.HomeScreen)
                            }
                            "Room" -> {
                                navController.navigate(Routes.ContactsRoom1) {
                                    popUpTo<Routes.HomeScreen>() {
                                        inclusive = false
                                    }
                                }
                            }
                            "Room_Many" -> {
                                navController.navigate(Routes.School) {
                                    popUpTo<Routes.HomeScreen>() {
                                        inclusive = false
                                    }
                                }
                            }
                            "SnackBar" -> {
                                navController.navigate(Routes.SnackBarSB) {
                                    popUpTo<Routes.HomeScreen>() {
                                        inclusive = false
                                    }
                                }
                            }
                            "UploadFile Retro" -> {
                                navController.navigate(Routes.UploadFileRetro) {
                                    popUpTo<Routes.HomeScreen>() {
                                        inclusive = false
                                    }
                                }
                            }
                            "TimerService" -> {
                                navController.navigate(Routes.TimerServiceTS) {
                                    popUpTo<Routes.HomeScreen>() {
                                        inclusive = false
                                    }
                                }
                            }
                            "Shared Preferences" -> {
                                navController.navigate(Routes.SharePreferencesSP)
                            }
                            "Permissions" -> {
                                navController.navigate(Routes.SingleMultiplePermissions)
                            }
                            "Test Receiver" -> {
                                navController.navigate(Routes.BroadcastReceivers)
                            }
                        }
                    }
                }
            }
        },
        gesturesEnabled = false,
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                            Text(
                                text = "Drawer navigation",
                                color = Color.Black,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch { drawerState.close() }
                                navController.popBackStack()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Arrow back",
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
                    scope.launch { 
                        drawerState.open()
                    }
                }) {
                    Text(text = "Open drawer")
                }
            }
        }
    }
}