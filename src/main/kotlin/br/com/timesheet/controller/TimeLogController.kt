package br.com.timesheet.controller

import br.com.timesheet.controller.request.TimeLogRequest
import br.com.timesheet.controller.response.TimeLogResponse
import br.com.timesheet.service.EmployeeService
import br.com.timesheet.service.TimeLogService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.websocket.server.PathParam

@RestController
@RequestMapping("time-log")
class TimeLogController {

    @Autowired
    private lateinit var timeLogService: TimeLogService

    @Autowired
    private lateinit var employeeService: EmployeeService

    @PostMapping("/apply/{employeeId}")
    fun registerTimeLog(@RequestBody @Validated timeLogRequest: TimeLogRequest, @PathParam("employeeId") employeeId: Long): ResponseEntity<TimeLogResponse> {
        return employeeService.findEmployeeById(employeeId)
            .let { employee ->
                timeLogRequest.timeLogDTO.employeeId = employeeId
                timeLogService.registerTimeLog(timeLogRequest.timeLogDTO)
                    .run {
                        ResponseEntity(TimeLogResponse(employee.name, this), HttpStatus.NO_CONTENT)
                    }
        }
    }

}