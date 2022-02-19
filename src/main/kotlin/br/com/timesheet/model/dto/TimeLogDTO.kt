package br.com.timesheet.model.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDateTime

class TimeLogDTO {
    @JsonIgnore
    var employeeId: Long = 0

    val registrationDateTime: LocalDateTime = LocalDateTime.now()
}