package br.com.timesheet.model.dto

import br.com.timesheet.persistence.enum.PhoneType
import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import java.time.LocalDateTime
import javax.validation.constraints.NotBlank

class EmployeeDTO {
    @JsonIgnore
    val id: Long = 1L

    @field:NotBlank(message = "Nome deve ser informado")
    val name: String = ""

    @field:NotBlank(message = "Documento deve ser informado")
    val document: String = ""

    @field:DateTimeFormat(pattern = "yyyy-MM-dd")
    val birthDate: LocalDate = LocalDate.now()

    val office: String = ""

    val completeName: String = ""

    val phone: String = ""

    val phoneType: PhoneType = PhoneType.MOBILE

    @JsonIgnore
    val createDate: LocalDateTime = LocalDateTime.now()
}


