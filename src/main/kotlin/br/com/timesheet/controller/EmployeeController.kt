package br.com.timesheet.controller

import br.com.timesheet.service.EmployeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

const val EMPLOYEE_CONTROLLER = "/employees"

@RestController
@RequestMapping(EMPLOYEE_CONTROLLER)
class EmployeeController {

    @Autowired
    private lateinit var employeeService: EmployeeService
}