package br.com.timesheet.model.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDateTime

class TimeLogDTO {
    @JsonIgnore
    var employee: EmployeeDTO = EmployeeDTO()

    var registrationDate: LocalDateTime = LocalDateTime.now()
}