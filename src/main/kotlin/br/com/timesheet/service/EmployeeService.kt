package br.com.timesheet.service

import br.com.timesheet.persistence.repository.EmployeeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EmployeeService {

    @Autowired
    private lateinit var employeeRepository: EmployeeRepository

    fun registerEmployee() {
    }

    fun updateEmployee() {

    }

    fun deleteEmployee() {

    }

    fun findEmployeeById() {

    }

    fun getAllEmployees() {

    }
}