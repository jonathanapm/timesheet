package br.com.timesheet.model.mapper

import br.com.timesheet.model.dto.EmployeeDTO
import br.com.timesheet.persistence.entities.Employee

class ToEmployeeDTOMapper: Mapper<Employee, EmployeeDTO> {
    override fun map(t: Employee): EmployeeDTO {
        return EmployeeDTO().apply {
            birthDate = t.birthDate
            completeName = t.completeName?: ""
            createDate = t.createDate
            name = t.name
            id = t.id
            office = t.office
            document = t.document
            phone = t.phone?: ""
            phoneType = t.phoneType
        }
    }
}