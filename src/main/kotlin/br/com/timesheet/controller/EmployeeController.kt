package br.com.timesheet.controller

import br.com.timesheet.model.dto.EmployeeDTO
import br.com.timesheet.service.EmployeeService
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

/**
 * Controlador do fluxo de funcionários
 */
@RestController
@RequestMapping("/employees")
class EmployeeController(
    private val employeeService: EmployeeService
) {

    /**
     * Salvar um novo funcionário na base
     * @param employee dados do funcionário
     * @return dados do funcionário salvo
     */
    @PostMapping("/register", produces = [MediaType.APPLICATION_JSON_VALUE])
    @ApiOperation("Salva um novo funcionário na base")
    @ApiResponses(
        ApiResponse(code = 201, message = "Informa que o funcionário foi criado com sucesso"),
        ApiResponse(code = 400, message = "Dados inválidos")
    )
    fun saveEmployee(@RequestBody @Valid employee: EmployeeDTO): ResponseEntity<EmployeeDTO> =
        ResponseEntity(employeeService.saveEmployee(employee), HttpStatus.CREATED)

    /**
     * Deletar um funcionário na base
     * @param employeeId identificador do funcionário
     * @return se o funcionário foi deletado com sucesso
     */
    @DeleteMapping("/remove/{employeeId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    @ApiOperation("Delete um funcionário da base")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Informa que o usuário foi removido com sucesso"),
        ApiResponse(code = 404, message = "Funcionário não encontrado")
    ])
    fun deleteEmployee(@PathVariable("employeeId") employeeId: Long): ResponseEntity<Unit> =
        ResponseEntity(employeeService.deleteEmployee(employeeId), HttpStatus.OK)

    /**
     * Buscar um funcionário na base através do seu identifcador
     * @param employeeId identificador do funcionário
     * @return funcionário encontrado
     */
    @GetMapping("/id/{employeeId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    @ApiOperation("Busca um funcionário na base através do seu identificar")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Funcionário encontrado na base de dados"),
        ApiResponse(code = 404, message = "Funcionário não encontrado")
    ])
    fun getEmployee(@PathVariable("employeeId") employeeId: Long): ResponseEntity<EmployeeDTO> =
        ResponseEntity(employeeService.findEmployee(employeeId), HttpStatus.OK)

    /**
     * Buscar um funcionário na base através do seu documento
     * @param document documento do funcionário
     * @return funcionário encontrado
     */
    @GetMapping("/document/{document}", produces = [MediaType.APPLICATION_JSON_VALUE])
    @ApiOperation("Busca um funcionário na base através do seu documento")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Funcionário encontrado na base de dados"),
        ApiResponse(code = 404, message = "Funcionário não encontrado")
    ])
    fun getEmployeeByDocument(@PathVariable("document") document: String): ResponseEntity<EmployeeDTO> =
        ResponseEntity(employeeService.findEmployee(document), HttpStatus.OK)
}