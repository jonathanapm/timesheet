package br.com.timesheet.controller.response

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

class EmployeeResponse(
    @JsonProperty("mensagem de retorno")
    val msg: String = "",
    dateTimeResponse: LocalDateTime = LocalDateTime.now()
) {
}