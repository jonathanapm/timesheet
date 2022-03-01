package br.com.timesheet.controller

import br.com.timesheet.controller.response.TimeLogResponse
import br.com.timesheet.service.EmployeeService
import br.com.timesheet.service.TimeLogService
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
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
    @ApiOperation("Busca o registro de horas de um funcionário")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Registros de horas encontrados na base"),
        ApiResponse(code = 404, message = "Funcionário não encontrado")
    ])
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
    @ApiOperation("Insere um novo registro de horas para um funcionário")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Registro de hora realizado com sucesso"),
        ApiResponse(code = 404, message = "Funcionário não encontrado"),
        ApiResponse(code = 403, message = "Limite de horas diárias excedidas"),
    ])
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