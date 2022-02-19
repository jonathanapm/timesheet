package br.com.timesheet.model.dto

import br.com.timesheet.persistence.enum.PhoneType
import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDate
import java.time.LocalDateTime

class EmployeeDTO
{
    val id: Long = 0

    val name: String = ""

    val document: String = ""

    val birthDate: LocalDate = LocalDate.now()

    val office: String = ""

    val completeName: String = ""

    val phone: String = ""

    val phoneType: PhoneType = PhoneType.MOBILE

    @JsonIgnore
    val createDate: LocalDateTime = LocalDateTime.now()
}