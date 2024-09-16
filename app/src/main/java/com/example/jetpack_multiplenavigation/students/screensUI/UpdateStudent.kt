package com.example.jetpack_multiplenavigation.students.screensUI

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.draf.students.data.model.Student
import com.example.draf.students.screensUI.UpdateStudentScreen
import com.example.jetpack_multiplenavigation.students.studentsViewModel.StudentsViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun UpdateStudent(
    navController: NavHostController,
    student: Student
) {
    val studentsViewModel = koinViewModel<StudentsViewModel>()
    val state = studentsViewModel.state.collectAsStateWithLifecycle().value
    UpdateStudentScreen(
        student = student,
        state = state,
        navController = navController
    ) {
        studentsViewModel.onEvent(it)
    }
}