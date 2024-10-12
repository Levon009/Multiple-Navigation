package com.example.jetpack_multiplenavigation.students.screensUI

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpack_multiplenavigation.students.presentation.screensUI.AddStudentScreen
import com.example.jetpack_multiplenavigation.MainActivity
import com.example.jetpack_multiplenavigation.navigation.Routes
import com.example.jetpack_multiplenavigation.students.core.util.TestTags
import com.example.jetpack_multiplenavigation.students.di.KoinTestRule
import com.example.jetpack_multiplenavigation.students.di.instrumentedTestModule
import com.example.jetpack_multiplenavigation.students.di.studentsDatabaseModule
import com.example.jetpack_multiplenavigation.students.presentation.StudentsViewModel
import com.example.jetpack_multiplenavigation.ui.theme.JetPack_MultipleNavigationTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI


class AddStudentScreenKtTest {

    @get:Rule(order = 0)
    val koinTestRule = KoinTestRule(
        modules = listOf(studentsDatabaseModule, instrumentedTestModule)
    )

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @OptIn(KoinExperimentalAPI::class)
    @Before
    fun setUp() {
        composeTestRule.setContent {
            JetPack_MultipleNavigationTheme {
                val navController = rememberNavController()
                val studentsViewModel = koinViewModel<StudentsViewModel>()
                val state = studentsViewModel.state.collectAsStateWithLifecycle()
                NavHost(
                    navController = navController,
                    startDestination = Routes.AddStudentRoute
                ) {
                    composable<Routes.AddStudentRoute> {
                        AddStudentScreen(
                            navController = navController,
                            state = state.value
                        ) {
                            studentsViewModel.onEvent(it)
                        }
                    }
                }
            }
        }
    }

    @Test
    fun checkIfStudentSavedCorrectly() {
        composeTestRule.onNodeWithTag(TestTags.STUDNET_NAME_TEXT_FIELD).performTextInput("")
        composeTestRule.onNodeWithTag(TestTags.STUDENT_SECONDNAME_TEXT_FIELD).performTextInput("")
        composeTestRule.onNodeWithTag(TestTags.STUDENT_AGE_TEXT_FIELD).performTextInput("")
        composeTestRule.onNodeWithContentDescription("Add").performClick()
    }
}