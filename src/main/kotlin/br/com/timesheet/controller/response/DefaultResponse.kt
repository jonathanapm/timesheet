package br.com.timesheet.controller.response

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

open class DefaultResponse(
    @JsonProperty("horário do retorno")
    val dateTimeResponse: LocalDateTime = LocalDateTime.now()
) {
}