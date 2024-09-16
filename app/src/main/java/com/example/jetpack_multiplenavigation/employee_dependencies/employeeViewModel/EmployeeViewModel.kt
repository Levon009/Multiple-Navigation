package com.example.jetpack_koin2.Employee.employeeViewModel

import androidx.lifecycle.ViewModel
import com.example.jetpack_koin2.Employee.data.model.Employee
import com.example.jetpack_koin2.Employee.employeeRepository.EmployeeRepository

class EmployeeViewModel(
    private val employeeRepository: EmployeeRepository
) : ViewModel() {

    fun saveEmployee(employee: Employee) {
        employeeRepository.saveEmployee(employee)
    }

    fun findEmployeeByPhoneNo(phoneNo: String) = employeeRepository.findEmployeeByPhoneNo(phoneNo)
}