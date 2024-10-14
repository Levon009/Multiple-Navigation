package com.example.jetpack_multiplenavigation.navigation

import com.example.jetpack_multiplenavigation.students.domain.model.Student
import com.example.jetpack_multiplenavigation.customNavArgs.data.model.BreedSize
import com.example.jetpack_multiplenavigation.customNavArgs.data.model.Dog
import kotlinx.serialization.Serializable
import okhttp3.Route

@Serializable
sealed class Routes {
    @Serializable
    data object HomeScreen : Routes()

    @Serializable
    data class InstagramUserScreen(
        val name: String,
        val age: Int
    ) : Routes()

    @Serializable
    data class MeditationScreen(
        val firstName: String
    ) : Routes()

    @Serializable
    data object SearchScreen: Routes()

    @Serializable
    data object PetListScreen : Routes()

    @Serializable
    data class PetDetailsScreen(
        val id: Int
    ) : Routes()

    @Serializable
    data object LoadingScreen : Routes()

    @Serializable
    data object StatSection : Routes()

    @Serializable
    data object TimerSection: Routes()

    @Serializable
    data class MusicSection(
        val barsCount: Int
    ) : Routes()

    @Serializable
    data object BottomTaskBarSection: Routes()

    @Serializable
    data class ExpandableBox(
        val text: String
    ) : Routes()

    @Serializable
    data class DrawerNavigationScreen(
        val name: String
    ) : Routes()

    @Serializable
    data object MagnifierSection : Routes()

    @Serializable
    data object CheckBoxRadioBSwitch : Routes()

    @Serializable
    data object SnapshotFlowSection : Routes()

    @Serializable
    data object TextFieldsSection : Routes()

    @Serializable
    data class ProduceStateSection(
        val id: Int
    ) : Routes()

    @Serializable
    data object Search2Section : Routes()

    @Serializable
    data class SimpleAnimation(
        val id: Int
    ) : Routes()

    @Serializable
    data class OptionMenu(
        val settings: String,
        val logOut: String
    ) : Routes()

    @Serializable
    data object Editor: Routes()

    @Serializable
    data class PersonsList(
        val id: Int
    ) : Routes()

    @Serializable
    data object SwipeDropMenuList : Routes()

    @Serializable
    data object DragDropListItemSection : Routes()

    @Serializable
    data class PersonsListFull(
        val supportingText: String
    ) : Routes()

    @Serializable
    data object ExpendableList : Routes()

    @Serializable
    data object Dialogs : Routes()

    @Serializable
    data object Products : Routes()

    @Serializable
    data class PostProducts(
        val id: Int
    ) : Routes()

    @Serializable
    data class CoilFull(
        val id: Int
    ) : Routes()

    @Serializable
    data object DogListRoute : Routes()

    @Serializable
    data class DogDetailRoute(
        val dog: Dog,
        val breedSize: BreedSize
    ) : Routes()

    @Serializable
    data object CarsListFull : Routes()

    @Serializable
    data object PickSaveImage : Routes()

    @Serializable
    data object Koin1 : Routes()

    @Serializable
    data object EmployeeDI : Routes()

    @Serializable
    data object AddStudentRoute : Routes()

    @Serializable
    data object StudentsListRoute : Routes()

    @Serializable
    data class UpdateStudentRoute(
        val student: Student
    ) : Routes()

    @Serializable
    data object ContactsRoom1 : Routes()

    @Serializable
    data object School : Routes()

    @Serializable
    data object SnackBarSB : Routes()

    @Serializable
    data object ImageDownloadManager : Routes()

    @Serializable
    data class Intents(
        val uri: String = ""
    ) : Routes()

    @Serializable
    data object UploadFileRetro : Routes()

    @Serializable
    data class PhotoCompressionWork(
        val uriString: String
    ) : Routes()

    @Serializable
    data object TimerServiceTS : Routes()

    @Serializable
    data object ChatFcm : Routes()

    @Serializable
    data object EncryptDecrypt : Routes()

    @Serializable
    data object SharePreferencesSP : Routes()

    @Serializable
    data object SingleMultiplePermissions : Routes()

    @Serializable
    data class TextPrinter(
        val id: Int
    ) : Routes()

    @Serializable
    data object BroadcastReceivers : Routes()

    @Serializable
    data object Notifications : Routes()

    @Serializable
    data object MediaContents : Routes()

    @Serializable
    data object ContactsContents : Routes()

    @Serializable
    data object DaggerCustom : Routes()

    @Serializable
    data object EmailPreferences : Routes()

    @Serializable
    data object RegisterUser : Routes()

    @Serializable
    data object LoginUser : Routes()

    @Serializable
    data object Authorization : Routes()

    @Serializable
    data object Notes : Routes()

    @Serializable
    data class AddEditNotes(
        val noteId: Int = -1,
        val noteColor: Int = -1
    ) : Routes()

    @Serializable
    data object CustomSwipe : Routes()

    @Serializable
    data object Constraints : Routes()

    @Serializable
    data object CircleAnimation : Routes()

    @Serializable
    data object TimerObservable : Routes()

    @Serializable
    data object MatrixEffect : Routes()

    @Serializable
    data object LoadInitialData : Routes()
}