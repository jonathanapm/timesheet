package br.com.timesheet.model.mapper

import br.com.timesheet.model.dto.EmployeeDTO
import br.com.timesheet.persistence.entities.Employee

class ToEmployeeMapper: Mapper<EmployeeDTO, Employee> {

    override fun map(t: EmployeeDTO): Employee {
        return Employee(
            birthDate = t.birthDate,
            completeName = t.completeName,
            name = t.name,
            office = t.office,
            document = t.document,
            phone = t.phone,
            phoneType = t.phoneType,
        )
    }
}