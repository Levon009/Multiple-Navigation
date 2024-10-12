package com.example.jetpack_multiplenavigation.employee_dependencies.presentation.screens

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
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import com.example.jetpack_multiplenavigation.employee_dependencies.domain.model.Employee
import com.example.jetpack_multiplenavigation.employee_dependencies.presentation.EmployeeViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun EmployeeInfo(viewModel: EmployeeViewModel = koinViewModel()) {
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
    var phoneNoToSearch by remember {
        mutableStateOf("")
    }
    var employeeFound by remember {
        mutableStateOf<Employee?>(null)
    }
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Text(text = "-----Employee Info-----")
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
            viewModel.saveEmployee(Employee(name, phoneNo, address))
            name = ""
            phoneNo = ""
            address = ""
        }) {
            Text(text = "Save")
        }
        TextField(
            value = phoneNoToSearch,
            onValueChange = {
                phoneNoToSearch = it
            },
            placeholder = {
                Text(text = "Enter phone to find employee")
            }
        )
        Button(onClick = {
            keyboardController?.hide()
            employeeFound = viewModel.findEmployeeByPhoneNo(phoneNoToSearch)
        }) {
            Text(text = "Find employee")
        }
        employeeFound?.let {
            Text(
                text = buildAnnotatedString {
                    append(it.name)
                    append("\n")
                    append(it.phoneNo)
                    append("\n")
                    append(it.address)
                    append("\n")
                }
            )
        }
    }
}