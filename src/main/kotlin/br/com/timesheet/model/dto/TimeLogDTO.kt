package br.com.timesheet.model.dto

import com.fasterxml.jackson.annotation.JsonIgnore

data class TimeLogDTO(
    @JsonIgnore
    var employeeId: Long,

)