package com.example.jetpack_multiplenavigation.employee_dependencies.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.example.jetpack_koin2.Employee.data.model.Employee
import com.example.jetpack_koin2.Employee.employeeFactory.EmployeeFactory
import org.koin.compose.koinInject

@Composable
fun EmployeeFactory1(factory: EmployeeFactory = koinInject()) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var name by remember {
        mutableStateOf("")
    }
    var phoneNo by remember {
        mutableStateOf("")
    }
    var address by remember {
        mutableStateOf("")
    }
    var displayMessage by remember {
        mutableStateOf("")
    }
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Text(text = "-----Employee Factory1-----")
        TextField(
            value = name,
            onValueChange = {
                name = it
            },
            placeholder = {
                Text(text = "Enter your name")
            }
        )
        TextField(
            value = phoneNo,
            onValueChange = {
                phoneNo = it
            },
            placeholder = {
                Text(text = "Enter your phone")
            }
        )
        TextField(
            value = address,
            onValueChange = {
                address = it
            },
            placeholder = {
                Text(text = "Enter your address")
            }
        )
        Button(onClick = {
            keyboardController?.hide()
            displayMessage = factory.saveEmployee(Employee(name, phoneNo, address))
            name = ""
            phoneNo = ""
            address = ""
        }) {
            Text(text = "Save")
        }
        Text(text = displayMessage)
    }
}