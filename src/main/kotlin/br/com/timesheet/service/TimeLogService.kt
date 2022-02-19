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

    fun registerTimeLog(employeeId: Long): List<TimeLogDTO> {
        findTimeLogByEmployeeId(employeeId, null).let {
            if (it.size >= REGISTER_MAX) {
                throw RuntimeException("Não é possível inserir novo registro. Banco de horas do dia completo")
            }
            TimeLogDTO().apply {
                this.employeeId = employeeId
                saveTimeLog(this).let(it::plus)
                return it
            }
        }
    }

    private fun saveTimeLog(timeLogDTO: TimeLogDTO) =
        try {
            timeLogRepository.save(Mapper.convert<TimeLogDTO, TimeLog>(timeLogDTO)).let {
                Mapper.convert<TimeLog, TimeLogDTO>(it)
            }
        } catch (e: RuntimeException) {
            throw e
        }

    fun findTimeLogByEmployeeId(employeeId: Long, date: LocalDate?): List<TimeLogDTO> =
        try {
            val dateTime = date?.toString() ?: LocalDate.now().toString()
            timeLogRepository.findByEmployeeId(employeeId, dateTime).map {
                Mapper.convert(it)
            }
        } catch (e: RuntimeException) {
            throw e
        }
}