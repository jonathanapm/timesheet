package br.com.timesheet.controller.response

import br.com.timesheet.model.dto.TimeLogDTO
import com.fasterxml.jackson.annotation.JsonProperty

data class TimeLogResponse(
    val employeeName: String,
    @JsonProperty("registrationData")
    val timeLogList: List<TimeLogDTO> = listOf()
) {
}