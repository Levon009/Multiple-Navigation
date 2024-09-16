package com.example.jetpack_koin2.Employee.employeeRepository

import com.example.jetpack_koin2.Employee.data.model.Employee

interface EmployeeRepository {
    fun saveEmployee(employee: Employee)
    fun findEmployeeByPhoneNo(phoneNo: String) : Employee?
}