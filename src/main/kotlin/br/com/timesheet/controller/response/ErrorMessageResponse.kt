package br.com.timesheet.controller.response

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.http.HttpStatus
import java.time.LocalDateTime

/**
 * Classe de retorno de erros da aplicação
 */
data class ErrorMessageResponse(
    @JsonProperty
    val status: HttpStatus,
    @JsonProperty
    val message: String?,
    @JsonProperty
    val date: LocalDateTime = LocalDateTime.now()
)