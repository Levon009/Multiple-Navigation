package com.example.jetpack_multiplenavigation.employee_dependencies.presentation

import androidx.lifecycle.ViewModel
import com.example.jetpack_multiplenavigation.employee_dependencies.domain.model.Employee
import com.example.jetpack_multiplenavigation.employee_dependencies.domain.repository.EmployeeRepository

class EmployeeViewModel(
    private val employeeRepository: EmployeeRepository
) : ViewModel() {

    fun saveEmployee(employee: Employee) {
        employeeRepository.saveEmployee(employee)
    }

    fun findEmployeeByPhoneNo(phoneNo: String) = employeeRepository.findEmployeeByPhoneNo(phoneNo)
}