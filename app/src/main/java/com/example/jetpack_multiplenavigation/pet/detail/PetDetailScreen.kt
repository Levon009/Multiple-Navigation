package com.example.jetpack_multiplenavigation.pet.detail

import android.app.Activity
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jetpack_multiplenavigation.navigation.Routes
import com.example.jetpack_multiplenavigation.pet.data.PetDataSource
import com.example.jetpack_multiplenavigation.pet.detail.imageItem.ImageItem
import com.example.jetpack_multiplenavigation.pet.detail.menuItems.MenuItem1
import com.example.jetpack_multiplenavigation.pet.detail.menuItems.MenuItem2
import com.example.jetpack_multiplenavigation.pet.detail.petBasicInfoItem.PetBasicInfoItem
import com.example.jetpack_multiplenavigation.pet.detail.petInfoItem.PetInfoItem

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PetDetailScreen(
    navController: NavHostController,
    index: Int,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onNavigate: () -> Unit
) {
    val context = (LocalContext.current as? Activity)
    var showMore by remember {
        mutableStateOf(false)
    }
    with(sharedTransitionScope) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Detail",
                            fontSize = 22.sp,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            onNavigate.invoke()
                        }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Arrow back"
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            Toast.makeText(context, "Favorite", Toast.LENGTH_SHORT).show()
                        }) {
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = "Favorite"
                            )
                        }
                        IconButton(onClick = {
                            showMore = !showMore
                        }) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "More vert"
                            )
                        }
                        DropdownMenu(
                            expanded = showMore,
                            onDismissRequest = {
                                showMore = false
                            }
                        ) {
                            MenuItem1 {
                                showMore = false
                                navController.navigate(Routes.WebSockets(1)) {
                                    popUpTo<Routes.HomeScreen>() {
                                        inclusive = false
                                    }
                                }
                            }
                            MenuItem2 {
                                showMore = false
                                context?.finish()
                            }
                        }
                    }
                )
            }
        ) { paddingValues ->
            val pet = PetDataSource.petList[index]
            LazyColumn(contentPadding = paddingValues) {
                item {
                    ImageItem(
                        painter = painterResource(id = pet.image),
                        modifier = Modifier
                            .fillMaxWidth()
                            .sharedElement(
                                state = rememberSharedContentState(key = "image/${pet.id}"),
                                animatedVisibilityScope = animatedVisibilityScope
                            )
                            .height(350.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    PetBasicInfoItem(
                        name = pet.name,
                        gender = pet.gender,
                        location = pet.location,
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                }
                item {
                    PetInfoItem(pet = pet)
                }
            }
        }
    }
}