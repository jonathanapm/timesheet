package br.com.timesheet.controller

import br.com.timesheet.model.dto.EmployeeDTO
import br.com.timesheet.service.EmployeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/employees")
class EmployeeController {

    @Autowired
    private lateinit var employeeService: EmployeeService

    @PostMapping("/register", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun saveEmployee(@RequestBody employee: EmployeeDTO): ResponseEntity<EmployeeDTO> =
        ResponseEntity(employeeService.saveEmployee(employee), HttpStatus.CREATED)

    @DeleteMapping("/remove/{employeeId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun deleteEmployee(@PathVariable("employeeId") employeeId: Long): ResponseEntity<Unit> =
        ResponseEntity(employeeService.deleteEmployee(employeeId), HttpStatus.OK)

    @GetMapping("/{employeeId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getEmployee(@PathVariable("employeeId") employeeId: Long): ResponseEntity<EmployeeDTO> =
        ResponseEntity(employeeService.findEmployeeById(employeeId), HttpStatus.OK)


    @GetMapping("/{document}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getEmployeeByDocument(@PathVariable("document") document: String): ResponseEntity<EmployeeDTO> =
        ResponseEntity(employeeService.findByDocument(document), HttpStatus.OK)
}