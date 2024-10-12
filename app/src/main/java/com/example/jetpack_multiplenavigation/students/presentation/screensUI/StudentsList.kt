package com.example.jetpack_multiplenavigation.students.presentation.screensUI

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.jetpack_multiplenavigation.students.presentation.StudentsViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun StudentsList(navController: NavHostController) {
    val studentsViewModel = koinViewModel<StudentsViewModel>()
    val state = studentsViewModel.state.collectAsStateWithLifecycle()
    StudentsListScreen(
        navController = navController,
        state = state.value
    ) {
        studentsViewModel.onEvent(it)
    }
}