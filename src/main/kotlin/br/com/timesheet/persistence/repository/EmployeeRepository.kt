package br.com.timesheet.persistence.repository

import br.com.timesheet.persistence.entities.EmployeeEntity
import org.springframework.data.jpa.repository.JpaRepository

interface EmployeeRepository: JpaRepository<Long, EmployeeEntity> {

}