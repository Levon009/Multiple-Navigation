package com.example.jetpack_multiplenavigation.employee_dependencies.data.repository

import com.example.jetpack_multiplenavigation.employee_dependencies.domain.model.Employee
import com.example.jetpack_multiplenavigation.employee_dependencies.domain.repository.EmployeeRepository

class EmployeeRepositoryImpl : EmployeeRepository {
    private val employees = arrayListOf<Employee>()

    override fun saveEmployee(employee: Employee) {
        employees.add(employee)
    }

    override fun findEmployeeByPhoneNo(phoneNo: String): Employee? {
        return employees.firstOrNull {
            it.phoneNo == phoneNo
        }
    }
}