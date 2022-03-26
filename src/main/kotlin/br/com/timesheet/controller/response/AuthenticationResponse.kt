package br.com.timesheet.controller.response

import br.com.timesheet.model.enums.TokenTypeEnum
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class AuthenticationResponse(

    @JsonProperty
    val responseTime: LocalDateTime = LocalDateTime.now(),

    @JsonProperty
    val token: String,

    @JsonProperty
    val tokenType: TokenTypeEnum = TokenTypeEnum.Bearer
)