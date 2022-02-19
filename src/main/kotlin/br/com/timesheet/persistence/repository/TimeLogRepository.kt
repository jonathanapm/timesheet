package br.com.timesheet.persistence.repository

import br.com.timesheet.persistence.entities.TimeLog
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TimeLogRepository : JpaRepository<TimeLog, Long> {

    fun findByEmployeeId(employeeId: Long): List<TimeLog>

}