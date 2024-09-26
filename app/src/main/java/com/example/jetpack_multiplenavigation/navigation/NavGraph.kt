package com.example.jetpack_multiplenavigation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.draf.personsListFull.PersonsListScreen
import com.example.draf.students.data.model.Student
import com.example.jetpack_multiplenavigation.authentication.presentation.screens.AuthorizationScreen
import com.example.jetpack_multiplenavigation.authentication.presentation.screens.LoginScreen
import com.example.jetpack_multiplenavigation.authentication.presentation.screens.RegisterScreen
import com.example.jetpack_multiplenavigation.bindedServiceTimer.TimerServiceScreen
import com.example.jetpack_multiplenavigation.bottomTaskBar.TaskBar
import com.example.jetpack_multiplenavigation.broadcastDynamicReceiver.BroadcastReceiverScreen
import com.example.jetpack_multiplenavigation.chat.screens.ChatFcmScreen
import com.example.jetpack_multiplenavigation.cipherManager.screens.EncryptDecryptScree
import com.example.jetpack_multiplenavigation.circleAnimation.CircleAnimationScreen
import com.example.jetpack_multiplenavigation.coil.CoilScreens
import com.example.jetpack_multiplenavigation.constraintsLayout.ConstraintsLayoutScreen
import com.example.jetpack_multiplenavigation.contactsRoom1.contactsScreen.ContactsScreen
import com.example.jetpack_multiplenavigation.contentProvider.contacts.screens.PhoneContactsScreen
import com.example.jetpack_multiplenavigation.contentProvider.media.screens.MediaContentProviderScreen
import com.example.jetpack_multiplenavigation.customNavArgs.CustomNavArgs
import com.example.jetpack_multiplenavigation.customNavArgs.data.model.BreedSize
import com.example.jetpack_multiplenavigation.customNavArgs.data.model.Dog
import com.example.jetpack_multiplenavigation.customNavArgs.screens.DogDetailScreen
import com.example.jetpack_multiplenavigation.customNavArgs.screens.DogListScreen
import com.example.jetpack_multiplenavigation.daggerCustom.authentication.screens.CustomDIScreen
import com.example.jetpack_multiplenavigation.dependencies.screens.KoinScreen
import com.example.jetpack_multiplenavigation.dialogs.DialogsScreen
import com.example.jetpack_multiplenavigation.downloadManager.screens.ImageDownloadManagerScreen
import com.example.jetpack_multiplenavigation.dragDropListItem.DragDropListScreen
import com.example.jetpack_multiplenavigation.drawerNavigation.DrawerNavigation
import com.example.jetpack_multiplenavigation.editor.EditorScreen
import com.example.jetpack_multiplenavigation.emailPreferences.EmailScreen
import com.example.jetpack_multiplenavigation.employee_dependencies.screens.EmployeesScreen
import com.example.jetpack_multiplenavigation.expandableBox.ExpandableBox
import com.example.jetpack_multiplenavigation.expendableList.ExpendedLanguagesScreen
import com.example.jetpack_multiplenavigation.expendableMenu.PersonsScreen
import com.example.jetpack_multiplenavigation.home.HomeScreen
import com.example.jetpack_multiplenavigation.instagram.InstagramScreen
import com.example.jetpack_multiplenavigation.intents.IntentsScreen
import com.example.jetpack_multiplenavigation.listCarsFull.screens.CarsListScreen
import com.example.jetpack_multiplenavigation.listSwipeDropMenu.LanguagesScreen
import com.example.jetpack_multiplenavigation.loading.LoadingScreen
import com.example.jetpack_multiplenavigation.magnifierSection.MagnifierSection
import com.example.jetpack_multiplenavigation.meditation.MeditationScreen
import com.example.jetpack_multiplenavigation.music.MusicHandler
import com.example.jetpack_multiplenavigation.notes.presentation.add_edit_note.AddEditNoteScreen
import com.example.jetpack_multiplenavigation.notes.presentation.notes.NotesScreen
import com.example.jetpack_multiplenavigation.notifications.NotificationsScreen
import com.example.jetpack_multiplenavigation.optionMenu.OptionMenu
import com.example.jetpack_multiplenavigation.permissions.screens.PermissionsScreen
import com.example.jetpack_multiplenavigation.pet.detail.PetDetailScreen
import com.example.jetpack_multiplenavigation.pet.home.PetsHomeScreen
import com.example.jetpack_multiplenavigation.photoCompressionWork.PhotoCompressionWorkScreen
import com.example.jetpack_multiplenavigation.pickSaveImage.PickSaveImage
import com.example.jetpack_multiplenavigation.produceState.ProduceStateScreen
import com.example.jetpack_multiplenavigation.products.retrofit.productsUI.UpdateProductScreen
import com.example.jetpack_multiplenavigation.radioBCheckBSwitch.CheckBRadioBSwitchScreen
import com.example.jetpack_multiplenavigation.school.screens.SchoolScreen
import com.example.jetpack_multiplenavigation.search.SearchScreen
import com.example.jetpack_multiplenavigation.search2.SearchScreen2
import com.example.jetpack_multiplenavigation.simpleAnimation.SimpleAnimation
import com.example.jetpack_multiplenavigation.snackBarSB.screens.SnackBarScreen
import com.example.jetpack_multiplenavigation.snapshotFlow.SnapshotFlowScreen
import com.example.jetpack_multiplenavigation.stats.Stats
import com.example.jetpack_multiplenavigation.students.screensUI.AddStudent
import com.example.jetpack_multiplenavigation.students.screensUI.StudentsList
import com.example.jetpack_multiplenavigation.students.screensUI.UpdateStudent
import com.example.jetpack_multiplenavigation.students.studentNavArgs.StudentsNavArgs
import com.example.jetpack_multiplenavigation.textFields.TextFieldsScreen
import com.example.jetpack_multiplenavigation.timer.Timer
import com.example.jetpack_multiplenavigation.products.screens.ProductsScreen
import com.example.jetpack_multiplenavigation.sharedPreferencesSP.SharedPreferencesScreen
import com.example.jetpack_multiplenavigation.swipeWithActions.presentation.screens.CustomSwipeScreen
import com.example.jetpack_multiplenavigation.textPrinter.TextPrinterScree
import com.example.jetpack_multiplenavigation.uploadFileRetrofit.screens.FileUploadScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.Route
import org.koin.compose.KoinContext
import kotlin.reflect.typeOf

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val scope = rememberCoroutineScope()
    KoinContext {
        SharedTransitionLayout {
            NavHost(
                navController = navController,
                startDestination = Routes.HomeScreen
            ) {
                composable<Routes.HomeScreen>(
                    enterTransition = {
                        return@composable fadeIn(tween(1000))
                    }, exitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }, popEnterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }
                ) {
                    HomeScreen(navController = navController) {
                        when (it) {
                            1 -> {
                                navController.navigate(Routes.PetListScreen)
                            }
                            2 -> {
                                navController.navigate(
                                    Routes.MeditationScreen(
                                        firstName = "Levon"
                                    )
                                )
                            }
                            3 -> {
                                scope.launch {
                                    delay(3100L)
                                    navController.navigate(
                                        Routes.InstagramUserScreen(
                                            name = "Levon",
                                            age = 34
                                        )
                                    )
                                }
                            }
                            4 -> {
                                navController.navigate(Routes.StatSection)
                            }
                        }
                    }
                }
                composable<Routes.StatSection>(
                    enterTransition = {
                        return@composable fadeIn(tween(1000))
                    }, exitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }, popEnterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }
                ) {
                    Stats {
                        navController.navigateUp()
                    }
                }
                composable<Routes.MeditationScreen>(
                    enterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }, exitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }
                ) {
                    val args = it.toRoute<Routes.MeditationScreen>()
                    MeditationScreen(
                        name = args.firstName,
                        navController = navController,
                        modifier = modifier,
                    ) {
                        when (it) {
                            0 -> navController.navigateUp()
                            1 -> {
                                navController.navigate(
                                    Routes.MusicSection(
                                        barsCount = 20
                                    )
                                )
                            }
                            2 -> {
                                navController.navigate(Routes.CustomSwipe) {
                                    popUpTo<Routes.MeditationScreen>() {
                                        inclusive = false
                                    }
                                }
                            }
                            3 -> navController.navigate(Routes.ExpandableBox(text = "This is now revealed."))
                            4 -> navController.navigate(Routes.TimerSection)
                        }
                    }
                }
                composable<Routes.SearchScreen>(
                    enterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }, exitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }
                ) {
                    SearchScreen(
                        navController = navController,
                        modifier = modifier
                    )
                }
                composable<Routes.InstagramUserScreen>(
                    enterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }, exitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }
                ) {
                    val args = it.toRoute<Routes.InstagramUserScreen>()
                    InstagramScreen(
                        name = args.name,
                        age = args.age,
                        navController = navController,
                        modifier = modifier
                    )
                }
                composable<Routes.LoadingScreen>(
                    enterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }, exitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }
                ) {
                    LoadingScreen(
                        percentage = 0.8f,
                        num = 100,
                        nanController = navController,
                    )
                }
                composable<Routes.TimerSection>(
                    enterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }, exitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }
                ) {
                    Timer(
                        totalTime = 100 * 1000L,
                        initialValue = 1f,
                        strokeWidth = 7.dp,
                        circleColor = Color.Green,
                        activeBarColor = Color(0xFF378900),
                        inactiveBarColor = Color.DarkGray,
                        navController = navController,
                        modifier = modifier.size(200.dp)
                    )
                }
                composable<Routes.MusicSection>(
                    enterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }, exitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }
                ) {
                    val args = it.toRoute<Routes.MusicSection>()
                    MusicHandler(
                        barsCount = args.barsCount,
                        navController = navController
                    )
                }
                composable<Routes.BottomTaskBarSection>(
                    enterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }, exitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }
                ) {
                    TaskBar(
                        navController = navController,
                        modifier = modifier
                    )
                }
                composable<Routes.ExpandableBox>(
                    enterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(799)
                        )
                    }, exitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }
                ) {
                    val args = it.toRoute<Routes.ExpandableBox>()
                    ExpandableBox(
                        text = args.text,
                        navController = navController,
                        modifier = modifier
                    )
                }
                composable<Routes.DrawerNavigationScreen>(
                    enterTransition = {
                        return@composable fadeIn(tween(300))
                    }, popEnterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(300)
                        )
                    }, exitTransition = {
                        return@composable fadeOut(tween(300))
                    }, popExitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(300)
                        )
                    }
                ) {
                    val args = it.toRoute<Routes.DrawerNavigationScreen>()
                    DrawerNavigation(
                        name = args.name,
                        navController = navController
                    )
                }
                composable<Routes.MagnifierSection>(
                    enterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    },
                    exitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }
                ) {
                    MagnifierSection(
                        navController = navController,
                    )
                }
                composable<Routes.CheckBoxRadioBSwitch>(
                    enterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }, exitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }
                ) {
                    CheckBRadioBSwitchScreen(navController = navController,)
                }
                composable<Routes.SnapshotFlowSection>(
                    enterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }, exitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }
                ) {
                    SnapshotFlowScreen(navController = navController)
                }
                composable<Routes.TextFieldsSection>(
                    enterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }, exitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }
                ) {
                    TextFieldsScreen(navController = navController)
                }
                composable<Routes.ProduceStateSection>(
                    enterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }, exitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }
                ) {
                    ProduceStateScreen(navController = navController)
                }
                composable<Routes.Search2Section>(
                    enterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }, exitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }
                ) {
                    SearchScreen2(navController = navController)
                }
                composable<Routes.SimpleAnimation>(
                    enterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }, exitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }
                ) {
                    SimpleAnimation(
                        navController = navController,
                        modifier = modifier
                    )
                }
                composable<Routes.OptionMenu>(
                    enterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }, exitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }
                ) {
                    val args = it.toRoute<Routes.OptionMenu>()
                    OptionMenu(
                        settings = args.settings,
                        logOut = args.logOut,
                        navController = navController
                    )
                }
                composable<Routes.PetListScreen>(
                    enterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }, exitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }
                ) {
                    PetsHomeScreen(
                        sharedTransitionScope = this@SharedTransitionLayout,
                        animatedVisibilityScope = this,
                        navController = navController
                    ) { id ->
                        navController.navigate(Routes.PetDetailsScreen(id))
                    }
                }
                composable<Routes.PetDetailsScreen>(
                    enterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }, exitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }
                ) {
                    val args = it.toRoute<Routes.PetDetailsScreen>()
                    PetDetailScreen(
                        index = args.id,
                        sharedTransitionScope = this@SharedTransitionLayout,
                        animatedVisibilityScope = this
                    ) {
                        navController.navigateUp()
                    }
                }
                composable<Routes.Editor>(
                    enterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }, exitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }
                ) {
                    EditorScreen()
                }
                composable<Routes.PersonsList>(
                    enterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }, exitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }
                ) {
                    val args = it.toRoute<Routes.PersonsList>()
                    PersonsScreen(
                        id = args.id,
                        navController = navController
                    )
                }
                composable<Routes.SwipeDropMenuList>(
                    enterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }, exitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }
                ) {
                    LanguagesScreen(
                        navController = navController,
                    )
                }
                composable<Routes.DragDropListItemSection>(
                    enterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }, exitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }
                ) {
                    DragDropListScreen(navController = navController)
                }
                composable<Routes.PersonsListFull>(
                    enterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }, exitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }
                ) {
                    val args = it.toRoute<Routes.PersonsListFull>()
                    PersonsListScreen(
                        supportingText = args.supportingText,
                        navController = navController,
                    )
                }
                composable<Routes.ExpendableList>(
                    enterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }, exitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }
                ) {
                    ExpendedLanguagesScreen(navController = navController)
                }
                composable<Routes.Dialogs>(
                    enterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }, exitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }
                ) {
                    DialogsScreen(
                        navController = navController,
                        modifier = modifier
                    )
                }
                composable<Routes.Products>(
                    enterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }, exitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }
                ) {
                    ProductsScreen(navController = navController)
                }
                composable<Routes.PostProducts>(
                    enterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }, exitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }
                ) {
                    UpdateProductScreen(navController = navController,)
                }
                composable<Routes.CoilFull>(
                    enterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }, exitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }
                ) {
                    //val args = it.toRoute<Routes.CoilFull>()
                    CoilScreens(navController = navController)
                }
                composable<Routes.DogListRoute>(
                    enterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }, exitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }
                ) {
                    DogListScreen(
                        navController = navController,
                        onItemClick = { dog, breedSize ->
                            navController.navigate(
                                Routes.DogDetailRoute(
                                    dog = dog,
                                    breedSize = breedSize
                                )
                            )
                        }
                    )
                }
                composable<Routes.DogDetailRoute>(
                    typeMap = mapOf(
                        typeOf<Dog>() to CustomNavArgs.DogType,
                        typeOf<BreedSize>() to NavType.EnumType(BreedSize::class.java)
                    ),
                    enterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }, exitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }
                ) {
                    val args = it.toRoute<Routes.DogDetailRoute>()
                    DogDetailScreen(
                        dog = args.dog,
                        breedSize = args.breedSize,
                        navController = navController
                    )
                }
                composable<Routes.CarsListFull>(
                    enterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }, exitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }
                ) {
                    CarsListScreen(navController = navController)
                }
                composable<Routes.PickSaveImage>(
                    enterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }, exitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }
                ) {
                    PickSaveImage(navController = navController)
                }
                composable<Routes.Koin1>(
                    enterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }, exitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }
                ) {
                    KoinScreen(navController = navController)
                }
                composable<Routes.EmployeeDI>(
                    enterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }, exitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }
                ) { 
                    EmployeesScreen(navController = navController)
                }
                composable<Routes.AddStudentRoute>(
                    enterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }, exitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }
                ) {
                    AddStudent(navController = navController)
                }
                composable<Routes.StudentsListRoute>(
                    enterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }, exitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }
                ) {
                    StudentsList(navController = navController)
                }
                composable<Routes.UpdateStudentRoute>(
                    typeMap = mapOf(
                        typeOf<Student>() to StudentsNavArgs.StudentsNavType
                    ),
                    enterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }, exitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }
                ) {
                    val args = it.toRoute<Routes.UpdateStudentRoute>()
                    UpdateStudent(
                        navController = navController,
                        student = args.student
                    )
                }
                composable<Routes.ContactsRoom1>(
                    enterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }, exitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }
                ) { 
                    ContactsScreen(navController = navController)
                }
                composable<Routes.School>(
                    enterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }, exitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }
                ) {
                    SchoolScreen(navController = navController)
                }
                composable<Routes.SnackBarSB>(
                    enterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }, exitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }
                ) {
                    SnackBarScreen(navController = navController)
                }
                composable<Routes.ImageDownloadManager>(
                    enterTransition = {
                        return@composable fadeIn(tween(1000))
                    }, popEnterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(1000)
                        )
                    }, exitTransition = {
                        return@composable fadeOut(tween(1000))
                    }, popExitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(1000)
                        )
                    }
                ) {
                    ImageDownloadManagerScreen(navController = navController)
                }
                composable<Routes.Intents>(
                    enterTransition = {
                        return@composable fadeIn(tween(1000))
                    }, popEnterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(1000)
                        )
                    }, exitTransition = {
                        return@composable fadeOut(tween(1000))
                    }, popExitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(1000)
                        )
                    }
                ) {
                    val args = it.toRoute<Routes.Intents>()
                    IntentsScreen(
                        uriStr = args.uri,
                        navController = navController
                    )
                }
                composable<Routes.UploadFileRetro>(
                    enterTransition = {
                        return@composable fadeIn(tween(1000))
                    }, popEnterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(1000)
                        )
                    }, exitTransition = {
                        return@composable fadeOut(tween(1000))
                    }, popExitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(1000)
                        )
                    }
                ) {
                    FileUploadScreen(navController = navController)
                }
                composable<Routes.PhotoCompressionWork>(
                    enterTransition = {
                        return@composable fadeIn(tween(1000))
                    }, popEnterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(1000)
                        )
                    }, exitTransition = {
                        return@composable fadeOut(tween(1000))
                    }, popExitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(1000)
                        )
                    }
                ) {
                    val args = it.toRoute<Routes.PhotoCompressionWork>()
                    PhotoCompressionWorkScreen(
                        uriString = args.uriString,
                        navController = navController
                    )
                }
                composable<Routes.TimerServiceTS>(
                    enterTransition = {
                        return@composable fadeIn(tween(700))
                    }, popEnterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }, exitTransition = {
                        return@composable fadeOut(tween(1000))
                    }, popExitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(1000)
                        )
                    }
                ) {
                    TimerServiceScreen(navController = navController)
                }
                composable<Routes.ChatFcm>(
                    enterTransition = {
                        return@composable fadeIn(tween(700))
                    }, popEnterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }, exitTransition = {
                        return@composable fadeOut(tween(700))
                    }, popExitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }
                ) { 
                    ChatFcmScreen(navController = navController)
                }
                composable<Routes.EncryptDecrypt>(
                    enterTransition = {
                        return@composable fadeIn(tween(700))
                    }, popEnterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }, exitTransition = {
                        return@composable fadeOut(tween(700))
                    }, popExitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }
                ) {  
                    EncryptDecryptScree(navController = navController)
                }
                composable<Routes.SharePreferencesSP>(
                    enterTransition = {
                        return@composable fadeIn(tween(700))
                    }, popEnterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }, exitTransition = {
                        return@composable fadeOut(tween(700))
                    }, popExitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }
                ) {
                    SharedPreferencesScreen(navController = navController)
                }
                composable<Routes.SingleMultiplePermissions>(
                    enterTransition = {
                        return@composable fadeIn(tween(700))
                    }, popEnterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }, exitTransition = {
                        return@composable fadeOut(tween(700))
                    }, popExitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }
                ) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM) {
                        PermissionsScreen(navController = navController)
                    }
                }
                composable<Routes.TextPrinter>(
                    enterTransition = {
                        return@composable fadeIn(tween(700))
                    }, popEnterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }, exitTransition = {
                        return@composable fadeOut(tween(700))
                    }, popExitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }
                ) {
                    TextPrinterScree(navController = navController)
                }
                composable<Routes.BroadcastReceivers>(
                    enterTransition = {
                        return@composable fadeIn(tween(700))
                    }, popEnterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }, exitTransition = {
                        return@composable fadeOut(tween(700))
                    }, popExitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }
                )  {
                    BroadcastReceiverScreen(navController = navController)
                }
                composable<Routes.Notifications>(
                    enterTransition = {
                        return@composable fadeIn(tween(700))
                    }, popEnterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }, exitTransition = {
                        return@composable fadeOut(tween(700))
                    }, popExitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }
                ) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        NotificationsScreen(navController = navController)
                    }
                }
                composable<Routes.MediaContents>(
                    enterTransition = {
                        return@composable fadeIn(tween(700))
                    }, popEnterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }, exitTransition = {
                        return@composable fadeOut(tween(700))
                    }, popExitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }
                ) {
                    MediaContentProviderScreen(navController = navController)
                }
                composable<Routes.ContactsContents>(
                    enterTransition = {
                        return@composable fadeIn(tween(700))
                    }, popEnterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }, exitTransition = {
                        return@composable fadeOut(tween(700))
                    }, popExitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }
                ) { 
                    PhoneContactsScreen(navController = navController)
                }
                composable<Routes.DaggerCustom>(
                    enterTransition = {
                        return@composable fadeIn(tween(700))
                    }, popEnterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }, exitTransition = {
                        return@composable fadeOut(tween(700))
                    }, popExitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }
                ) {
                    CustomDIScreen(navController = navController)
                }
                composable<Routes.EmailPreferences>(
                    enterTransition = {
                        return@composable fadeIn(tween(700))
                    }, popEnterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }, exitTransition = {
                        return@composable fadeOut(tween(700))
                    }, popExitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }
                ) {
                    EmailScreen(navController = navController)
                }
                composable<Routes.Authorization>(
                    enterTransition = {
                        return@composable fadeIn(tween(700))
                    }, popEnterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }, exitTransition = {
                        return@composable fadeOut(tween(700))
                    }, popExitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }
                ) {
                    AuthorizationScreen(navController = navController)
                }
                composable<Routes.RegisterUser>(
                    enterTransition = {
                        return@composable fadeIn(tween(700))
                    }, popEnterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }, exitTransition = {
                        return@composable fadeOut(tween(700))
                    }, popExitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }
                ) {
                    RegisterScreen(navController = navController)
                }
                composable<Routes.LoginUser>(
                    enterTransition = {
                        return@composable fadeIn(tween(700))
                    }, popEnterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }, exitTransition = {
                        return@composable fadeOut(tween(700))
                    }, popExitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }
                ) {
                    LoginScreen(navController = navController)
                }
                composable<Routes.Notes>(
                    enterTransition = {
                        return@composable fadeIn((tween(700)))
                    }, popEnterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }, exitTransition = {
                        return@composable fadeOut(tween(700))
                    }, popExitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }
                ) {
                    NotesScreen(navController = navController)
                }
                composable<Routes.AddEditNotes>(
                    enterTransition = {
                        return@composable fadeIn((tween(700)))
                    }, popEnterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }, exitTransition = {
                        return@composable fadeOut(tween(700))
                    }, popExitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }
                ) {
                    val args = it.toRoute<Routes.AddEditNotes>()
                    AddEditNoteScreen(
                        navController = navController,
                        noteColor = args.noteColor
                    )
                }
                composable<Routes.CustomSwipe>(
                    enterTransition = {
                        return@composable fadeIn(tween(700))
                    }, popEnterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }, exitTransition = {
                        return@composable fadeOut(tween(700))
                    }, popExitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }
                ) {
                    CustomSwipeScreen(navController = navController)
                }
                composable<Routes.Constraints>(
                    enterTransition = {
                        return@composable fadeIn(tween(700))
                    }, popEnterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }, exitTransition = {
                        return@composable fadeOut(tween(700))
                    }, popExitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }
                ) {
                    ConstraintsLayoutScreen(navController = navController)
                }
                composable<Routes.CircleAnimation>(
                    enterTransition = {
                        return@composable fadeIn(tween(700))
                    }, popEnterTransition = {
                        return@composable slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                        )
                    }, exitTransition = {
                        return@composable fadeOut(tween(700))
                    }, popExitTransition = {
                        return@composable slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                        )
                    }
                ) {
                    CircleAnimationScreen(navController = navController)
                }
            }
        }
    }
}