package com.example.jetpack_multiplenavigation.students.screensUI

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.jetpack_multiplenavigation.students.data.StudentsEvent
import com.example.jetpack_multiplenavigation.students.data.model.StudentMark
import com.example.jetpack_multiplenavigation.students.studentsViewModel.StudentsViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun StudentMarksDialog(onEvent: (StudentsEvent) -> Unit) {
    val studentsViewModel = koinViewModel<StudentsViewModel>()
    val marks = studentsViewModel.marks.collectAsStateWithLifecycle().value
    AlertDialog(
        onDismissRequest = {
            onEvent(StudentsEvent.HideMarksDialog)
        },
        confirmButton = {
            TextButton(onClick = {
                onEvent(StudentsEvent.HideMarksDialog)
            }) {
                Text(
                    text = "Confirm",
                    fontFamily = FontFamily.Serif
                )
            }
        },
        dismissButton = {
            TextButton(onClick = {
                onEvent(StudentsEvent.HideMarksDialog)
            }) {
                Text(
                    text = "Dismiss",
                    fontFamily = FontFamily.Serif
                )
            }
        },
        title = {
            Text(
                text = "Marks",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
            )
        },
        text = {
            LazyColumn(
                contentPadding = PaddingValues(4.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(marks) { mark ->
                    Text(
                        text = mark.mark.toString(),
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    )
}

val allMarks = listOf(
    StudentMark(10),
    StudentMark(9),
    StudentMark(4),
    StudentMark(3),
    StudentMark(5),
)