package br.com.timesheet.model.dto

import br.com.timesheet.persistence.enum.PhoneType
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

data class EmployeeDTO(
    @JsonIgnore
    val id: Long = 0,

    val name: String,

    val document: String,

    val birthDate: LocalDate,

    val office: String,

    val completeName: String = "",

    val phone: String = "",

    val phoneType: PhoneType = PhoneType.MOBILE,

    @JsonIgnore
    val createDate: LocalDateTime = LocalDateTime.now()
) {
}