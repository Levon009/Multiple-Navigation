package com.example.jetpack_multiplenavigation.students.presentation.screensUI

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.jetpack_multiplenavigation.students.data.StudentsEvent
import com.example.jetpack_multiplenavigation.students.data.StudentsState
import com.example.jetpack_multiplenavigation.students.domain.model.ProgrammingLanguage
import com.example.jetpack_multiplenavigation.students.presentation.StudentsViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LanguagesDialog(
    state: StudentsState,
    onEvent: (StudentsEvent) -> Unit
) {
    val studentsViewModel = koinViewModel<StudentsViewModel>()
    val languages = studentsViewModel.languages.collectAsStateWithLifecycle().value
    AlertDialog(
        onDismissRequest = {
            onEvent(StudentsEvent.HideLanguagesDialog)
        },
        confirmButton = {
            TextButton(onClick = {
                onEvent(StudentsEvent.HideLanguagesDialog)
            }) {
                Text(
                    text = "Confirm",
                    fontFamily = FontFamily.Serif
                )
            }
        },
        dismissButton = {
            TextButton(onClick = {
                onEvent(StudentsEvent.HideLanguagesDialog)
            }) {
                Text(
                    text = "Dismiss",
                    fontFamily = FontFamily.Serif
                )
            }
        },
        title = {
            Text(
                text = "Programming Language",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Center
            )
        },
        text = {
            languages.forEach {
                if (it.studentId == state.studentId) {
                    Text(
                        text = it.name,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        textAlign = TextAlign.Center
                    )
                }
            }
        },
    )
}

val allLanguages = listOf(
    ProgrammingLanguage(
        "Java",
        28
    ),
    ProgrammingLanguage(
        "Kotlin",
        29
    ),
    ProgrammingLanguage(
        "C++",
        30
    ),
    ProgrammingLanguage(
        "Python",
        31
    ),
    ProgrammingLanguage(
        "Swift",
        32
    ),
)