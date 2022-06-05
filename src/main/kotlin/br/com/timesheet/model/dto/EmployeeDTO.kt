package br.com.timesheet.model.dto

import br.com.timesheet.persistence.enum.PhoneType
import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import java.time.LocalDateTime
import javax.validation.constraints.NotBlank

class EmployeeDTO {
    @JsonIgnore
    var id: Long = 1L

    @field:NotBlank(message = "Nome deve ser informado")
    var name: String = ""

    @field:NotBlank(message = "Documento deve ser informado")
    var document: String = ""

    @field:DateTimeFormat(pattern = "yyyy-MM-dd")
    var birthDate: LocalDate = LocalDate.now()

    var office: String = ""

    var completeName: String = ""

    var phone: String = ""

    var phoneType: PhoneType = PhoneType.MOBILE

    @JsonIgnore
    var createDate: LocalDateTime = LocalDateTime.now()
}


