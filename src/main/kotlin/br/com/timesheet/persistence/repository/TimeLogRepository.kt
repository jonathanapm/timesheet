package br.com.timesheet.persistence.repository

import br.com.timesheet.persistence.entities.TimeLog
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface TimeLogRepository : JpaRepository<TimeLog, Long> {

    /**
     * Busca registros de horas do funcionário através do seu identificador
     * @param employeeId identificador do funcionário
     * @return registro de horas encontradas
     */
    fun findByEmployeeId(employeeId: Long): List<TimeLog>
}