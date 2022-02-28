package br.com.timesheet.service

import br.com.timesheet.controller.exception.EmployeeNotFound
import br.com.timesheet.controller.exception.ApplicationError
import br.com.timesheet.model.dto.EmployeeDTO
import br.com.timesheet.model.util.Mapper
import br.com.timesheet.persistence.entities.Employee
import br.com.timesheet.persistence.repository.EmployeeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EmployeeService {

    @Autowired
    private lateinit var employeeRepository: EmployeeRepository

    /**
     * Salva um funcionário na base de dados
     * @param employeeDTO dados do novo funcionário
     * @return dados do funcionário salvo
     */
    fun saveEmployee(employeeDTO: EmployeeDTO): EmployeeDTO =
        employeeRepository.save(Mapper.convert<EmployeeDTO, Employee>(employeeDTO)).let(Mapper::convert)

    /**
     * Deleta um funcionário da base
     * @param employeeId identificar do funcionário
     */
    fun deleteEmployee(employeeId: Long): Unit =
        try {
            employeeRepository.findById(employeeId)
                .map(employeeRepository::delete)
                .orElseThrow(::EmployeeNotFound)
        } catch (e: RuntimeException) {
            throw e
        }

    /**
     * Busca um funcionário na base
     * @param employeeId identificador do funcionário
     * @return dados do funcionário encontrado
     */
    fun findEmployeeById(employeeId: Long): EmployeeDTO =
        try {
            employeeRepository.findById(employeeId)
                .map { Mapper.convert<Employee, EmployeeDTO>(it) }
                .orElseThrow(::EmployeeNotFound)
        } catch (e: RuntimeException) {
            throw e
        }

    /**
     * Busca um funcionário na base
     * @param document documento do funcionário
     * @return dados do funcionário encontrado
     */
    fun findByDocument(document: String): EmployeeDTO =
        try {
            employeeRepository.findByDocument(document)
                .map { Mapper.convert<Employee, EmployeeDTO>(it) }
                .orElseThrow(::EmployeeNotFound)
        }catch (e: RuntimeException) {
            throw e
        }
}