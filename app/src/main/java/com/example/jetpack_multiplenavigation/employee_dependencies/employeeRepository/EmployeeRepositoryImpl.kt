package com.example.jetpack_koin2.Employee.employeeRepository

import com.example.jetpack_koin2.Employee.data.model.Employee

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