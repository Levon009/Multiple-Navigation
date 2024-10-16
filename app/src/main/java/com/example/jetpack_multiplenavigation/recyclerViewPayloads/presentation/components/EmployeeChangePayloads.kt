package com.example.jetpack_multiplenavigation.recyclerViewPayloads.presentation.components

interface EmployeeChangePayloads {
    data class Name(val newName: String) : EmployeeChangePayloads
    data class Age(val newAge: Int) : EmployeeChangePayloads
}