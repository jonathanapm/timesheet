package br.com.timesheet.controller

import br.com.timesheet.controller.response.EmployeeResponse
import br.com.timesheet.model.dto.EmployeeDTO
import br.com.timesheet.model.enums.StatusResponse
import br.com.timesheet.service.EmployeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.query.Param
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/employees")
class EmployeeController {

    @Autowired
    private lateinit var employeeService: EmployeeService

    @PostMapping("/register", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun saveEmployee(@RequestBody employee: EmployeeDTO): ResponseEntity<EmployeeResponse> {
        employeeService.saveEmployee(employeeDTO = employee)
        return ResponseEntity(
            EmployeeResponse(StatusResponse.CREATE_EMPLOYEE_SUCCESS.message, LocalDateTime.now()),
            StatusResponse.CREATE_EMPLOYEE_SUCCESS.httpStatus
        )
    }

}