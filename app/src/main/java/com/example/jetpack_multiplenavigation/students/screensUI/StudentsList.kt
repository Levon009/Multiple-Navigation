package com.example.jetpack_multiplenavigation.students.screensUI

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.draf.students.screensUI.StudentsListScreen
import com.example.jetpack_multiplenavigation.students.studentsViewModel.StudentsViewModel
import org.koin.compose.viewmodel.koinViewModel

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