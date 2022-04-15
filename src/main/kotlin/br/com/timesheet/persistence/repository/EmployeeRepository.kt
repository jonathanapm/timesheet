package br.com.timesheet.persistence.repository

import br.com.timesheet.persistence.entities.Employee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface EmployeeRepository: JpaRepository<Employee, Long> {

    /**
     * Busca um usuário por documento
     * @param document do funcionário
     * @return dados do funcionário encontrado
     */
    fun findByDocument(document: String): Optional<Employee>
}