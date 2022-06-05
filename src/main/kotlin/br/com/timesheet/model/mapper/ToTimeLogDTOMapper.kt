package br.com.timesheet.model.mapper

import br.com.timesheet.model.dto.TimeLogDTO
import br.com.timesheet.persistence.entities.TimeLog

class ToTimeLogDTOMapper(
    private val toEmployeeDTOMapper: ToEmployeeDTOMapper
): Mapper<TimeLog, TimeLogDTO> {

    override fun map(t: TimeLog): TimeLogDTO {
        return TimeLogDTO().apply {
            registrationDate = t.registrationDateTime
            employee = toEmployeeDTOMapper.map(t.employee)
        }
    }
}