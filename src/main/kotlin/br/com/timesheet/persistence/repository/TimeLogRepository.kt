package br.com.timesheet.persistence.repository

import br.com.timesheet.persistence.entities.TimeLog
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface TimeLogRepository : JpaRepository<TimeLog, Long> {

    @Query(value =
        "SELECT *FROM TIME_LOG " +
        "WHERE employee_id = :employeeId AND PARSEDATETIME(registration_date_time, 'yyyy-MM-dd) = :dateTime", nativeQuery = true)
    fun findByEmployeeId(
        @Param("employeeId") employeeId: Long,
        @Param("dateTime") dateTime: String
    ): List<TimeLog>


}