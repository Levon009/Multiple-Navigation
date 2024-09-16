package com.example.jetpack_koin2.Employee.employeeFactory

import com.example.jetpack_koin2.Employee.data.model.Employee
import com.example.jetpack_koin2.Employee.employeeRepository.EmployeeRepository

class EmployeeFactory(
    private val employeeRepository: EmployeeRepository
) {
    fun saveEmployee(employee: Employee) : String {
        employeeRepository.saveEmployee(employee)
        return "Saved at $this"
    }
}