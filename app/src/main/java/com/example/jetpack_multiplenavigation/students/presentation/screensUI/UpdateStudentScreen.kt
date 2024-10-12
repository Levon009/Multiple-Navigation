package com.example.jetpack_multiplenavigation.students.presentation.screensUI

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.jetpack_multiplenavigation.students.domain.model.Student
import com.example.jetpack_multiplenavigation.students.data.StudentsEvent
import com.example.jetpack_multiplenavigation.students.data.StudentsState

@Composable
fun UpdateStudentScreen(
    student: Student,
    state: StudentsState,
    navController: NavHostController,
    onEvent: (StudentsEvent) -> Unit
) {
    val context = LocalContext.current
    if (state.studentId == -1) {
        onEvent(StudentsEvent.SetStudentId(student.id))
        onEvent(StudentsEvent.SetFirstName(student.firstName))
        onEvent(StudentsEvent.SetSecondName(student.secondName))
        onEvent(StudentsEvent.SetAge(student.age))
    }
    val firstName = remember {
        mutableStateOf(student.firstName)
    }
    val secondName = remember {
        mutableStateOf(student.secondName)
    }
    val age = remember {
        mutableStateOf(student.age.toString())
    }
    StudentsTopBar(
        title = "Add Student",
        navController = navController
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            TextFieldContent(
                text = firstName,
                labelText = "First name"
            ) {
                onEvent(StudentsEvent.SetFirstName(firstName.value))
            }
            Spacer(modifier = Modifier.height(10.dp))
            TextFieldContent(
                text = secondName,
                labelText = "Second name"
            ) {
                onEvent(StudentsEvent.SetSecondName(secondName.value))
            }
            Spacer(modifier = Modifier.height(10.dp))
            TextFieldContent(
                text = age,
                labelText = "Age"
            ) {
                if (age.value.isNotBlank())
                    onEvent(StudentsEvent.SetAge(Integer.parseInt(age.value)))
            }
            Spacer(modifier = Modifier.height(25.dp))
            ButtonContent("Update student") {
                if (firstName.value.isNotBlank() || secondName.value.isNotBlank() || age.value.isNotBlank()) {
                    val newStudent = Student(firstName.value, secondName.value, Integer.parseInt(age.value))
                    onEvent(StudentsEvent.UpdateStudent(newStudent))
                } else {
                    Toast.makeText(context, "Please fill out all fields", Toast.LENGTH_SHORT).show()
                }
                navController.navigateUp()
            }
        }
    }
}