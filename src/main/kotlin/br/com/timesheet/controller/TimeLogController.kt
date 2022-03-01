package br.com.timesheet.controller

import br.com.timesheet.controller.response.TimeLogResponse
import br.com.timesheet.service.EmployeeService
import br.com.timesheet.service.TimeLogService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

/**
 * Controle de registro de horas
 */
@RestController
@RequestMapping("time-log")
class TimeLogController {

    @Autowired
    private lateinit var timeLogService: TimeLogService

    @Autowired
    private lateinit var employeeService: EmployeeService

    /**
     * Busca o registro de horas de um funcionário
     * @param employeeId identificador do funcionário
     * @param date data da busca dos registros
     * @return dados dos registros encontrados
     */
    @GetMapping("/{employeeId}/{date}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getTimeLogByEmployeeId(@PathVariable("employeeId") employeeId: Long,
                               @PathVariable("date") date: String): ResponseEntity<TimeLogResponse> {
        return employeeService.findEmployeeById(employeeId).let {
            timeLogService.getTimeLogByEmployeeAndDate(employeeId, LocalDate.parse(date))
                .run {
                    ResponseEntity(TimeLogResponse(it.name, this).toJson(), HttpStatus.OK)
                }
        }
    }

    /**
     * Insere um novo registro de horas para um funcionário
     * @param employeeId identificador do funcionário
     * @return dados do registro inserido
     */
    @GetMapping("/apply/{employeeId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun registerTimeLog(@PathVariable("employeeId") employeeId: Long): ResponseEntity<TimeLogResponse> {
        return employeeService.findEmployeeById(employeeId)
            .let { employeeDTO ->
                timeLogService.registerTimeLog(employeeDTO)
                    .run {
                        ResponseEntity(TimeLogResponse(employeeDTO.name, this).toJson(), HttpStatus.OK)
                    }
        }
    }


}