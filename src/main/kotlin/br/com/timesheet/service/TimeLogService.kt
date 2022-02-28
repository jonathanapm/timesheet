package br.com.timesheet.service

import br.com.timesheet.model.dto.EmployeeDTO
import br.com.timesheet.model.dto.TimeLogDTO
import br.com.timesheet.model.util.Mapper
import br.com.timesheet.persistence.entities.TimeLog
import br.com.timesheet.persistence.repository.TimeLogRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class TimeLogService {

    companion object {
        const val REGISTER_MAX = 4
    }

    @Autowired
    private lateinit var timeLogRepository: TimeLogRepository

    fun registerTimeLog(employeeDTO: EmployeeDTO): List<TimeLogDTO> {
        findTimeLog(employeeDTO.id, null).let {
            if (it.size >= REGISTER_MAX) {
                throw RuntimeException("Não é possível inserir novo registro. Banco de horas do dia completo")
            }
            return it.plus(TimeLog(employeeId = employeeDTO.id)).let(::saveTimeLog)
        }
    }

    private fun saveTimeLog(timeLogList: List<TimeLog>): List<TimeLogDTO> =
        try {
            timeLogRepository.saveAll(timeLogList).map(Mapper::convert)
        } catch (e: RuntimeException) {
            throw e
        }

    private fun findTimeLog(employeeId: Long, date: LocalDate?): List<TimeLog> =
        try {
            timeLogRepository.findByEmployeeId(employeeId)
                .filter {
                    it.registrationDateTime.toLocalDate() == (date ?: LocalDate.now())
                }
        } catch (e: RuntimeException) {
            throw e
        }

    fun getTimeLogByEmployeeAndDate(employeeId: Long, date: LocalDate): List<TimeLogDTO> =
        findTimeLog(employeeId, date).let(Mapper::convert)
}