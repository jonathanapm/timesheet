package br.com.timesheet.persistence.repository

import br.com.timesheet.persistence.entities.EmployeeEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface EmployeeRepository: JpaRepository<EmployeeEntity, Long> {

    fun findByDocument(document: String): Optional<EmployeeEntity>
}