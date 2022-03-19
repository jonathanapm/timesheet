package br.com.timesheet.service

import br.com.timesheet.controller.exception.EmployeeAlreadyRegisteredException
import br.com.timesheet.controller.exception.EmployeeNotFound
import br.com.timesheet.model.dto.EmployeeDTO
import br.com.timesheet.model.util.Mapper
import br.com.timesheet.persistence.entities.Employee
import br.com.timesheet.persistence.repository.EmployeeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EmployeeService(private val employeeRepository: EmployeeRepository) {

    /**
     * Salva um funcionário na base de dados
     * @param employeeDTO dados do novo funcionário
     * @return dados do funcionário salvo
     */
    fun saveEmployee(employeeDTO: EmployeeDTO): EmployeeDTO {
        try {
            findByDocument(employeeDTO.document).map {
                throw EmployeeAlreadyRegisteredException("Funcionário já existe para o documento ${employeeDTO.document}")
            }
            return employeeRepository.save(Mapper.convert<EmployeeDTO, Employee>(employeeDTO)).let(Mapper::convert)
        } catch (e: RuntimeException) {
            throw RuntimeException("Erro ao salvar funcionário", e)
        }
    }

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
            throw RuntimeException("Erro ao deletar funcionário", e)
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
            throw RuntimeException("Erro ao buscar funcionário por id", e)
        }

    /**
     * Busca um funcionário na base
     * @param document documento do funcionário
     * @return dados do funcionário encontrado
     */
    fun findEmployeeByDocument(document: String): EmployeeDTO =
        findByDocument(document)
            .map { Mapper.convert<Employee, EmployeeDTO>(it) }
            .orElseThrow(::EmployeeNotFound)

    private fun findByDocument(document: String) =
        try {
            employeeRepository.findByDocument(document)
        }catch (e: RuntimeException) {
            throw RuntimeException("Erro na busca de funcionário por documento", e)
        }
}