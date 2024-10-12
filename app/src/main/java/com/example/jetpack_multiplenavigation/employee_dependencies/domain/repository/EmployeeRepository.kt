package com.example.jetpack_multiplenavigation.employee_dependencies.domain.repository

import com.example.jetpack_multiplenavigation.employee_dependencies.domain.model.Employee

interface EmployeeRepository {
    fun saveEmployee(employee: Employee)
    fun findEmployeeByPhoneNo(phoneNo: String) : Employee?
}