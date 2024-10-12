package com.example.jetpack_multiplenavigation.employee_dependencies.data.employeeFactory

import com.example.jetpack_multiplenavigation.employee_dependencies.domain.model.Employee
import com.example.jetpack_multiplenavigation.employee_dependencies.domain.repository.EmployeeRepository

class EmployeeFactory(
    private val employeeRepository: EmployeeRepository
) {
    fun saveEmployee(employee: Employee) : String {
        employeeRepository.saveEmployee(employee)
        return "Saved at $this"
    }
}