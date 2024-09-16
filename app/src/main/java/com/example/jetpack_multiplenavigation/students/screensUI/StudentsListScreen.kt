package com.example.draf.students.screensUI

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.jetpack_multiplenavigation.navigation.Routes
import com.example.jetpack_multiplenavigation.students.data.StudentsEvent
import com.example.jetpack_multiplenavigation.students.data.StudentsSortType
import com.example.jetpack_multiplenavigation.students.data.StudentsState
import com.example.jetpack_multiplenavigation.students.screensUI.LanguagesDialog
import com.example.jetpack_multiplenavigation.students.screensUI.StudentMarksDialog
import com.example.jetpack_multiplenavigation.students.studentsViewModel.StudentsViewModel
import com.example.jetpack_multiplenavigation.students.swipeToDeleteStudentItem.SwipeToRefreshStudentContainer
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun StudentsListScreen(
    navController: NavHostController,
    state: StudentsState,
    onEvent: (StudentsEvent) -> Unit
) {
    val studentsViewModel = koinViewModel<StudentsViewModel>()
    val isRefreshing = studentsViewModel.isRefreshing.collectAsStateWithLifecycle().value
    val swipeToRefreshState = rememberSwipeRefreshState(isRefreshing = isRefreshing)
    val snackBarHostState = remember {
        SnackbarHostState()
    }
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
        floatingActionButton = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(25.dp)
            ) {
                FloatingActionButton(
                    onClick = {
                        navController.popBackStack()
                    },
                    containerColor = Color.DarkGray,
                    contentColor = Color.White,
                    modifier = Modifier.padding(start = 15.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Home"
                    )
                }
                FloatingActionButton(
                    onClick = {
                        onEvent(StudentsEvent.DeleteAllStudents)
                    },
                    containerColor = Color.DarkGray,
                    contentColor = Color.White,
                ) {
                    Icon(
                        imageVector = Icons.Default.DeleteSweep,
                        contentDescription = "Delete Range"
                    )
                }
                FloatingActionButton(
                    onClick = {
                        navController.navigate(Routes.AddStudentRoute)
                    },
                    containerColor = Color.DarkGray,
                    contentColor = Color.White
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add"
                    )
                }
            }
        }
    ) { paddingValues ->
        if (state.showLanguagesDialog) {
            LanguagesDialog(
                state = state,
                onEvent = onEvent,
            )
        }
        if (state.showMarksDialog) {
            StudentMarksDialog(onEvent = onEvent)
        }
        SwipeRefresh(
            modifier = Modifier.padding(paddingValues),
            state = swipeToRefreshState,
            onRefresh = studentsViewModel::checkForRefresh,
            indicator = { state, refreshTrigger ->
                SwipeRefreshIndicator(
                    state = state,
                    refreshTriggerDistance = refreshTrigger,
                    backgroundColor = Color.Gray,
                    contentColor = Color.Magenta
                )
            }
        ) {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    StudentsTopContent(
                        state = state,
                        onEvent = onEvent
                    )
                }
                itemsIndexed(
                    items = state.students,
                    key = { _, student ->
                        student.hashCode()
                    }
                ) { index, student ->
                    SwipeToRefreshStudentContainer(
                        student = student,
                        onRemove = {
                            onEvent(StudentsEvent.DeleteStudent(student))
                        }
                    ) {
                        StudentsListItem(
                            student = student,
                            onEvent = onEvent,
                            onLanguageClick = {
                                studentsViewModel.getStudentLanguage(student.id)
                                onEvent(StudentsEvent.ShowLanguagesDialog(student.id))
                            }
                        ) {
                            navController.navigate(Routes.UpdateStudentRoute(student))
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun StudentsTopContent(
    state: StudentsState,
    onEvent: (StudentsEvent) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
    ) {
        StudentsSortType.entries.forEach { sortType ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(start = 7.dp)
                    .clickable {
                        onEvent(StudentsEvent.SortStudents(sortType))
                    }
            ) {
                RadioButton(
                    selected = sortType == state.studentsSortType,
                    onClick = {
                        onEvent(StudentsEvent.SortStudents(sortType))
                    },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Color.Green,
                        unselectedColor = Color.Green
                    )
                )
                Text(
                    text = sortType.name,
                    fontFamily = FontFamily.Serif
                )
            }
        }
    }
}