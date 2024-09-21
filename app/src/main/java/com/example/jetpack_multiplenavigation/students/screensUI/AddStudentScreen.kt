package com.example.draf.students.screensUI

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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.jetpack_multiplenavigation.students.core.util.TestTags
import com.example.jetpack_multiplenavigation.students.data.StudentsEvent
import com.example.jetpack_multiplenavigation.students.data.StudentsState

@Composable
fun AddStudentScreen(
    navController: NavHostController,
    state: StudentsState,
    onEvent: (StudentsEvent) -> Unit
) {
    val firstName = remember {
        mutableStateOf(state.firstName)
    }
    val secondName = remember {
        mutableStateOf(state.secondName)
    }
    val age = remember {
        mutableStateOf(state.age.toString())
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
                labelText = "First name",
                testTag = TestTags.STUDNET_NAME_TEXT_FIELD
            ) {
                onEvent(StudentsEvent.SetFirstName(firstName.value))
            }
            Spacer(modifier = Modifier.height(10.dp))
            TextFieldContent(
                text = secondName,
                labelText = "Second name",
                testTag = TestTags.STUDENT_SECONDNAME_TEXT_FIELD
            ) {
                onEvent(StudentsEvent.SetSecondName(secondName.value))
            }
            Spacer(modifier = Modifier.height(10.dp))
            TextFieldContent(
                text = age,
                labelText = "Age",
                testTag = TestTags.STUDENT_AGE_TEXT_FIELD
            ) {
                if (age.value.isNotBlank())
                    onEvent(StudentsEvent.SetAge(Integer.parseInt(age.value)))
            }
            Spacer(modifier = Modifier.height(25.dp))
            ButtonContent("Add student") {
                onEvent(StudentsEvent.SaveStudent)
                navController.navigateUp()
            }
        }
    }
}