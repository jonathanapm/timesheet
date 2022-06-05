package br.com.timesheet.service

import br.com.timesheet.controller.exception.LimitedAttemptsException
import br.com.timesheet.model.dto.EmployeeDTO
import br.com.timesheet.model.dto.TimeLogDTO
import br.com.timesheet.model.mapper.ToEmployeeMapper
import br.com.timesheet.model.mapper.ToTimeLogDTOMapper
import br.com.timesheet.persistence.entities.TimeLog
import br.com.timesheet.persistence.repository.TimeLogRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class TimeLogService(
    private val timeLogRepository: TimeLogRepository,
    private val toEmployeeMapper: ToEmployeeMapper,
    private val toTimeLogDTOMapper: ToTimeLogDTOMapper
) {

    companion object {
        const val REGISTER_MAX = 4
    }

    /**
     * Registro de horas para um funcionário
     * @param employeeDTO dados do usuário
     * @return dados de horas inseridos
     */
    fun registerTimeLog(employeeDTO: EmployeeDTO): List<TimeLogDTO> {
        findTimeLog(employeeDTO.id, null).let {
            if (it.size >= REGISTER_MAX) {
                throw LimitedAttemptsException()
            }
            return it.plus(TimeLog(employee = toEmployeeMapper.map(employeeDTO)))
                .let(::saveTimeLog)
        }
    }

    /**
     * Salva o funcinário na base
     * @param timeLogList lista de horas a serem salvas
     * @return registro de horas
     */
    private fun saveTimeLog(timeLogList: List<TimeLog>): List<TimeLogDTO> =
        try {
            timeLogRepository.saveAll(timeLogList).map(toTimeLogDTOMapper::map)
        } catch (e: RuntimeException) {
            throw RuntimeException("Erro ao salvar registro de horas", e)
        }

    /**
     * Busca registro de horas do funcinário
     * @param employeeId identificador do funcionaŕio
     * @param date data dos registros de horas
     * @return dados encontrados
     */
    fun getTimeLogByEmployeeAndDate(employeeId: Long, date: LocalDate): List<TimeLogDTO> =
        findTimeLog(employeeId, date).map(toTimeLogDTOMapper::map)

    private fun findTimeLog(employeeId: Long, date: LocalDate?): List<TimeLog> =
        try {
            timeLogRepository.findByEmployeeId(employeeId)
                .filter {
                    it.registrationDateTime.toLocalDate() == (date ?: LocalDate.now())
                }

        } catch (e: RuntimeException) {
            throw RuntimeException("Erro na busca de registros", e)
        }
}