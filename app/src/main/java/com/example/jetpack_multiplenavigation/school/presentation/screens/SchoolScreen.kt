package com.example.jetpack_multiplenavigation.school.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.jetpack_multiplenavigation.school.presentation.SchoolViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SchoolScreen(navController: NavHostController) {
    val schoolViewModel = koinViewModel<SchoolViewModel>()
    val studentsOfSubject = schoolViewModel.studentsOfSubject.collectAsStateWithLifecycle().value
    val subjectsOfStudent = schoolViewModel.subjectsOfStudent.collectAsStateWithLifecycle().value
    schoolViewModel.getStudentsOfSubject("Math")
    schoolViewModel.getSubjectsOfStudent("Levon Hakobyan")
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "School",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily.Serif,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Arrow bakc"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = paddingValues,
            modifier = Modifier
                .fillMaxSize()
                .padding(25.dp)
        ) {
            items(subjectsOfStudent) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(3.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = it.student3.studentName,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp
                    )
                    it.subjectsList.forEach { subject ->
                        Text(
                            text = buildAnnotatedString {
                                val boldStyle = SpanStyle(
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold
                                )
                                pushStyle(boldStyle)
                                append(subject.subjectName)
                                append("\n")
                                pop()
                            }
                        )
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}