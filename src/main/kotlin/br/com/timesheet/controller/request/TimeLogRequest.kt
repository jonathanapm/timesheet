package br.com.timesheet.controller.request

import br.com.timesheet.model.dto.TimeLogDTO
import com.fasterxml.jackson.annotation.JsonProperty

data class TimeLogRequest(
    @JsonProperty("registrationData")
    val timeLogDTO: TimeLogDTO
)