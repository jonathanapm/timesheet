package br.com.timesheet.model.dto

import br.com.timesheet.persistence.enum.PhoneType
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

data class EmployeeDTO(
    @JsonIgnore
    val id: UUID = UUID.randomUUID(),

    @JsonProperty("nome")
    val name: String,

    @JsonProperty("aniversario")
    val birthDate: LocalDate,

    @JsonProperty("cargo")
    val office: String,

    @JsonProperty("nomeCompleto")
    val completeName: String = "",

    @JsonProperty("telefone")
    val phone: String = "",

    @JsonProperty("tipoTelefone")
    val phoneType: PhoneType = PhoneType.MOBILE,

    @JsonIgnore
    val createDate: LocalDateTime = LocalDateTime.now()
) {
}