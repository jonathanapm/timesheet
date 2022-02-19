package br.com.timesheet.service

import br.com.timesheet.model.dto.TimeLogDTO
import br.com.timesheet.model.util.Mapper
import br.com.timesheet.persistence.entities.TimeLogEntity
import br.com.timesheet.persistence.repository.TimeLogRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TimeLogService {

    companion object {
        const val REGISTER_MAX = 4
    }

    @Autowired
    private lateinit var timeLogRepository: TimeLogRepository

    fun registerTimeLog(timeLogDTO: TimeLogDTO): List<TimeLogDTO> {
        findTimeLogByEmployeeId(timeLogDTO.employeeId).let {
            if (REGISTER_MAX >= it.size) {
                throw RuntimeException("Não é possível inserir novo registro. Banco de horas completo")
            }
            saveTimeLog(timeLogDTO)
            return it.plus(timeLogDTO)
        }
    }

    private fun saveTimeLog(timeLogDTO: TimeLogDTO) =
        timeLogRepository.save(Mapper.convert<TimeLogDTO, TimeLogEntity>(timeLogDTO))

    private fun findTimeLogByEmployeeId(employeeId: Long): List<TimeLogDTO> =
        timeLogRepository.findByEmployeeId(employeeId).map {
            Mapper.convert(it)
        }




}