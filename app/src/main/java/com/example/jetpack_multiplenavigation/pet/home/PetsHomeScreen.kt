package com.example.jetpack_multiplenavigation.pet.home

import android.app.Activity
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.FireExtinguisher
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jetpack_multiplenavigation.navigation.Routes
import com.example.jetpack_multiplenavigation.pet.data.PetDataSource
import com.example.jetpack_multiplenavigation.scrollToTopButton.ScrollToTopButton

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PetsHomeScreen(
    modifier: Modifier = Modifier,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    navController: NavHostController,
    onPetClick: (Int) -> Unit
) {
    val context = (LocalContext.current  as? Activity)
    val state = rememberLazyListState()
    val petList = PetDataSource.petList
    var showMenu by remember {
        mutableStateOf(false)
    }
    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            ScrollToTopButton(state = state)
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Pets",
                        fontSize = 24.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.W600,
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
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Arrow back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate(Routes.Notifications)
                    }) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notifications"
                        )
                    }
                    IconButton(onClick = {
                        showMenu = !showMenu
                    }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "Dot menu"
                        )
                    }
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = {
                            showMenu = false
                        }
                    ) {
                        DropMenuItem1 {
                            showMenu = false
                            navController.navigate(Routes.ChatFcm)
                        }
                        DropMenuItem2 {
                            showMenu = false
                            Toast.makeText(context, "Log out", Toast.LENGTH_SHORT).show()
                            context?.finish()
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            state = state,
            contentPadding = paddingValues
        ) {
            itemsIndexed(petList) { index, pet ->
                PetInfoItem(
                    pet = pet,
                    sharedTransitionScope = sharedTransitionScope,
                    animatedVisibilityScope = animatedVisibilityScope
                ) {
                    onPetClick(index)
                }
            }
        }
    }
}

@Composable
fun DropMenuItem1(onItemClick: () -> Unit) {
    DropdownMenuItem(
        text = { Text(text = "Firebase") },
        onClick = {
            onItemClick()
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.FireExtinguisher,
                contentDescription = "Firebase"
            )
        }
    )
}

@Composable
fun DropMenuItem2(onItemClick: () -> Unit) {
    DropdownMenuItem(
        text = { Text(text = "Log out") },
        onClick = {
            onItemClick()
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                contentDescription = "Exit"
            )
        }
    )
}