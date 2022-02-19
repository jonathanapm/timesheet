package br.com.timesheet.persistence.repository

import br.com.timesheet.persistence.entities.Employee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface EmployeeRepository: JpaRepository<Employee, Long> {

    fun findByDocument(document: String): Optional<Employee>
}