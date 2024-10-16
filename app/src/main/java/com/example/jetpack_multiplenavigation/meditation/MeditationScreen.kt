package com.example.jetpack_multiplenavigation.meditation

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.jetpack_multiplenavigation.fragments.presentation.FragmentsActivity
import com.example.jetpack_multiplenavigation.navigation.Routes
import com.example.jetpack_multiplenavigation.recyclerViewPayloads.presentation.RecyclerActivity
import com.example.jetpack_multiplenavigation.ui.theme.DeepBlue
import com.example.jetpack_multiplenavigation.ui.theme.LightRed
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MeditationScreen(
    modifier: Modifier = Modifier,
    name: String,
    navController: NavHostController,
    onMenuClick: (Int) -> Unit,
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var isSearchClick by remember {
        mutableStateOf(false)
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(DeepBlue)
    ) {
        if (isSearchClick) {
            LinearProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = Color.Magenta
            )
        } else {
            Column {
                GreetingsSection(name = name) {
                    isSearchClick = true
                    scope.launch {
                        delay(1500L)
                        navController.navigate(Routes.SearchScreen) {
                            popUpTo<Routes.HomeScreen>() {
                                inclusive = false
                            }
                        }
                    }
                }
                ChipsSection(chips = getChips()) { index ->
                    when (index) {
                        0 -> {
                            navController.navigate(
                                Routes.DrawerNavigationScreen(
                                    name = "Levon Hakobyan"
                                ))
                        }
                        1 -> navController.navigate(Routes.MagnifierSection)
                        2 -> navController.navigate(Routes.CheckBoxRadioBSwitch)
                        3 -> navController.navigate(Routes.SnapshotFlowSection)
                        4 -> navController.navigate(Routes.TextFieldsSection)
                        5 -> navController.navigate(Routes.ProduceStateSection(0))
                        6 -> {
                            navController.navigate(Routes.Search2Section) {
                                popUpTo<Routes.HomeScreen>() {
                                    inclusive = false
                                }
                            }
                        }
                        7 -> navController.navigate(Routes.PersonsListFull(
                            supportingText = "Person Item"
                        ))
                        8 -> navController.navigate(Routes.DragDropListItemSection)
                        9 -> navController.navigate(Routes.SwipeDropMenuList)
                        10 -> navController.navigate(Routes.PersonsList(0))
                        11 -> navController.navigate(Routes.ExpendableList)
                        12 -> {
                            navController.navigate(Routes.PickSaveImage) {
                                popUpTo<Routes.HomeScreen>() {
                                    inclusive = false
                                }
                            }
                        }
                        13 -> {
                            navController.navigate(Routes.Koin1) {
                                popUpTo<Routes.HomeScreen>() {
                                    inclusive = false
                                }
                            }
                        }
                        14 -> navController.navigate(Routes.Intents(""))
                        15 -> {
                            Intent(context, FragmentsActivity::class.java).apply {
                                context.startActivity(this)
                            }
                        }
                        16 -> navController.navigate(Routes.BottomTaskBarSection)
                        17 -> navController.navigate(Routes.Constraints)
                        18 -> navController.navigate(Routes.TimerObservable)
                        19 -> navController.navigate(Routes.LoadInitialData)
                        20 -> {
                            Intent(context, RecyclerActivity::class.java).apply {
                                context.startActivity(this)
                            }
                        }
                    }
                }
                MeditationSection(color = LightRed)
                FeatureSection(
                    features = getFeatures(),
                    navController = navController
                )
            }
            BottomMenu(
                menuItems = getMenuItems(),
                modifier = Modifier.align(Alignment.BottomCenter)
            ) { index ->
                onMenuClick(index)
            }
        }
    }
}